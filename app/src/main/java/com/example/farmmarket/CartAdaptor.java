package com.example.farmmarket;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {

    private ArrayList<Product> listProduct;
    private HashMap<Integer, Integer> cart;
    private FarmMarketDatabase db;


    public CartAdaptor(HashMap<Integer, Integer> cart) {

        if (cart != null) {
            this.cart = cart;
            //Debug
            for(Integer c: cart.keySet()){
                String msgerr = String.valueOf(c)+":"+String.valueOf(cart.get(c));
                Log.d("INFO_HashMap_Cart",msgerr);
            }
            this.listProduct = db.getProductById(cart.keySet());

            //Debug
            listProduct.forEach(product -> {
                Log.d("INFO_HashMap_Cart",product.getName());
            });
        }
    }

    @NonNull
    @Override
    public CartAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent, false);
        return new CartAdaptor.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(listProduct.get(position).getName());

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(listProduct.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        Locale vnd = new Locale("vi", "VN");
        Currency vietnamdong = Currency.getInstance(vnd);
        NumberFormat vietnamdongFormat = NumberFormat.getCurrencyInstance(vnd);


        String price = vietnamdongFormat.format(listProduct.get(position).getPrice());


        holder.price.setText(price);

        holder.num.setText(this.cart.get(listProduct.get(position).getId()));

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.containsKey(listProduct.get(position).getId())) {
                    Integer currrentNum = cart.get(listProduct.get(position).getId());
                    cart.put(listProduct.get(position).getId(),currrentNum+1);
                }
                else {
                    cart.put(listProduct.get(position).getId(),1);
                }
                holder.num.setText(cart.get(listProduct.get(position).getId()));
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.containsKey(listProduct.get(position).getId())) {
                    Integer currrentNum = cart.get(listProduct.get(position).getId());
                    if (currrentNum == 1){
                        cart.remove(listProduct.get(position).getId());
                        listProduct.remove(position);
                    }
                    else {
                        cart.put(listProduct.get(position).getId(),currrentNum-1);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView num;
        TextView price;
        TextView totalPrice;
        ImageView pic;
        ImageView btnAdd;
        ImageView btnMinus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.imgProduct);
            num=itemView.findViewById(R.id.txtNum);
            name=itemView.findViewById(R.id.txtNameProduct);
            price=itemView.findViewById(R.id.priceItem);
            btnAdd=itemView.findViewById(R.id.btnAdd);
            btnMinus=itemView.findViewById(R.id.btnMinus);
            totalPrice=itemView.findViewById(R.id.totalPrice);
        }
    }
}
