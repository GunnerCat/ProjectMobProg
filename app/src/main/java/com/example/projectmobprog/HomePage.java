package com.example.projectmobprog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.projectmobprog.recyclerView.Item;
import com.example.projectmobprog.recyclerView.ItemAdapter;

import java.util.ArrayList;

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

        Item item1 = new Item();
        item1.setText("text1");

        Item item2 = new Item();
        item2.setText("text2");

        Item item3 = new Item();
        item3.setText("text3");

        Item item4 = new Item();
        item4.setText("text4");

        Item item5 = new Item();
        item5.setText("text5");

        Item item6 = new Item();
        item5.setText("text5");

        for (int i = 0; i < 10; i++) {
            listItem.add(item1);
            listItem.add(item2);
            listItem.add(item3);
            listItem.add(item4);
            listItem.add(item5);
            listItem.add(item6);
        }
        listing.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(this,listItem);
        listing.setAdapter(adapter);

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