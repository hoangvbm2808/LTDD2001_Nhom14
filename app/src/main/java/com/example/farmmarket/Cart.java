package com.example.farmmarket;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {
    private static HashMap<Integer,Integer> cart;

    public Cart() {}
    public Cart(HashMap<Integer,Integer> cart) {
        Cart.cart = cart;
    }

    HashMap<Integer, Integer> addProduct(Integer productId) {
        if (cart != null){
//            Log.d("INFO_CART_INCART","Add product when cart EXIST");
            if (cart.containsKey(productId)) {
                Integer currrentNum = cart.get(productId);
                cart.put(productId,currrentNum+1);
//                Log.d("INFO_CART_INCART","Add product success");
            }
            else {
                cart.put(productId,1);
//                Log.d("INFO_CART_INCART","Add product fail");
            }
        }
        else {
//            Log.d("INFO_CART_INCART","Add product when cart NULL");
            cart = new HashMap<>();
            cart.put(productId,1);
        }
        return cart;
    }

    HashMap<Integer, Integer> minusProduct(Integer productId) {
        if (cart.containsKey(productId)) {
            Integer currrentNum = cart.get(productId);
            if (currrentNum == 1){
                cart.remove(productId);
            }
            else {
                cart.put(productId,currrentNum-1);
            }
        }
        return cart;
    }

    HashMap<Integer, Integer> getCart() {
        return cart;
    }

    void showCart() {
        if(cart != null ){
            for(Integer c: cart.keySet()){
                Log.d("INFO_CART_INCART",String.valueOf(c)+":"+String.valueOf(cart.get(c)));
            }
        }
    }

    boolean checkCartNull() {
        return cart == null;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + cart +
                '}';
    }

    Integer getNum(Integer product_id) {
        return cart.get(product_id);
    }

    HashMap<Integer, Integer> removeProduct(Integer productId) {
        cart.remove(productId);
        return cart;
    }

    void deleteCart(){
        cart =null;
    }
}
