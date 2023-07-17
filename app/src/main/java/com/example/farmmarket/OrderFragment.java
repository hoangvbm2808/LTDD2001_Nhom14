package com.example.farmmarket;

import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.farmmarket.retrofit.OrderApi;
import com.example.farmmarket.retrofit.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class OrderFragment extends Fragment {

    private int user_id;
    private RecyclerView recyclerViewOrder;
    RecyclerView.Adapter adapter;
    private List<Order> orders = new ArrayList<>();
    MainActivity activity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        orders = activity.getListOrder();


        View view = inflater.inflate(R.layout.fragment_order, container, false);




        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewOrder = view.findViewById(R.id.listOrder);
        recyclerViewOrder.setLayoutManager(linearLayoutManager);
        recyclerViewOrder.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1);
        recyclerViewOrder.setLayoutManager(gridLayoutManager);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        orders = activity.getListOrder();
        Log.d("SIZE_InOrderFg", String.valueOf(orders.size()));
        adapter=new OrderAdapter(orders, activity.getSize("a"));
        recyclerViewOrder.setAdapter(adapter);
    }
}