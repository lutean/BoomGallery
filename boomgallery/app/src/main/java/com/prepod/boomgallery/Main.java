package com.prepod.boomgallery;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 09.06.2016.
 */
public class Main extends Activity {
    //cursor and columnIndex defined Globally so we can use it in below class.
    private Cursor cursor;
    private int columnIndex;
    private ImageView imageView;
    private ListView listView;
    private List<String> pictures = new ArrayList();
    FeedListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listImg);

        listAdapter = new FeedListAdapter(this, pictures);

        listView.setAdapter(listAdapter);
        //Searching Images ID's from Gallery. _ID is the Default id code for all. You can retrive image,contacts,music id in the same way.
        String[] list = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};

        //Retriving Images from Database(SD CARD) by Cursor.
        cursor = getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, list, null, null, MediaStore.Images.Thumbnails._ID);
        //columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

        if (cursor != null){
           while (cursor.moveToNext()) {


                String hz = cursor.getString(1);

               pictures.add(hz);
                Log.v("my", hz);

            }
            listAdapter.notifyDataSetChanged();
        }

    }
}