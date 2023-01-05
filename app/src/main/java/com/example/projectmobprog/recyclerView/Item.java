package com.example.projectmobprog.recyclerView;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private int price;


    public String getTitle() {
        return title;
    }
    public int getPrice() {
        return price;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(int price) {
        this.price = price;
    }

}
