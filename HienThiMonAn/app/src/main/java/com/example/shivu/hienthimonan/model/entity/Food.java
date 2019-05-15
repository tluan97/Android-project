package com.example.shivu.hienthimonan.model.entity;

public class Food {
    private int id;
    private String title;
    private String content;
    private String avatar;
    private String images;


    public Food(int id, String title, String content, String avatar, String images) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.avatar = avatar;
        this.images = images;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
