package com.example.a2501974391_uts_mcs.Model;

public class Article {
    Integer id;
    String title;
    String date;
    Integer image;
    String description;

    public Article() {
    }

    public Article(Integer id, String title, String date, Integer image, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.image = image;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
