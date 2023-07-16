package com.example.farmmarket.retrofit;

import com.example.farmmarket.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderApi {
    @GET("/order/{id}")
    Call<List<Order>> getOrder(@Path("id") int user_id);
}
