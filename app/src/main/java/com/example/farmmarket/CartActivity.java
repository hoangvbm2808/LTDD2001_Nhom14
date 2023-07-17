package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Calendar;
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
    Button btnPay;
    int user_id;
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("On_CREATE_Cart", "Createeeeeee");
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
        btnPay = findViewById(R.id.btnPay);
        user_id = getIntent().getIntExtra("user_id", 0);


        //Button back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(CartActivity.this, MainActivity.class);
                Log.d("USER_ID_IN_CART", String.valueOf(user_id));
                goToMainActivity.putExtra("user_id",user_id);
                finish();
                startActivity(goToMainActivity);
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

        Log.d("On_CREATE_Cart", "Stop");
    }

    @Override
    public void onPause() {
        Log.d("On_Pause_Cart", "Start");
        super.onPause();
        Log.d("On_Pause_Cart", "Stop");
    }

    @Override
    public void onResume() {
        Log.d("On_Resume_Cart", "Start");
        super.onResume();
        //Set PayText
        txtSubTotal.setText(vietnamdongFormat.format(getTotalPrice()));
        txtTax.setText(vietnamdongFormat.format(getTax()));
        txtTotal.setText(vietnamdongFormat.format(getTotalAmount()));

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(CartActivity.this);

                String url = "http://10.17.37.245:9000/order/save";

                HashMap<String,String> params = new HashMap<String, String>();

                LocalDate dateObj = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = dateObj.format(formatter);

                params.put("create_day", date);
                params.put("total_amount",String.valueOf(getTotalAmount()));
                params.put("user_id",String.valueOf(user_id));

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        products.removeAll(products);
                        cart.deleteCart();
                        adapter.notifyDataSetChanged();
                        layoutTotal.setVisibility(View.GONE);
                        layoutTax.setVisibility(View.GONE);
                        layoutSum.setVisibility(View.GONE);
                        layoutPay.setVisibility(View.GONE);
                        layoutcartNull.setVisibility(View.VISIBLE);
                        Toast.makeText(CartActivity.this, "Thanh toán thành công", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println(error.getMessage());
                        Toast.makeText(CartActivity.this, "Thanh toán thất bại", Toast.LENGTH_LONG).show();
                    }
                });

                queue.add(jsonObjectRequest);
            }
        });
        Log.d("On_Resume_Cart", "Stop");
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

    @Override
    public void onStop() {
        Log.d("ON_STOP_Cart", "Create");
        super.onStop();
        Log.d("ON_STOP_Cart", "STOPPPPPPPPPPP");
    }

}