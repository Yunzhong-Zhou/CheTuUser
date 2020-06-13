package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/13.
 */
public class Fragment1TabModel implements Serializable {
    String id;
    int category;
    String title;
    String img;

    public Fragment1TabModel(String id, int category, String title, String img) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
