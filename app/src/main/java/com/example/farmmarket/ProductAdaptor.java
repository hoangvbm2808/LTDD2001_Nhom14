package com.example.farmmarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder>{
    ArrayList<Product> products;

    public ProductAdaptor(ArrayList<Product> foodDomains) {
        this.products = foodDomains;
    }
    @Override
    public ProductAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product,parent, false);
        return new ProductAdaptor.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdaptor.ViewHolder holder, int position) {
        holder.name.setText(products.get(position).getName());

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(products.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.productPic);
            name=itemView.findViewById(R.id.productName);
            price=itemView.findViewById(R.id.productPrice);
        }
    }
}
