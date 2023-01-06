package com.example.projectmobprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projectmobprog.recyclerView.Item;
import com.example.projectmobprog.recyclerView.ItemAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {
    RecyclerView listing;
    ArrayList<Item> listItem;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        listing = findViewById(R.id.listing);
        listItem = new ArrayList<>();

        getItems();

    }
    private void getItems() {
        Call<Products> call = RetrofitClient.getInstance().getMyApi().getList();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {

                Products ItemList = response.body();

                Log.w("listItem add called",new Gson().toJson(response.body()));


                for (int i = 0; i < 20; i++) {
                    Item item = new Item();
                    item.setPrice(ItemList.getProducts().get(i).getPrice());
                    item.setTitle(ItemList.getProducts().get(i).getTitle());
                    listItem.add(item);
                }

                listing.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new ItemAdapter(getApplicationContext(),listItem);
                listing.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                String message = t.getMessage();
                Log.d("failure", message);
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.account_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.viewAccount:{

                Intent getIntent = getIntent();
                String username = getIntent.getStringExtra("username");

                Intent intent = new Intent(this, ViewAccountActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                return true;
            }

            case R.id.updateAccount:{
                Intent getIntent = getIntent();
                String username = getIntent.getStringExtra("username");

                Intent intent = new Intent(this, UpdateAccountActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                return true;
            }

            case R.id.logout:{
                finish();
            }

            default: return super.onOptionsItemSelected(item);
        }
    }

}