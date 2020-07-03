package com.chetu.user.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/7/2.
 */
public class XuanZeFuWuModel implements Serializable {
    String id;
    String title;
    double money;

    public XuanZeFuWuModel(String id, String title, double money) {
        this.id = id;
        this.title = title;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
