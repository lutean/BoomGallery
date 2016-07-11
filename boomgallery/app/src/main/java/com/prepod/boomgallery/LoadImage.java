package com.prepod.boomgallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class LoadImage {

    public void load(String uri, ImageView imageView){
        LoadImageTask task = new LoadImageTask(imageView);
        task.execute(uri);
    }

    private Bitmap loadFile(String uri){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        options.outHeight = 100;
        options.outWidth = 100;
        return BitmapFactory.decodeFile(uri);
    }


    private class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

        private String filePath;
        private ImageView imageView;


        public String getFilePath() {
            return filePath;
        }

        public LoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            filePath = params[0];
            return loadFile(filePath);
        }
    }



}
