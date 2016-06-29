package com.prepod.boomgallery;


import android.net.Uri;

import java.net.URI;

public class ItemObject {

    private String name;
    private Uri photo;

    public ItemObject(String name, Uri photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}
