package com.example.farmmarket;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {
    private static HashMap<Integer,Integer> cart = new HashMap<>();

    public Cart() {
        this.cart = cart;
    }
    public Cart(HashMap<Integer,Integer> cart) {
        this.cart = cart;
    }

    HashMap<Integer, Integer> addProduct(Integer productId) {
        if (cart.containsKey(productId)) {
            Integer currrentNum = cart.get(productId);
            cart.put(productId,currrentNum+1);
        }
        else {
            cart.put(productId,1);
        }
        return this.cart;
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
        return this.cart;
    }

    HashMap<Integer,Integer> getCart() {
        return cart;
    }

    void showCart() {
        if(this.cart != null ){
            for(Integer c: cart.keySet()){
                Log.d("INFO_CART_INCART",String.valueOf(c)+":"+String.valueOf(cart.get(c)));
            }
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + cart +
                '}';
    }
}
