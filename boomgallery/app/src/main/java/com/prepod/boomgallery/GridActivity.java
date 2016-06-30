package com.prepod.boomgallery;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKPhotoArray;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    CustomDrawerAdapter adapter;
    List<DrawerItem> dataList;
    String[] mDrawerTitles;
    AvaView ava;
    TextView nameMe;
    ImageLoader imageLoader;
    private static final String[] sMyScope = new String[] {
            VKScope.FRIENDS,
            VKScope.WALL,
            VKScope.PHOTOS,
            VKScope.NOHTTPS,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        setTitle(null);

        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(topToolBar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ava = (AvaView) findViewById(R.id.drawer_pic);
        nameMe = (TextView) findViewById(R.id.name);

        imageLoader = BoomGallery.getInstance().getImageLoader();

        LinearLayout drawerBtn = (LinearLayout) findViewById(R.id.drawer_btn);
        drawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerTitles = new String[]{"Gallery"};





        showGrid();
        //topToolBar.setLogo(R.drawable.logo);
      //  topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));


    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_refresh){
            Toast.makeText(MainActivity.this, "Refresh App", Toast.LENGTH_LONG).show();
        }
        if(id == R.id.action_new){
            Toast.makeText(MainActivity.this, "Create Text", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
*/

    private void showGrid(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new GridFragment())
                .commit();
    }

    public void getInfoVK(){

        VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS,
                "photo_200"));
//        VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.USER_IDS, "1,2"));
        request.secure = false;
        request.useSystemLanguage = true;

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                //  Bitmap map = null;
                final VKApiUserFull user = (VKApiUserFull) ((VKList) response.parsedModel).get(0);
                String avaVk = user.photo_200;
                String name = user.first_name;
                String lastName = user.last_name;
                ava.setImageUrl(avaVk, imageLoader);
                if (name != null && lastName != null)
                    nameMe.setText(name + " " + lastName);

            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
    }

    private void sync(){
        List<String> allItems = new ArrayList<>();
        String[] list = {MediaStore.Images.Thumbnails._ID, MediaStore.Images.Thumbnails.DATA};
        Cursor cursor = getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, list, null, null, MediaStore.Images.Thumbnails._ID);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String hz = cursor.getString(1);
                allItems.add(hz);
                //Uri imageUri = Uri.parse(hz);
                //allItems.add(imageUri);
            }
        }
       // Bitmap photo = null;
        for (int i=0; i < allItems.size(); i++) {
            //Uri imageUri = allItems.get(i);

           /* try {
                photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            Bitmap photo = BitmapFactory.decodeFile(allItems.get(i));
            VKRequest request = VKApi.uploadAlbumPhotoRequest(new VKUploadImage(photo, VKImageParameters.pngImage()), 233415735, 0);
            request.executeWithListener(new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {

                    Log.v("My", " " + response);
                }

                @Override
                public void onError(VKError error) {

                    Log.v("My", " " + error);
                }

                @Override
                public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                     Log.v("My", " " + request);
                }
            });

        }

    }

    private void checkAlbum(){
        VKRequest request = new VKRequest("photos.getAlbums", VKParameters.from("extended", 1, "type", "request"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_item, mDrawerTitles));

        dataList = new ArrayList<DrawerItem>();
        if (VKSdk.isLoggedIn()) {
            dataList.add(new DrawerItem("Logout", R.drawable.vk));
            dataList.add(new DrawerItem("Sync", R.drawable.sync));
        }else{
            dataList.add(new DrawerItem("Login", R.drawable.vk));
        }

        adapter = new CustomDrawerAdapter(this, R.layout.drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        //showFeed();
                        if (VKSdk.isLoggedIn()) {
                            VKSdk.logout();
                            nameMe.setText(null);
                            ava.setImageResource(R.drawable.log);
                        } else {
                            VKSdk.login(GridActivity.this, sMyScope);
                        }
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case 1:
                        sync();
                        /*LoginManager.ge*nstance().logOut();
                        Intent dialogIntent = new Intent(MainActivity.this , AuthActivity.class);
                        startActivity(dialogIntent);*/




                        break;
                }
            }
        });

        if (VKSdk.isLoggedIn())
            getInfoVK();
    }


}
