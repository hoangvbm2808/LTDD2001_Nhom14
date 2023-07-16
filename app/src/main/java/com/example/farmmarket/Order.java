package com.example.farmmarket;

import com.google.gson.annotations.SerializedName;

public class Order {
    private int id;

    private int user_id;
    private String create_day;
    private double total_amount;

    public Order(int order_id, int user_id, String create_day, double total_amount) {
        this.id = order_id;
        this.user_id = user_id;
        this.create_day = create_day;
        this.total_amount = total_amount;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreate_day() {
        return create_day;
    }

    public void setCreate_day(String create_day) {
        this.create_day = create_day;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

}
