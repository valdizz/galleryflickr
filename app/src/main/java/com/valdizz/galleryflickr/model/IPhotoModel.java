package com.valdizz.galleryflickr.model;

import com.valdizz.galleryflickr.model.entity.Photo;

import java.util.List;

import io.reactivex.Observable;

public interface IPhotoModel {

    Observable<List<Photo>> getPhotos();
}
