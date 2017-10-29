package com.valdizz.galleryflickr.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valdizz.galleryflickr.R;
import com.valdizz.galleryflickr.model.entity.Photo;
import com.valdizz.galleryflickr.view.PhotoDetailActivity;
import com.valdizz.galleryflickr.view.PhotoDetailFragment;

import java.util.List;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.ViewHolder> {

    private final List<Photo> photos;
    private boolean mTwoPane;

    public PhotosRecyclerViewAdapter(List<Photo> photos, boolean mTwoPane) {
        this.photos = photos;
        this.mTwoPane = mTwoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(photos.get(position).getTitle());
        holder.url.setText(photos.get(position).getUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(PhotoDetailFragment.PHOTO_TITLE, holder.title.getText().toString());
                    arguments.putString(PhotoDetailFragment.PHOTO_URL, holder.url.getText().toString());
                    PhotoDetailFragment fragment = new PhotoDetailFragment();
                    fragment.setArguments(arguments);
                    ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.photo_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, PhotoDetailActivity.class);
                    intent.putExtra(PhotoDetailFragment.PHOTO_TITLE, holder.title.getText().toString());
                    intent.putExtra(PhotoDetailFragment.PHOTO_URL, holder.url.getText().toString());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos==null ? 0 : photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView url;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.photo_title);;
            url = (TextView) itemView.findViewById(R.id.photo_url);;
        }
    }
}
