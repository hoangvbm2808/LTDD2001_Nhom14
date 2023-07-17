package com.example.farmmarket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {

    ArrayList<Product> listProduct;
    private Context context;
    Cart cart = new Cart();

    private CartRecycle mListener;
    Locale vnd = new Locale("vi", "VN");
    NumberFormat vietnamdongFormat = NumberFormat.getCurrencyInstance(vnd);
    FarmMarketDatabase db;


    public interface CartRecycle{
        void onItemDelete(int position);
        void onItemClick(int positon);

    }

    public void setOnItemDeleteListener(CartRecycle clickDelete) {
        mListener = clickDelete;
    }
    public CartAdaptor(ArrayList<Product> listProduct,Context context) {
        this.cart = new Cart();
        this.listProduct = listProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent, false);
        this.context = parent.getContext();
        db = new FarmMarketDatabase(this.context);
        return new CartAdaptor.ViewHolder(inflate, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(listProduct.get(position).getName());
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(listProduct.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        String price = vietnamdongFormat.format(listProduct.get(position).getPrice());


        holder.price.setText(price);

        holder.num.setText(String.valueOf(this.cart.getNum(listProduct.get(position).getId())));


        float total = listProduct.get(position).getPrice() * this.cart.getNum(listProduct.get(position).getId());
        holder.totalPrice.setText(vietnamdongFormat.format(total));

    }

    @Override
    public int getItemCount() {
        if (listProduct == null) {
            return 0;
        }
        else {
            return listProduct.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView num;
        TextView price;
        TextView totalPrice;
        ImageView pic;
        ImageView btnAdd;
        ImageView btnMinus;
        Button btnDeleteItem;

        CartRecycle listener;



        public ViewHolder(@NonNull View itemView, CartRecycle mListener) {
            super(itemView);
            pic=itemView.findViewById(R.id.imgProduct);
            num=itemView.findViewById(R.id.txtNum);
            name=itemView.findViewById(R.id.textCreateDay);
            price=itemView.findViewById(R.id.priceItem);
            btnAdd=itemView.findViewById(R.id.btnAdd);
            btnMinus=itemView.findViewById(R.id.btnMinus);
            totalPrice=itemView.findViewById(R.id.txtAmount);
            btnDeleteItem=itemView.findViewById(R.id.btnDeleteItem);
            listener=mListener;
            itemView.findViewById(R.id.btnDeleteItem).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cart.removeProduct(listProduct.get(getAdapterPosition()).getId());
                    mListener.onItemDelete(getAdapterPosition());
                    Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cart.addProduct(listProduct.get(getAdapterPosition()).getId());
                    mListener.onItemClick(getAdapterPosition());
                }
            });
            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cart.getNum(listProduct.get(getAdapterPosition()).getId()) > 1){
                        cart.minusProduct(listProduct.get(getAdapterPosition()).getId());
                        mListener.onItemClick(getAdapterPosition());
                    }
                    else{
                        cart.removeProduct(listProduct.get(getAdapterPosition()).getId());
                        mListener.onItemDelete(getAdapterPosition());
                        Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    public Float getTotalPrice() {
        float total = 0;
        HashMap<Integer, Integer> carthm = cart.getCart();
        Log.d("CartHM_In_getTotalPrice", String.valueOf(carthm));
        if (carthm != null) {
            for (Integer c: carthm.keySet()) {
                total += db.getPriceProductById(c) * Float.parseFloat(String.valueOf(carthm.get(c)));
            }
            return total;
        }
        return total;
    }

}
