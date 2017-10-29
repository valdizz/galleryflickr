package com.valdizz.galleryflickr.presenter;

import com.valdizz.galleryflickr.model.PhotoModel;
import com.valdizz.galleryflickr.model.entity.Photo;
import com.valdizz.galleryflickr.view.IPhotoListView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class PhotoListPresenter implements IPhotoListPresenter {

    private PhotoModel photoModel;
    private IPhotoListView photoListView;

    public PhotoListPresenter(IPhotoListView photoListView) {
        photoModel = new PhotoModel();
        this.photoListView = photoListView;
    }

    @Override
    public void getPhotosData() {

        photoListView.showLoadingIndicator();
        Observable<List<Photo>> photosObservable = photoModel.getPhotos();
        photosObservable.subscribe(new Consumer<List<Photo>>() {
            @Override
            public void accept(List<Photo> photosresult) throws Exception {
                photoListView.updatePhotosRecyclerView(photosresult);
                photoListView.hideLoadingIndicator();
            }
        });
    }
}
