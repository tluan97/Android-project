package com.example.shivu.hienthithongtindiadiem.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.shivu.hienthithongtindiadiem.R;
import com.example.shivu.hienthithongtindiadiem.model.entity.Landmark;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static String EXTRA_LAND_MAP = "MapActivity.Land.Map";
    MapFragment mapFragment;
    GoogleMap map;
    Landmark landmark;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        landmark = (Landmark)getIntent().getSerializableExtra(EXTRA_LAND_MAP);
        mapComponent();
    }
    private void mapComponent(){
        mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.fmtMap);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng location = new LatLng(landmark.getLatitude(),landmark.getLongidute());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,13));
        map.addMarker(new MarkerOptions()
            .title(landmark.getTitle())
            .snippet("Website: "+landmark.getWebsite())
            .position(location));
    }
}
