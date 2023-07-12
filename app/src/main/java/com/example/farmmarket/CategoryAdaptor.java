package com.example.farmmarket;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {
    ArrayList<Category> categories;
    private ItemClickListener mItemListener;
    public CategoryAdaptor(ArrayList<Category> foodDomains, ItemClickListener itemClickListener) {
        this.categories = foodDomains;
        this.mItemListener = itemClickListener;
    }
    @Override
    public CategoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent, false);
        return new CategoryAdaptor.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, int position) {
        holder.title.setText(categories.get(position).getTitle());

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(categories.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(categories.get(position));
        });

    }
    public interface ItemClickListener {
        void onItemClick(Category category);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.categoryPic);
            title=itemView.findViewById(R.id.categoryName);
        }
    }


}
