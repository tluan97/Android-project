package com.example.shivu.hienthithongtindiadiem.view;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivu.hienthithongtindiadiem.R;
import com.example.shivu.hienthithongtindiadiem.helper.LandmarkSeeder;
import com.example.shivu.hienthithongtindiadiem.model.entity.Landmark;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lsLandmarks;
    private ArrayList<Landmark> landmarks;
    private ArrayList<String> titleLandmarks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mapComponent();
        eventComponent();
    }
    private void init(){
        landmarks = LandmarkSeeder.GetListLandmarks();
        titleLandmarks=new ArrayList<>();
        int len = landmarks.size();
        for(int i=0;i<len;++i){
            titleLandmarks.add((i+1) + ". " + landmarks.get(i).getTitle());
        }
    }
    private void mapComponent(){
        lsLandmarks = findViewById(R.id.lsLandmarks);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titleLandmarks);
        lsLandmarks.setAdapter(arrayAdapter);
    }
    private void eventComponent(){
        lsLandmarks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,final int i, long l) {
                Dialog dialog = new Dialog(MainActivity.this,R.style.Theme_AppCompat_Light_Dialog);
                dialog.setContentView(R.layout.dialog_map_website_landmark);
                TextView txtTitleDialog = dialog.findViewById(R.id.txtTitleDialog);
                txtTitleDialog.setText(landmarks.get(i).getTitle());
                Button btnMap = dialog.findViewById(R.id.btnMap);
                Button btnInfo = dialog.findViewById(R.id.btnInfo);
                btnInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,WebActivity.class);
                        intent.putExtra(WebActivity.EXTRA_URL,landmarks.get(i).getWebsite());
                        startActivity(intent);
                    }
                });
                btnMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,MapActivity.class);
                        intent.putExtra(MapActivity.EXTRA_LAND_MAP,landmarks.get(i));
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });
    }
}
