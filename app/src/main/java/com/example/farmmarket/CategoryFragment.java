package com.example.farmmarket;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = view.findViewById(R.id.listCategory);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        recyclerViewCategoryList.setHasFixedSize(true);
        ArrayList<Category> category = new ArrayList<>();
        category.add(new Category("Rau Củ", "rau_bapcaitrang"));
        category.add(new Category("Thịt", "heo_nacdam"));
        category.add(new Category("Thủy Sản", "tom_the"));
        category.add(new Category("Trứng", "trung_ga6"));
        category.add(new Category("Trái cây", "nho_xanhkhonghatuc"));
        category.add(new Category("Thực Phẩm Đông Lạnh", "vien_ca2"));
        category.add(new Category("Thực Phẩm Sơ Chế", "thucpham_soche"));
        category.add(new Category("Dầu Ăn, Gia Vị", "gia_vi"));
        category.add(new Category("Gạo, Mì, Bún, Đậu", "me_trang_khong_vo"));
        category.add(new Category("Thực Phẩm Khô", "thucuongyenmach"));
        category.add(new Category("Bánh Kẹo", "banh_bong_lan_solite_cuon_bo_sua_360g"));
        category.add(new Category("Nước Ngọt", "suatraicay_th_true_juice_chuoi"));
        category.add(new Category("Thực Phẩm Ăn Liền", "mixaohaohao_vitomxaochuangotgoi75g"));
        category.add(new Category("Rượu, Bia", "ruou_soju_goodday_huongvietquat"));
        category.add(new Category("Sức Khỏe, Làm Đẹp", "suckhoe_lamdep"));
        category.add(new Category("Mẹ Và Bé", "mevabe"));
        category.add(new Category("Chăm Sóc Cá Nhân", "chamsoc_canhan"));;
        category.add(new Category("Dụng Cụ Nhà Bếp", "mixaohaohao_vitomxaochuangotgoi75g"));
        category.add(new Category("Dụng Cụ Nhà Tắm", "ruou_soju_goodday_huongvietquat"));
        category.add(new Category("Trà, Cà Phê", "suckhoe_lamdep"));
        category.add(new Category("Gia Đình", "mevabe"));
        category.add(new Category("Đồ Chơi Trẻ Em", "chamsoc_canhan"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 4);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManager);

        adapter=new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);

    }



}