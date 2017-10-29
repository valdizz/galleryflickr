package com.valdizz.galleryflickr.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.valdizz.galleryflickr.R;
import com.valdizz.galleryflickr.presenter.IPhotoDetailPresenter;
import com.valdizz.galleryflickr.presenter.PhotoDetailPresenter;


public class PhotoDetailFragment extends Fragment implements IPhotoDetailView {

    public static final String PHOTO_TITLE = "photo_title";
    public static final String PHOTO_URL = "photo_url";
    public static final String NO_URL = "No url";
    public static final String NO_TITLE = "No title";

    private String photo_title, photo_item_url;
    private IPhotoDetailPresenter photoDetailPresenter;
    private ImageView imageView;
    private ProgressBar progressBar;
    private TextView titleTextView;

    public PhotoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (photoDetailPresenter == null) {
            photoDetailPresenter = new PhotoDetailPresenter(this);
        }

        photo_title = getArguments().containsKey(PHOTO_TITLE) ? getArguments().getString(PHOTO_TITLE) : NO_TITLE;
        photo_item_url = getArguments().containsKey(PHOTO_URL) ? getArguments().getString(PHOTO_URL) : NO_URL;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_detail, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.photo_item);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressbar);
        titleTextView = (TextView) rootView.findViewById(R.id.title_text);

        photoDetailPresenter.loadTitle(photo_title);
        photoDetailPresenter.loadImage(photo_item_url);
        return rootView;
    }

    @Override
    public void showTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void showImage(String url) {
        Picasso.with(getContext())
                .load(url)
                .error(android.R.drawable.stat_notify_error)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
