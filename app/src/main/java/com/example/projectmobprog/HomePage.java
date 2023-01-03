package com.example.projectmobprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

}