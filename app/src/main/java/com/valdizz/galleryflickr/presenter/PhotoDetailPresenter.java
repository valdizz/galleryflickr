package com.valdizz.galleryflickr.presenter;

import com.valdizz.galleryflickr.view.IPhotoDetailView;

public class PhotoDetailPresenter implements IPhotoDetailPresenter {

    private IPhotoDetailView photoDetailView;

    public PhotoDetailPresenter(IPhotoDetailView photoDetailView) {
        this.photoDetailView = photoDetailView;
    }

    @Override
    public void loadTitle(String title) {
        photoDetailView.showTitle(title);
    }

    @Override
    public void loadImage(String url) {
        photoDetailView.showImage(url);
    }
}
