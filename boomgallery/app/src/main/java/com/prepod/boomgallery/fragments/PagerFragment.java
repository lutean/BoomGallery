package com.prepod.boomgallery.fragments;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prepod.boomgallery.BoomGallery;
import com.prepod.boomgallery.Consts;
import com.prepod.boomgallery.R;
import com.prepod.boomgallery.utils.GalleryLoader;

public class PagerFragment extends Fragment {

    private ImageView fullImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_pager, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Integer startPosition = getArguments().getInt("startPosition", 0);

        fullImage = (ImageView) getView().findViewById(R.id.imageFullScreen);

        Uri mImages = BoomGallery.imageList.get(startPosition).getFullImageUri();
        String tmp = mImages.toString();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap photo = BitmapFactory.decodeFile(tmp, options);
        fullImage.setImageBitmap(photo);
    }
}
