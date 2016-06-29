package com.prepod.boomgallery;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Антон on 30.06.2016.
 */
public class FullScreen  extends AppCompatActivity {

    private ImageView fullImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        fullImage = (ImageView) findViewById(R.id.imageFullScreen);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
               Uri mImages = (Uri)extras.get("image");
                Log.v("My", "" + mImages);
                fullImage.setImageURI(mImages);
            }
        }

    }
}
