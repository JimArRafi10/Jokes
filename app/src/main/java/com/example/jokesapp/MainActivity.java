package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.jokesapp.Adapters.CategoryAdapter;
import com.example.jokesapp.fragments.Main;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView catList;
    List<String> categories;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categories = new ArrayList<>();
        //insert cat names

        categories.add("Any");
        categories.add("Programming");
        categories.add("Misc");
        categories.add("Pun");
        categories.add("Spooky");
        categories.add("Christmas");


        catList = findViewById(R.id.catList);
        adapter = new CategoryAdapter(categories);
        catList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        catList.setAdapter(adapter);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().replace(R.id.fragment_frame, new Main());
        transaction.commit();
    }
}