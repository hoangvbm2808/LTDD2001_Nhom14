package com.example.farmmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import androidx.fragment.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;

    private Handler slideHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFab);
        myFab.setColorFilter(Color.WHITE);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);


        //Set menu item id placehoder == false
        MenuItem menuItem = bottomNavigationView.getMenu().getItem(2);
        menuItem.setEnabled(false);


        //Slide show();
        viewPager2 = findViewById(R.id.fragmentHome);

        //Add banner to slider
        List<SlideItem> sliderItem = new ArrayList<>();
        sliderItem.add(new SlideItem(R.drawable.banner1));
        sliderItem.add(new SlideItem(R.drawable.banner2));

        viewPager2.setAdapter(new SlideAdapter(sliderItem, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2); // Quanlity banner
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);



        // Distance between 2 banner
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            // Effect tranform
            @Override
            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//                page.setScaleY(0.4f + r + 0.1f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 3000);
            }
        });



    }


    //
//    private BottomNavigationView.OnItemSelectedListener
//            mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnItemSelectedListener(){
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            int id = item.getItemId();
//            switch (item.getItemId()) {
//                case 0:
//                    switchToHomeFragment();
//                    break;
//                case 1:
//                    switchToCategoryFragment();
//                    break;
//            }
//            return false;
//        }
//    };

    //Slide show
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 3000);
    }


//    public void switchToHomeFragment() {
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.fragmentContainerView, new HomeFragment()).commit();
//    }
//    public void switchToCategoryFragment() {
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.fragmentContainerView, new CategoryFragment()).commit();
//    }
//
//
//
//    private void loadFragment(Fragment fragment) {
//        // load fragment
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentContainerView, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

}