package com.example.shivu.hienthithongtindiadiem.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.example.shivu.hienthithongtindiadiem.R;
import com.example.shivu.hienthithongtindiadiem.presentation.MyWebViewClient;

public class WebActivity extends AppCompatActivity {
    public static String EXTRA_URL="WebActivity.url";
    WebView wvLandmark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        String url = getIntent().getStringExtra(EXTRA_URL);
        mapComponent();
        wvLandmark.setWebViewClient(new MyWebViewClient());
        wvLandmark.getSettings().setLoadsImagesAutomatically(true);
        wvLandmark.getSettings().setJavaScriptEnabled(true);
        wvLandmark.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvLandmark.loadUrl(url);
    }
    private void mapComponent(){
        wvLandmark= findViewById(R.id.wvLandmarks);
    }
}
