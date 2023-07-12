package com.example.farmmarket;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    CategoryFragment categoryFragment = new CategoryFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String first_name = getIntent().getStringExtra("first_name");
        String last_name = getIntent().getStringExtra("last_name");

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFab);
        myFab.setColorFilter(Color.WHITE);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);


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
               //     startActivity(new Intent(MainActivity.this, LoginActivity.class));
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



}