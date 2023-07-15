package com.example.farmmarket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductActivity extends AppCompatActivity implements ProductAdaptor.ProductRecycle{

    private RecyclerView recyclerViewProductList;
    private RecyclerView.Adapter adapter;

    private ImageView btnBack;

    ArrayList<Product> arrproducts;

    private Cart cart = new Cart();


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


        //Send cart to ProductApdaptor
        recyclerViewProductList = findViewById(R.id.listProduct);
        recyclerViewProductList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProductList.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewProductList.setLayoutManager(gridLayoutManager);

        adapter=new ProductAdaptor(arrproducts, this);
        recyclerViewProductList.setAdapter(adapter);
    }
    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void getProduct(Integer productid) {
        this.cart.addProduct(productid) ;
        Toast.makeText(this, "Bạn đã thêm sản phẩm "+productid+ " vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
}