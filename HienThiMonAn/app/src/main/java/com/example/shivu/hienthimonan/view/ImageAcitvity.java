package com.example.shivu.hienthimonan.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.shivu.hienthimonan.R;
import com.example.shivu.hienthimonan.model.entity.FoodPhoto;

import java.lang.annotation.Target;

public class ImageAcitvity extends AppCompatActivity {
    public static final String EXTRA_FOOD_PHOTO = "FoodPhotoActivity.FOOD_PHOTO";
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mImageView = (ImageView) findViewById(R.id.image);
        FoodPhoto foodPhoto = getIntent().getParcelableExtra(EXTRA_FOOD_PHOTO);
        Glide.with(this)
                .load(foodPhoto.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(mImageView);
        }
}
