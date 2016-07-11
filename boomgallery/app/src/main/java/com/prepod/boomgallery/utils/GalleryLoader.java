package com.prepod.boomgallery.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.prepod.boomgallery.ItemImage;

import java.util.List;

public class GalleryLoader extends AsyncTaskLoader<List<ItemImage>>{

    private List<ItemImage> images;

    public GalleryLoader(Context context){
        super(context);
    }

    @Override
    public List<ItemImage> loadInBackground() {
        Context context = getContext();
        List<ItemImage> imageList = ImageLoadHelper.getImages(context);
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
