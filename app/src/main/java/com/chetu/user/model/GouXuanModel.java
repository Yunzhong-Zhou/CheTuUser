package com.chetu.user.model;

/**
 * Created by zyz on 2020/6/11.
 */
public class GouXuanModel {
    String title;
    boolean isgouxuan;

    public GouXuanModel(String title, boolean isgouxuan) {
        this.title = title;
        this.isgouxuan = isgouxuan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsgouxuan() {
        return isgouxuan;
    }

    public void setIsgouxuan(boolean isgouxuan) {
        this.isgouxuan = isgouxuan;
    }
}
