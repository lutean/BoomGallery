package com.prepod.boomgallery.adapters;


import android.net.Uri;

public class ItemImage {

    private String imageName;
    private Uri thumbImageUri;
    private Uri fullImageUri;
    private Uri contenetUri;
    private int imageId;

    public ItemImage(String imageName, Uri thumbImageUri, Uri fullImageUri, Uri contenetUri) {
        this.imageName = imageName;
        this.thumbImageUri = thumbImageUri;
        this.fullImageUri = fullImageUri;
        this.contenetUri = contenetUri;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Uri getThumbImageUri() {
        return thumbImageUri;
    }

    public void setThumbImageUri(Uri thumbImageUri) {
        this.thumbImageUri = thumbImageUri;
    }

    public Uri getFullImageUri() {
        return fullImageUri;
    }

    public void setFullImageUri(Uri fullImageUri) {
        this.fullImageUri = fullImageUri;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public Uri getContenetUri() {
        return contenetUri;
    }

    public void setContenetUri(Uri contenetUri) {
        this.contenetUri = contenetUri;
    }
}
