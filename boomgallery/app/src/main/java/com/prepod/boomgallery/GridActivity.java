package com.prepod.boomgallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridLayoutManager lLayout;
    RecyclerView rView;
    private Cursor cursor;
    private ArrayList<String> mImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        setTitle(null);

        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(topToolBar);
        //topToolBar.setLogo(R.drawable.logo);
      //  topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));

        final List<ItemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(GridActivity.this, 3);

        rView = (RecyclerView)findViewById(R.id.rec_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.v("My", "!!!");
                        Intent intent = new Intent(GridActivity.this, FullScreen.class);

                        //intent.putStringArrayListExtra("images", mImages);
                        intent.putExtra("image",rowListItem.get(position).getPhoto());

                        startActivity(intent);
                    }
                })
        );

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(GridActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);
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

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
       // allItems.add(new ItemObject("United States", R.mipmap.ic_launcher));

        String[] list = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};

        //Retriving Images from Database(SD CARD) by Cursor.
        cursor = getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, list, null, null, MediaStore.Images.Thumbnails._ID);
        //columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

        if (cursor != null) {
            while (cursor.moveToNext()) {


                String hz = cursor.getString(1);

                mImages.add(hz);
                Uri imageUri = Uri.parse(hz);

                allItems.add(new ItemObject("!!!", imageUri));
            }
        }


        return allItems;
    }
}
