package com.example.shivu.hienthithongtindiadiem.helper;

import com.example.shivu.hienthithongtindiadiem.model.entity.Landmark;

import java.util.ArrayList;

public class LandmarkSeeder {
    public static ArrayList<Landmark> GetListLandmarks(){
        ArrayList<Landmark> landmarks = new ArrayList<>();
        landmarks.add(new Landmark(1,
                "Cleveland Tower City",
                41.497062,
                -81.6940056,
                "https://www.towercitycenter.com/"
        ));
        landmarks.add(new Landmark(2,
                "Browns Football Field",
                41.5060575,
                -81.7017368,
                "http://www.firstenergystadium.com/"
        ));
        landmarks.add(new Landmark(3,
                "Cleveland State University",
                41.5024973,
                -81.6747185,
                "https://www.csuohio.edu/"
        ));
        landmarks.add(new Landmark(4,
                "Playhouse Square",
                41.5013088,
                -81.6829834,
                "http://www.playhousesquare.org/"
        ));
        landmarks.add(new Landmark(5,
                "Art Museum",
                41.503373,
                -81.6079629,
                "http://clevelandmuseumofart.art/"
        ));
        return landmarks;
    }
}
