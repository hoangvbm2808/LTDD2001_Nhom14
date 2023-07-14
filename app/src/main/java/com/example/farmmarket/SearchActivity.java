package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    String nameProduct;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FarmMarketDatabase db = new FarmMarketDatabase(SearchActivity.this);
        ArrayList<String> nameProducts = db.getAllNameProduct();
        autoCompleteTextView = findViewById(R.id.ac_text_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nameProducts);

        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameProduct = adapter.getItem(position);
                FarmMarketDatabase db = new FarmMarketDatabase(SearchActivity.this);
                ArrayList<Product> arrproduct = db.getProductByName(nameProduct);
                Intent goToListProduct = new Intent(getApplicationContext(), ProductActivity.class);
                goToListProduct.putParcelableArrayListExtra("listProducts", arrproduct);
                startActivity(goToListProduct);
            }
        });



    }
}