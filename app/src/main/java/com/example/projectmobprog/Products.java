package com.example.projectmobprog;

import com.example.projectmobprog.recyclerView.Item;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Products {
    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }

    @SerializedName("products")
    private List<Item> products = null;



}
