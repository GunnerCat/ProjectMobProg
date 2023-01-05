package com.example.projectmobprog;

import com.example.projectmobprog.recyclerView.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://dummyjson.com/";
    @GET("products")
    Call<Products>    getList();
}
