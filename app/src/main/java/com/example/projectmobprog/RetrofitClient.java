package com.example.projectmobprog;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Api myApi;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
        Log.d("instancejust finish crt", "RetrofitClient: "+myApi);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
            Log.d("Instance Created", "getInstance: "+instance);
        }
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}
