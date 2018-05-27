package com.example.sawara.tarek.Ex4_Networking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageViewHOlder> {

    private ArrayList<ImageViewModel> mImages;

    public RecyclerAdapter(ArrayList<ImageViewModel> images) {
        this.mImages = images;
    }

    @NonNull
    @Override
    public ImageViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout,parent,false);
        ImageViewHOlder mImageViewHOlder = new ImageViewHOlder(view);
        return mImageViewHOlder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHOlder holder, int position) {
        holder.bindViewHolder(mImages.get(position),position+1);

    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public static class ImageViewHOlder extends RecyclerView.ViewHolder {

        ImageView mAlbum;
        TextView mAlbumTitle;

        public ImageViewHOlder(View itemView) {
            super(itemView);
            mAlbum = itemView.findViewById(R.id.album);
            mAlbumTitle = itemView.findViewById(R.id.ablum_title);
        }
        public void bindViewHolder(ImageViewModel image,int index) {
            if (!TextUtils.isEmpty(image.getImageUrl())) {
                Picasso.with(mAlbum.getContext())
                        .load(image.getImageUrl())
                        .into(mAlbum);
                mAlbumTitle.setText("Image :"+index);
            }

        }
    }
}