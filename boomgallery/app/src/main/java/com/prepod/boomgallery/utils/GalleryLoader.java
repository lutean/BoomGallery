package com.prepod.boomgallery.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.AsyncTaskLoader;

import com.prepod.boomgallery.adapters.ItemImage;

import java.util.List;

public class GalleryLoader extends AsyncTaskLoader<List<ItemImage>>{

    private List<ItemImage> images;
    private Activity parentActivity;

    public GalleryLoader(Activity parentActivity){
        super(parentActivity);
        this.parentActivity = parentActivity;
    }

    @Override
    public List<ItemImage> loadInBackground() {
        Context context = getContext();
        List<ItemImage> imageList = ImageLoadHelper.getImages(parentActivity);
        return imageList;
    }

    @Override protected void onStartLoading() {

        if (images != null) {
            deliverResult(images);
        } else {
            forceLoad();
        }
    }

    @Override protected void onStopLoading() {
        cancelLoad();
    }

    @Override protected void onReset() {
        super.onReset();
        onStopLoading();

        if (images != null) {
            onReleaseResources(images);
            images = null;
        }
    }

    protected void onReleaseResources(List<ItemImage> list) {

    }



}
