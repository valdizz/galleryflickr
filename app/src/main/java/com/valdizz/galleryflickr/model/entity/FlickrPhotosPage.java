package com.valdizz.galleryflickr.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrPhotosPage {

    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    @SerializedName("perpage")
    private int perpage;
    @SerializedName("total")
    private int total;
    @SerializedName("photo")
    private List<FlickrPhoto> photos;
    @SerializedName("stat")
    private String stat;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<FlickrPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<FlickrPhoto> photos) {
        this.photos = photos;
    }

}
