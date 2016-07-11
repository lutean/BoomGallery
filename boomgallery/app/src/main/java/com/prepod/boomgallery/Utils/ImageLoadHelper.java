package com.prepod.boomgallery.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.prepod.boomgallery.ItemImage;

import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Images.Media.query;

public class ImageLoadHelper {

    public static List<ItemImage> getImages(Context context){

        List<ItemImage> imageList = new ArrayList<ItemImage>();
        String[] listThumb = {MediaStore.Images.Thumbnails.IMAGE_ID, MediaStore.Images.Thumbnails.DATA};

        Cursor cursorThumb = MediaStore.Images.Thumbnails.queryMiniThumbnails(context.getContentResolver(),
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                MediaStore.Images.Thumbnails.MINI_KIND,
                listThumb);

        int thumbColumnIndex = cursorThumb.getColumnIndex(MediaStore.Images.Thumbnails.DATA);


        if (cursorThumb != null) {
            while (cursorThumb.moveToNext()) {
                //int thumbID = cursorThumb.getInt(thumbColumnIndex);
                String thumb = cursorThumb.getString(thumbColumnIndex);
                Uri thumbImageUri = Uri.parse(thumb);
                Uri fullImageUri = getFullImage(context, cursorThumb);
                imageList.add(new ItemImage("", thumbImageUri, fullImageUri));
            }
        }


        return imageList;
    }

    private static Uri getFullImage(Context context, Cursor cursorThumb){

        String imageId = cursorThumb.getString(cursorThumb.getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID));
        String[] list = {MediaStore.Images.Media.DATA};


        Cursor cursor = query(context.getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                list,
                MediaStore.Images.Media._ID + "=?",
                new String[] {imageId},
                null);


        int imageColumn = cursor.getColumnIndex(list[0]);

        if (cursor != null && cursor.moveToNext()) {
                String fullImageUri = cursor.getString(imageColumn);
                cursor.close();
                return Uri.parse(fullImageUri);
        } else {
            cursor.close();
            return Uri.parse("");
        }
    }

}
