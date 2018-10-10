package com.prepod.boomgallery.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.prepod.boomgallery.R;

public class FullScreenActivity extends AppCompatActivity {

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
                String ttmp = mImages.toString();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap photo = BitmapFactory.decodeFile(ttmp, options);
                Log.v("My", "" + mImages);
                fullImage.setImageBitmap(photo);
            }
        }

    }
}
