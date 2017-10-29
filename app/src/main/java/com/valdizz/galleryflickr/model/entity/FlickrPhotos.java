package com.valdizz.galleryflickr.model.entity;

import com.google.gson.annotations.SerializedName;

public class FlickrPhotos {

    @SerializedName("photos")
    public FlickrPhotosPage page;

    public FlickrPhotosPage getPage() {
        return page;
    }

    public void setPage(FlickrPhotosPage page) {
        this.page = page;
    }
}
