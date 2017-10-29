package com.valdizz.galleryflickr.model.api;

import com.valdizz.galleryflickr.model.entity.PlaceholderPhoto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceholderAPI {

    @GET("/photos")
    Observable<List<PlaceholderPhoto>> getPhotos(@Query("albumId") int albumId);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
