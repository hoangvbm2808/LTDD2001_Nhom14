package com.example.farmmarket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CategoryFragment extends Fragment{
    private RecyclerView recyclerViewCategoryList;
    private ArrayList<Category> category = new ArrayList<>();
    private FarmMarketDatabase db = new FarmMarketDatabase(getActivity());

    private Cart cart = new Cart();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = view.findViewById(R.id.listCategory);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        recyclerViewCategoryList.setHasFixedSize(true);

        if(category.isEmpty()) {
            category.add(new Category(1,"Rau củ", "rau_bapcaitrang"));
            category.add(new Category(2,"Thịt", "heo_nacdam"));
            category.add(new Category(3,"Thủy sản", "tom_the"));
            category.add(new Category(4,"Trứng", "trung_ga6"));
            category.add(new Category(5,"Trái cây", "nho_xanhkhonghatuc"));
            category.add(new Category(6,"Thực phẩm đông lạnh", "vien_ca2"));
            category.add(new Category(7,"Thực phẩm chế biến", "thucpham_soche"));
            category.add(new Category(8,"Dầu ăn, gia vị", "gia_vi"));
            category.add(new Category(9,"Gạo, đậu, bột", "thucuongyenmach"));
            category.add(new Category(10,"Mì, miến, phở, nui khô", "mien_kho_ottogi"));
            category.add(new Category(11,"Mì, phở ăn liền ", "mi_hhtomchua"));
            category.add(new Category(12,"Bánh kẹo", "banh_bong_lan_solite_cuon_bo_sua_360g"));
            category.add(new Category(13,"Nước ngọt, nước suối", "suatraicay_th_true_juice_chuoi"));
            category.add(new Category(14,"Rượu, bia", "ruou_soju_goodday_huongvietquat"));
            category.add(new Category(15,"Sức khỏe, làm đẹp", "suckhoe_lamdep"));
            category.add(new Category(16,"Mẹ và bé", "mevabe"));
            category.add(new Category(17,"Chăm sóc cá nhân", "suatam_romanoattude"));;
            category.add(new Category(18,"Chăm sóc quần áo", "xitvai_downy1"));
            category.add(new Category(19,"Chăm sóc nhà cửa ", "xitphong_glade"));
            category.add(new Category(20,"Trà, cà phê", "caphe_highlands_moka"));
            category.add(new Category(21,"Gia đình", "mevabe"));
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 4);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManager);


        RecyclerView.Adapter adapter=new CategoryAdaptor(category, new CategoryAdaptor.ItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                FarmMarketDatabase db = new FarmMarketDatabase(getActivity());
                ArrayList<Product> arrproduct = db.getProductByCateId(category.getId());
//               DEBUG:  Log.d("INFO_CATEGORY_INCATEGORYFG", String.valueOf(category.getId()));
                Intent goToListProduct = new Intent(getActivity(), ProductActivity.class);
                goToListProduct.putParcelableArrayListExtra("listProducts", arrproduct);
                startActivity(goToListProduct);
            }
        });
        recyclerViewCategoryList.setAdapter(adapter);
//        Log.d("INFO_SIZE_ARRAYLIST", String.valueOf(this.category.size()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}