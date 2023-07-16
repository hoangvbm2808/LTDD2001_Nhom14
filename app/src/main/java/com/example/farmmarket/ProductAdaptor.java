package com.example.farmmarket;

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
import java.util.Locale;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder>{
    ArrayList<Product> products;
    ProductRecycle mListener;



    public ProductAdaptor(ArrayList<Product> foodDomains, ProductRecycle mListener) {
        this.products = foodDomains;
        this.mListener = mListener;
    }
    @Override
    public ProductAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product,parent, false);
        return new ProductAdaptor.ViewHolder(inflate, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdaptor.ViewHolder holder, int position) {
        holder.name.setText(products.get(position).getName());

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(products.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        Locale vnd = new Locale("vi", "VN");
        Currency vietnamdong = Currency.getInstance(vnd);
        NumberFormat vietnamdongFormat = NumberFormat.getCurrencyInstance(vnd);


        String price = vietnamdongFormat.format(products.get(position).getPrice());
        holder.productid = products.get(position).getId();
        holder.price.setText(price);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        ImageView pic;
        Button btnAdd;
        ProductRecycle mListener;
        int productid;
        public ViewHolder(@NonNull View itemView, ProductRecycle mListener) {
            super(itemView);
            this.mListener = mListener;
            pic=itemView.findViewById(R.id.productPic);
            name=itemView.findViewById(R.id.productName);
            price=itemView.findViewById(R.id.productPrice);
            btnAdd=itemView.findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("INFO_PRODUCTID_INVIEWHOLDER", String.valueOf(productid));
                    mListener.getProduct(productid);
                }
            });
        }
    }

    interface ProductRecycle{
        void getProduct(Integer product_id);
    }
}
