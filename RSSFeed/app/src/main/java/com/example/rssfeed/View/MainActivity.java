package com.example.rssfeed.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.rssfeed.R;
import com.example.rssfeed.helper.CategoriesSeeder;
import com.example.rssfeed.model.Category;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lsCategory;
    private ArrayList<Category> categories;
    private ArrayList<String> titleCategories;

    ImageView imgHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Specialtyfood.com Recipes");
        imgHinh = (ImageView)findViewById(R.id.logofood2);
        imgHinh.setImageResource(R.drawable.fdc_icon);
        init();
        mapComponent();
        eventComponent();
    }
    private void init(){
        categories = CategoriesSeeder.GetListCategories();
        titleCategories = new ArrayList<>();
        int len = categories.size();
        for(int i=0;i<len;++i){
            titleCategories.add((i+1) + ". " + categories.get(i).getTitle());
        }
    }
    private void mapComponent(){
        lsCategory = findViewById(R.id.lsCategory);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titleCategories);
        lsCategory.setAdapter(arrayAdapter);
    }
    private void eventComponent(){
        lsCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ListFood.class);
                intent.putExtra(ListFood.RSSLINK,categories.get(position).getLinkrss());
                intent.putExtra(ListFood.RSSTITLE,categories.get(position).getTitle());
                startActivity(intent);
            }
        });
    }
}
