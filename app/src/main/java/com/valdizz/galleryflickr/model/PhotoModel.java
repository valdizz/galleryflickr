package com.valdizz.galleryflickr.model;

import com.valdizz.galleryflickr.model.api.FlickrAPI;
import com.valdizz.galleryflickr.model.api.PlaceholderAPI;
import com.valdizz.galleryflickr.model.entity.FlickrPhotos;
import com.valdizz.galleryflickr.model.entity.Photo;
import com.valdizz.galleryflickr.model.entity.PlaceholderPhoto;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class PhotoModel implements IPhotoModel{

    @Override
    public Observable<List<Photo>> getPhotos() {

        Observable<List<PlaceholderPhoto>> placeholderObservable = PlaceholderAPI.retrofit
                .create(PlaceholderAPI.class)
                .getPhotos(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<FlickrPhotos> flickrPhotosObservable = FlickrAPI.retrofit
                .create(FlickrAPI.class)
                .getRecentPhotos("e51b3aa09f1d4177a1a4afae61bd831c", 50, "json", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<List<Photo>> combinedPhotos = Observable.zip(placeholderObservable, flickrPhotosObservable, new BiFunction<List<PlaceholderPhoto>, FlickrPhotos, List<Photo>>() {
                    @Override
                    public List<Photo> apply(@io.reactivex.annotations.NonNull List<PlaceholderPhoto> placeholderPhotos, @io.reactivex.annotations.NonNull FlickrPhotos flickrPhotos) throws Exception {
                        List<Photo> photos = new ArrayList<>();
                        for (int i = 0; i<50; i++){
                            photos.add(new Photo(placeholderPhotos.get(i).getTitle(), flickrPhotos.getPage().getPhotos().get(i).getUrl()));
                        }
                        return photos;
                    }
                }
        );
        return combinedPhotos;
    }
}
