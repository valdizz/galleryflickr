package com.valdizz.galleryflickr.model.api;

import com.valdizz.galleryflickr.model.entity.FlickrPhotos;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrAPI {

    @GET("/services/rest/?method=flickr.photos.getRecent")
    Observable<FlickrPhotos> getRecentPhotos(@Query("api_key") String api_key, @Query("per_page") int per_page, @Query("format") String format, @Query("nojsoncallback") int nojsoncallback);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
