package com.example.farmmarket;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends AppCompatActivity {

    private RecyclerView recyclerViewProductList;
    private RecyclerView.Adapter adapter;

    private ImageView btnBack;

    ArrayList<Product> arrproducts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.fragment_product);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.arrproducts = getIntent().getParcelableArrayListExtra("listProducts");


        recyclerViewProductList = findViewById(R.id.listProduct);
        recyclerViewProductList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProductList.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewProductList.setLayoutManager(gridLayoutManager);

        adapter=new ProductAdaptor(arrproducts);
        recyclerViewProductList.setAdapter(adapter);
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}