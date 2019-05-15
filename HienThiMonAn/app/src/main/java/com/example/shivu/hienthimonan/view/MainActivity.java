package com.example.shivu.hienthimonan.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.shivu.hienthimonan.R;
import com.example.shivu.hienthimonan.helper.FoodSeeder;
import com.example.shivu.hienthimonan.model.entity.Food;
import com.example.shivu.hienthimonan.presenter.FoodAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvFoods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvFoods = findViewById(R.id.lsFoods);
        ArrayList<Food> foods = FoodSeeder.GenerateData();

        FoodAdapter foodAdapter = new FoodAdapter(this,R.layout.row_food,foods);
        lvFoods.setAdapter(foodAdapter);
    }
}
