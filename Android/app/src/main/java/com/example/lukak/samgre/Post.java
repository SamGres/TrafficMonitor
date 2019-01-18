package com.example.lukak.samgre;


import java.math.BigDecimal;


public class Post {

    int idPost;
    String description;
    String category;
    Double x;
    double y;
    String region;

    public String getCause() {
        return cause;
    }

    public void setCause(String vzrok) {
        this.cause = vzrok;
    }

    String cause;

    User user;

    public Post() {}

    public Post(int idPost, String descriptio, String category, double x, double y, String region)
    {
        this.idPost = idPost;
        this.description = descriptio;
        this.category = category;
        this.x = x;
        this.y = y;
        this.region = region;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
