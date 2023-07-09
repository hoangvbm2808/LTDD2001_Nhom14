package com.example.farmmarket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();

    private RecyclerView recyclerViewProductList;
    private RecyclerView.Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = view.findViewById(R.id.fragmentHome);
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
        return view;
    }
    //Slide show
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 3000);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewProductList = view.findViewById(R.id.listProduct);
        recyclerViewProductList.setLayoutManager(linearLayoutManager);
        recyclerViewProductList.setHasFixedSize(true);
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Rau Củ", "rau_bapcaitrang", 12000));
        products.add(new Product("Thịt", "heo_nacdam", 13000) );
        products.add(new Product("Thủy Sản", "tom_the",14000));
        products.add(new Product("Trứng", "trung_ga6", 16000));
        products.add(new Product("Trái cây", "nho_xanhkhonghatuc",17900));
        products.add(new Product("Thực Phẩm Đông Lạnh", "vien_ca2",20000));
        products.add(new Product("Thực Phẩm Sơ Chế", "thucpham_soche", 30000));
        products.add(new Product("Dầu Ăn, Gia Vị", "gia_vi", 21000));
        products.add(new Product("Gạo, Mì, Bún, Đậu", "me_trang_khong_vo", 22000));
        products.add(new Product("Thực Phẩm Khô", "thucuongyenmach",23000));
        products.add(new Product("Bánh Kẹo", "banh_bong_lan_solite_cuon_bo_sua_360g", 24000));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerViewProductList.setLayoutManager(gridLayoutManager);

        adapter=new ProductAdaptor(products);
        recyclerViewProductList.setAdapter(adapter);

    }
}