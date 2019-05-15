package com.example.shivu.hienthithongtindiadiem.model.entity;

import java.io.Serializable;

public class Landmark implements Serializable{
    private int id;
    private String title;
    private double latitude;
    private double longidute;
    private String website;

    public Landmark(int id, String title, double latitude, double longidute, String website) {
        this.id = id;
        this.title = title;
        this.latitude = latitude;
        this.longidute = longidute;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongidute() {
        return longidute;
    }

    public void setLongidute(double longidute) {
        this.longidute = longidute;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
