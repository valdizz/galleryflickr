package com.valdizz.galleryflickr.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.valdizz.galleryflickr.R;
import com.valdizz.galleryflickr.adapter.PhotosRecyclerViewAdapter;
import com.valdizz.galleryflickr.model.entity.Photo;
import com.valdizz.galleryflickr.presenter.IPhotoListPresenter;
import com.valdizz.galleryflickr.presenter.PhotoListPresenter;

import java.util.ArrayList;
import java.util.List;

public class PhotoListActivity extends AppCompatActivity implements IPhotoListView {

    private IPhotoListPresenter photoListPresenter;
    private boolean mTwoPane;
    private List<Photo> photos;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);

        if (findViewById(R.id.photo_detail_container) != null) {
            mTwoPane = true;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(refreshListener);

        photos = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.photo_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new PhotosRecyclerViewAdapter(photos, mTwoPane));

        if (photoListPresenter==null) {
            photoListPresenter = new PhotoListPresenter(this);
        }
        photoListPresenter.getPhotosData();
    }

    @Override
    public void updatePhotosRecyclerView(List<Photo> photosresult) {
        photos.clear();
        photos.addAll(photosresult);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showLoadingIndicator() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingIndicator() {
        swipeRefreshLayout.setRefreshing(false);
    }

    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            photoListPresenter.getPhotosData();
        }
    };

}
