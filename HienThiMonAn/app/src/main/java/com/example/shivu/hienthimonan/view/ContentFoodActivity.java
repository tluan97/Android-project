package com.example.shivu.hienthimonan.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.shivu.hienthimonan.R;


public class ContentFoodActivity extends AppCompatActivity {
    private TextView txtContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_food);
        txtContent= findViewById(R.id.txtContent);
        //txtContent.setMovementMethod(new ScrollingMovementMethod());
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        txtContent.setText(android.text.Html.fromHtml(content));
    }
}
