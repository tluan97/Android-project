package com.example.shivu.hienthimonan.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shivu.hienthimonan.R;
import com.example.shivu.hienthimonan.model.entity.FoodPhoto;
import com.example.shivu.hienthimonan.view.ImageAcitvity;

import java.util.ArrayList;

public class ImageFoodAdapter extends RecyclerView.Adapter<ImageFoodAdapter.MyViewHolder>  {
    private ArrayList<FoodPhoto> foodPhotos;
    private Context context;

    public ImageFoodAdapter(Context context, ArrayList<FoodPhoto> foodPhotos) {
        this.context = context;
        this.foodPhotos = foodPhotos;
    }

    @Override
    public ImageFoodAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.row_image_food, parent, false);
        ImageFoodAdapter.MyViewHolder viewHolder = new ImageFoodAdapter.MyViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageFoodAdapter.MyViewHolder holder, int position) {
        FoodPhoto foodPhoto = foodPhotos.get(position);
        if(foodPhoto.getUrl().isEmpty()){
            return;
        }
        ImageView imageView = holder.mPhotoImageView;
        Glide.with(context)
                .load(foodPhoto.getUrl())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (foodPhotos.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mPhotoImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPhotoImageView = (ImageView) itemView.findViewById(R.id.imgFood);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                FoodPhoto spacePhoto = foodPhotos.get(position);
                Intent intent = new Intent(context, ImageAcitvity.class);
                intent.putExtra(ImageAcitvity.EXTRA_FOOD_PHOTO, spacePhoto);
                context.startActivity(intent);
        }
        }
    }


}
