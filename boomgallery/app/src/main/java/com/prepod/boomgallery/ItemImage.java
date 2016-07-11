package com.prepod.boomgallery;


import android.net.Uri;

public class ItemImage {

    private String imageName;
    private Uri thumbImageUri;
    private Uri fullImageUri;
    private int imageId;

    public ItemImage(String imageName, Uri thumbImageUri, Uri fullImageUri) {
        this.imageName = imageName;
        this.thumbImageUri = thumbImageUri;
        this.fullImageUri = fullImageUri;
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
}
