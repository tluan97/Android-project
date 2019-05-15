package com.example.shivu.hienthimonan.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class FoodPhoto implements Parcelable {

    private String mUrl;
    private String mTitle;

    public FoodPhoto(String url, String title) {
        mUrl = url;
        mTitle = title;
    }
    protected FoodPhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }
    public static ArrayList<FoodPhoto> getFoodPhoto(String urls){
        String[] listUrl = urls.split(";");
        ArrayList<FoodPhoto> photos = new ArrayList<>();
        int lenListUrl = listUrl.length;
        for(int i = 0; i<lenListUrl;++i){
            photos.add(new FoodPhoto(listUrl[i],"Pic "+i));
        }
        return photos;
    }

    public static final Creator<FoodPhoto> CREATOR = new Creator<FoodPhoto>() {
        @Override
        public FoodPhoto createFromParcel(Parcel in) {
            return new FoodPhoto(in);
        }

        @Override
        public FoodPhoto[] newArray(int size) {
            return new FoodPhoto[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
