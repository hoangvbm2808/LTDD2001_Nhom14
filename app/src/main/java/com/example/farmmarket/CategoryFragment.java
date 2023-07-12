package com.example.farmmarket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryFragment extends Fragment{
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView.Adapter adapter;
    private ArrayList<Category> category = new ArrayList<>();;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = view.findViewById(R.id.listCategory);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        recyclerViewCategoryList.setHasFixedSize(true);

        category.add(new Category("Rau củ", "rau_bapcaitrang"));
        category.add(new Category("Thịt", "heo_nacdam"));
        category.add(new Category("Thủy sản", "tom_the"));
        category.add(new Category("Trứng", "trung_ga6"));
        category.add(new Category("Trái cây", "nho_xanhkhonghatuc"));
        category.add(new Category("Thực phẩm đông lạnh", "vien_ca2"));
        category.add(new Category("Thực phẩm chế biến", "thucpham_soche"));
        category.add(new Category("Dầu ăn, gia vị", "gia_vi"));
        category.add(new Category("Gạo, đậu, bột", "thucuongyenmach"));
        category.add(new Category("Mì, miến, phở, nui khô", "mien_kho_ottogi"));
        category.add(new Category("Mì, phở ăn liền ", "mi_hhtomchua"));
        category.add(new Category("Bánh kẹo", "banh_bong_lan_solite_cuon_bo_sua_360g"));
        category.add(new Category("Nước ngọt, nước suối", "suatraicay_th_true_juice_chuoi"));
        category.add(new Category("Rượu, bia", "ruou_soju_goodday_huongvietquat"));
        category.add(new Category("Sức khỏe, làm đẹp", "suckhoe_lamdep"));
        category.add(new Category("Mẹ và bé", "mevabe"));
        category.add(new Category("Chăm sóc cá nhân", "suatam_romanoattude"));;
        category.add(new Category("Chăm sóc quần áo", "xitvai_downy1"));
        category.add(new Category("Chăm sóc nhà cửa ", "xitphong_glade"));
        category.add(new Category("Trà, cà phê", "caphe_highlands_moka"));
        category.add(new Category("Gia đình", "mevabe"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 4);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManager);
        adapter=new CategoryAdaptor(category, new CategoryAdaptor.ItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                FarmMarketDatabase db = new FarmMarketDatabase(getActivity());
                ArrayList<Product> arrproduct = db.getProductByCateId(category.getId());
                Log.d("INFO_CATEGORY", String.valueOf(category.getId()));
                Intent goToListProduct = new Intent(getActivity(), ProductFragment.class);
                goToListProduct.putParcelableArrayListExtra("listProducts", arrproduct);
                startActivity(goToListProduct);
            }
        });
        recyclerViewCategoryList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}