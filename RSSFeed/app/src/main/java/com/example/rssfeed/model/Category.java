package com.example.rssfeed.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String title;
    private String linkrss;

    public Category(int id, String title, String linkrss) {
        this.id = id;
        this.title = title;
        this.linkrss = linkrss;
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

    public String getLinkrss() {
        return linkrss;
    }

    public void setLinkrss(String linkrss) {
        this.linkrss = linkrss;
    }
}
