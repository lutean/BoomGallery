package com.prepod.boomgallery.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.prepod.boomgallery.BoomGallery;
import com.prepod.boomgallery.adapters.ItemImage;

import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Images.Media.query;

public class ImageLoadHelper {

    public static List<ItemImage> getImages(Context context){

        List<ItemImage> imageList = new ArrayList<ItemImage>();
        String[] listThumb = {MediaStore.Images.Thumbnails.IMAGE_ID, MediaStore.Images.Thumbnails.DATA};
        Uri test =   MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;

        Cursor cursorThumb = MediaStore.Images.Thumbnails.queryMiniThumbnails(context.getContentResolver(),
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                MediaStore.Images.Thumbnails.MINI_KIND,
                listThumb);

        int thumbColumnIndex = cursorThumb.getColumnIndex(MediaStore.Images.Thumbnails.DATA);
        int idColumnIndex = cursorThumb.getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID);

        if (cursorThumb != null) {
            while (cursorThumb.moveToNext()) {
                String thumb = cursorThumb.getString(thumbColumnIndex);
                Uri thumbImageUri = Uri.parse(thumb);
                Uri fullImageUri = getFullImage(context, cursorThumb);
                long id = cursorThumb.getLong(cursorThumb.getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID));
                Uri contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                imageList.add(new ItemImage("", thumbImageUri, fullImageUri, contentUri));
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
               // return Uri.parse("content://" + fullImageUri);
                return Uri.parse(fullImageUri);
        } else {
            cursor.close();
            return Uri.parse("");
        }
    }

    public static Uri getContentUri(int position){

        return BoomGallery.imageList.get(position).getContenetUri();
    }

}
