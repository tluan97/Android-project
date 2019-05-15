package com.example.shivu.hienthimonan.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.shivu.hienthimonan.R;
import com.example.shivu.hienthimonan.model.entity.Food;
import com.example.shivu.hienthimonan.view.ContentFoodActivity;
import com.example.shivu.hienthimonan.view.ImagesFoodActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends ArrayAdapter<Food>{
    private Context context;
    private int resource;
    private List<Food> objects;

    public FoodAdapter(Context context, int resource, ArrayList<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_food, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtTitle = convertView.findViewById(R.id.txtTitle);
            viewHolder.imgAvatar = convertView.findViewById(R.id.imgAvatar);
            viewHolder.layoutLeft= convertView.findViewById(R.id.layoutLeft);
            viewHolder.layoutRight =convertView.findViewById(R.id.layoutRight);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Food food = objects.get(position);
        viewHolder.txtTitle.setText(food.getTitle());
        Glide.with(context)
                .load(food.getAvatar())
                .override(80,80)
                .centerCrop()
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(viewHolder.imgAvatar);
        viewHolder.layoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivityContentFood(food);
            }
        });
        viewHolder.layoutRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showActivityImagesFood(food);
            }
        });

        return convertView;
    }

    void showActivityContentFood(Food food){
        Intent intent = new Intent(context,ContentFoodActivity.class);
        intent.putExtra("content",food.getContent());
        context.startActivity(intent);
    }
    void showActivityImagesFood(Food food){
        Intent intent = new Intent(context,ImagesFoodActivity.class);
        intent.putExtra(ImagesFoodActivity.EXTRA_URLS, food.getImages());
        context.startActivity(intent);
    }


    public class ViewHolder {
        TextView txtTitle;
        ImageView imgAvatar;
        LinearLayout layoutLeft,layoutRight;
    }
}
