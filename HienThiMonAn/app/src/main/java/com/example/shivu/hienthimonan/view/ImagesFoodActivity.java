package com.example.shivu.hienthimonan.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shivu.hienthimonan.R;
import com.example.shivu.hienthimonan.model.entity.FoodPhoto;
import com.example.shivu.hienthimonan.presenter.ImageFoodAdapter;

public class ImagesFoodActivity extends AppCompatActivity {
    public static String EXTRA_URLS= "ImagesFood.URLS";
    RecyclerView lsImages;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_food);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,4);
        lsImages = findViewById(R.id.lsImages);
        lsImages.setHasFixedSize(true);
        lsImages.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        String urls = intent.getStringExtra(EXTRA_URLS);
        ImageFoodAdapter adapter = new ImageFoodAdapter(this, FoodPhoto.getFoodPhoto(urls));
        lsImages.setAdapter(adapter);
    }
}
