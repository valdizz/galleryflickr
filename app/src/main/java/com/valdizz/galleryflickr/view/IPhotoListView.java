package com.valdizz.galleryflickr.view;

import com.valdizz.galleryflickr.model.entity.Photo;

import java.util.List;

public interface IPhotoListView {

    void updatePhotosRecyclerView(List<Photo> photos);
    void showLoadingIndicator();
    void hideLoadingIndicator();
}
