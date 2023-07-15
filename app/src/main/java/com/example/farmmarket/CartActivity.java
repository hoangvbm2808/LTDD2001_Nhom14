package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private RelativeLayout parent;
    private RecyclerView recyclerViewProductList;
    private CartAdaptor adapter;
    private Cart cart = new Cart();
    private FarmMarketDatabase db = new FarmMarketDatabase(this);
    private ArrayList<Product> products;
    Locale vnd = new Locale("vi", "VN");
    NumberFormat vietnamdongFormat = NumberFormat.getCurrencyInstance(vnd);

    LinearLayout layoutTotal;
    LinearLayout layoutTax;
    LinearLayout layoutSum;
    LinearLayout layoutPay;
    LinearLayout layoutcartNull;

    ImageView btnBack;
    TextView txtSubTotal;
    TextView txtTax;
    TextView txtTotal;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);



        parent = findViewById(R.id.parent);
        layoutTotal = findViewById(R.id.layoutTotal);
        layoutTax = findViewById(R.id.layoutTax);
        layoutSum = findViewById(R.id.layoutSum);
        layoutPay = findViewById(R.id.layoutPay);
        layoutcartNull = findViewById(R.id.layoutcartNull);
        btnBack = findViewById(R.id.btnBack);
        txtSubTotal = findViewById(R.id.txtSubTotal);
        txtTax = findViewById(R.id.txtTax);
        txtTotal = findViewById(R.id.txtTotal);


        //Button back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        cart.showCart(); DEBUG


//        SET LAYOUT CART and GET CART
        if (this.cart.checkCartNull()){
            Log.d("INFO_CART", "CART_IS_NULL");
            layoutTotal.setVisibility(View.GONE);
            layoutTax.setVisibility(View.GONE);
            layoutSum.setVisibility(View.GONE);
            layoutPay.setVisibility(View.GONE);
        }
        else {
            Log.d("INFO_CART", "CART_IS_EXIST");
            layoutcartNull.setVisibility(View.GONE);
            this.products = db.getProductById(this.cart.getCart().keySet());
        }




        //Dump info Cart to Cart activity from CartAdaptor and Set layout View Holder
        recyclerViewProductList = findViewById(R.id.listProduct);
        recyclerViewProductList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProductList.setHasFixedSize(false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerViewProductList.setLayoutManager(gridLayoutManager);

        adapter=new CartAdaptor(this.products,this);
        recyclerViewProductList.setAdapter(adapter);
        adapter.setOnItemDeleteListener(new CartAdaptor.CartRecycle() {
            @Override
            public void onItemDelete(int position) {
                products.remove(position);
                adapter.notifyItemRemoved(position);
                if (products.size() == 0) {
                    //Set Layout when delete over product in cart and delete Cart
                    layoutTotal.setVisibility(View.GONE);
                    layoutTax.setVisibility(View.GONE);
                    layoutSum.setVisibility(View.GONE);
                    layoutPay.setVisibility(View.GONE);
                    layoutcartNull.setVisibility(View.VISIBLE);
                    cart.deleteCart();
                }
                //Set PayText when click (delete)
                txtSubTotal.setText(vietnamdongFormat.format(getTotalPrice()));
                txtTax.setText(vietnamdongFormat.format(getTax()));
                txtTotal.setText(vietnamdongFormat.format(getTotalAmount()));
            }
            @Override
            public void onItemClick(int position) {
                adapter.notifyItemChanged(position);
                //Set PayText when click (add or minus)
                txtSubTotal.setText(vietnamdongFormat.format(getTotalPrice()));
                txtTax.setText(vietnamdongFormat.format(getTax()));
                txtTotal.setText(vietnamdongFormat.format(getTotalAmount()));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Set PayText
        txtSubTotal.setText(vietnamdongFormat.format(getTotalPrice()));
        txtTax.setText(vietnamdongFormat.format(getTax()));
        txtTotal.setText(vietnamdongFormat.format(getTotalAmount()));
    }

    public Float getTotalPrice() {
        float total = 0;
        HashMap<Integer, Integer> carthm = cart.getCart();
        if (carthm != null) {
            Log.d("INFO_CARTHM_INIF", String.valueOf(carthm));
            for (Integer c: carthm.keySet()) {
                total += db.getPriceProductById(c) * Float.parseFloat(String.valueOf(carthm.get(c)));
            }
            return total;
        }
        return total;
    }
    public Float getTax() {

        float tax = (float) (getTotalPrice() * 0.08);
        return tax;
    }
    public Float getTotalAmount() {

        return getTotalPrice() + getTax();
    }

}