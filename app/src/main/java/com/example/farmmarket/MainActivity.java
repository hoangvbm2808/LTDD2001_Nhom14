package com.example.farmmarket;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.farmmarket.retrofit.OrderApi;
import com.example.farmmarket.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
    OrderFragment orderFragment = new OrderFragment();

    ImageView btnSearch;
    ImageView btnCart;
    String first_name;
    String last_name;
    int user_id ;
    List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_name = getIntent().getStringExtra("first_name");
        last_name = getIntent().getStringExtra("last_name");
        user_id = getIntent().getIntExtra("user_id", 0);



        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFab);
        myFab.setColorFilter(Color.WHITE);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);


        //Search button
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearchActivity = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(goToSearchActivity);
            }
        });

        //Cart button
        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCartActivity = new Intent(MainActivity.this, CartActivity.class);
                startActivity(goToCartActivity);
            }
        });

        //Set menu item id placeholder == false
        MenuItem menuItem = bottomNavigationView.getMenu().getItem(2);
        menuItem.setEnabled(false);


        //Create category in database (Run once in lifecycle)
        SharedPreferences  prefs = getPreferences(this.MODE_PRIVATE);
        if(prefs.getBoolean("firstRun", true)) {
            FarmMarketDatabase db = new FarmMarketDatabase(MainActivity.this);
            db.addCategory();
            db.addProduct();
            prefs.edit().putBoolean("firstRun", false).commit();
        }


        //Switch fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, homeFragment).commit();


        //Request order
        Retrofit retrofit = new RetrofitService().getRetrofit();

        OrderApi orderApi = retrofit.create(OrderApi.class);

        Call<List<Order>> call = orderApi.getOrder(user_id);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code() +"On response", Toast.LENGTH_SHORT).show();
                }

                List<Order> listOrderResponse = response.body();
                for (Order order: listOrderResponse){
                    int id = order.getId();
                    int user_id = order.getUser_id();
                    String create_day = order.getCreate_day();
                    double amount = order.getTotal_amount();
                    orders.add(new Order(id, user_id, create_day, amount));
                }
            }
            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"On Fail", Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuHome ) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, homeFragment).commit();
                    return true;
                }
                if (item.getItemId() == R.id.menuCategory ) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, categoryFragment).commit();
                    return true;
                }
                if (item.getItemId() == R.id.menuInvoice ) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, orderFragment).commit();
                    //Send user_id to fragment
                    return true;
                }
                if (item.getItemId() == R.id.menuAccount ) {
                    Intent goToAccountActivity = new Intent(MainActivity.this, AccountActivity.class);
                    goToAccountActivity.putExtra("first_name", first_name);
                    goToAccountActivity.putExtra("last_name", last_name);
                    startActivity(goToAccountActivity);

                    return true;
                }
                return false;
            }
        });
    }

    public List<Order> getOrders() {
        return orders;
    }
    public int getSize() {
        return orders.size();
    }


}