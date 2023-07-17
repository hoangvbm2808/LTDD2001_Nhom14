package com.example.farmmarket;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.farmmarket.retrofit.OrderApi;
import com.example.farmmarket.retrofit.RetrofitService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
    OrderFragment orderFragment = new OrderFragment();

    ImageView btnSearch;
    ImageView btnCart;
    String first_name;
    String last_name;
    int user_id ;
    List<Order> orders;
    public int qrCode;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("On_Create_Main", "Create");
        super.onCreate(savedInstanceState);
        orders = new ArrayList<>();
        Log.d("SIZE_ORDER_CreateMain", String.valueOf(getSize("Create")));


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount accountGoogle = GoogleSignIn.getLastSignedInAccount(this);
        if (accountGoogle!=null) {
            first_name = accountGoogle.getDisplayName();
        }

        first_name = getIntent().getStringExtra("first_name");
        last_name = getIntent().getStringExtra("last_name");
        user_id = getIntent().getIntExtra("user_id", 0);
        Log.d("USER_ID_IN_MAIN", String.valueOf(user_id));
//        Retrofit retrofitCreate = new RetrofitService().getRetrofit();
//
//        OrderApi orderApiCreate = retrofitCreate.create(OrderApi.class);
//
//        Call<List<Order>> call = orderApiCreate.getOrder(user_id);
//        call.enqueue(new Callback<List<Order>>() {
//            @Override
//            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
//
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(), response.code() +"On response", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    setListOrderEmpty("CREATE");
//                    List<Order> createOrders = new ArrayList<>();
//                    List<Order> listOrderResponse = response.body();
//                    Log.d("Order_Create_BeforeFor", String.valueOf(getSize("Create")));
//                    for (Order order: listOrderResponse){
//                        int id = order.getId();
//                        int user_id = order.getUser_id();
//                        String create_day = order.getCreate_day();
//                        double amount = order.getTotal_amount();
//                        createOrders.add(new Order(id, user_id, create_day, amount));
//                    }
//                    setListOrder(createOrders, "CREATE");
//
//                    Log.d("Order_Create_AfterFor", String.valueOf(getSize("Create")));
//                }
//
//
//            }
//            @Override
//            public void onFailure(Call<List<Order>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage()+"On Fail", Toast.LENGTH_SHORT).show();
//                Log.d("Request fail", String.valueOf(getSize("Request fail")));
//            }
//        });
        setContentView(R.layout.activity_main);

        btnCart = findViewById(R.id.btnCart);


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFab);
        myFab.setColorFilter(Color.WHITE);

        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scanner();
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setBackground(null);


        //Search button
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearchActivity = new Intent(MainActivity.this, SearchActivity.class);
                goToSearchActivity.putExtra("user_id", user_id);
                startActivity(goToSearchActivity);
            }
        });

        //Cart button

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCartActivity = new Intent(MainActivity.this, CartActivity.class);
                goToCartActivity.putExtra("user_id", user_id);
                finish();
                startActivity(goToCartActivity);
            }
        });

        //Set menu item id placeholder == false
        MenuItem menuItem = bottomNavigationView.getMenu().getItem(2);
        menuItem.setEnabled(false);


        //Create category in database (Run once in lifecycle)
        SharedPreferences  prefs = getPreferences(this.MODE_PRIVATE);
        if(prefs.getBoolean("firstRun", true)) {
            FarmMarketDatabase db = new FarmMarketDatabase(MainActivity.this);
            db.addCategory();
            db.addProduct();
            prefs.edit().putBoolean("firstRun", false).commit();
        }


        //Switch fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, homeFragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuHome ) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, homeFragment).commit();
                    return true;
                }
                if (item.getItemId() == R.id.menuCategory ) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, categoryFragment).commit();
                    return true;
                }
                if (item.getItemId() == R.id.menuInvoice ) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, orderFragment).commit();
                    //Send user_id to fragment
                    return true;
                }
                if (item.getItemId() == R.id.menuAccount ) {
                    Intent goToAccountActivity = new Intent(MainActivity.this, AccountActivity.class);
                    goToAccountActivity.putExtra("first_name", first_name);
                    goToAccountActivity.putExtra("last_name", last_name);
                    goToAccountActivity.putExtra("user_id", user_id);
                    finish();
                    startActivity(goToAccountActivity);

                    return true;
                }
                return false;
            }
        });
        Log.d("On_Create_Main", "Stop");
    }

    public int getSize(String name) {
        Log.d("CALL_GetSize", name);
        return orders.size();
    }

    public List<Order> getListOrder() {
        return this.orders;
    }

    public List<Order> setListOrder(List<Order> listOrder, String name) {
        Log.d("CALL_SET_LIST_ORDER", name);
        this.orders = listOrder;
        return orders;
    }

    public List<Order> setListOrderEmpty(String name) {
        Log.d("CALL_SET_LIST_ORDER_EMPTY", name);
        this.orders.clear();
        return orders;
    }


    @Override
    public void onResume(){
        Log.d("On_Resume_Main", "Create");
        super.onResume();
        //Request order
        Retrofit retrofitResume = new RetrofitService().getRetrofit();

        OrderApi orderApiResume = retrofitResume.create(OrderApi.class);

        Call<List<Order>> call = orderApiResume.getOrder(user_id);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code() +"On response", Toast.LENGTH_SHORT).show();
                }

                setListOrderEmpty("RESUME");
                List<Order> rsmOrders = new ArrayList<>();
                List<Order> listOrderResponse = response.body();
                Log.d("Order_OnResume_BeforeFor", String.valueOf(getSize("Resume")));
                for (Order order: listOrderResponse){
                    int id = order.getId();
                    int user_id = order.getUser_id();
                    String create_day = order.getCreate_day();
                    double amount = order.getTotal_amount();
                    rsmOrders.add(new Order(id, user_id, create_day, amount));
                }
                setListOrder(rsmOrders, "RESUME");
                Log.d("Order_OnResume_AfterFor", String.valueOf(getSize("Resume")));
            }
            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"On Fail", Toast.LENGTH_SHORT).show();
                Log.d("Request fail", String.valueOf(getSize("Resume")));
            }
        });
        Log.d("On_Resume_Main", "Stop");
    }


    public void Scanner(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Nhấn nút tăng/giảm âm lượng để bật Flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(StartScan.class);
        launcher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            try {
                qrCode = Integer.parseInt(result.getContents());
                FarmMarketDatabase db = new FarmMarketDatabase(MainActivity.this);
                ArrayList<Product> arrproduct = db.getProductById(qrCode);
                Intent goToListProduct = new Intent(getApplicationContext(), ProductActivity.class);
                goToListProduct.putParcelableArrayListExtra("listProducts", arrproduct);
                startActivity(goToListProduct);
            } catch (Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Sản phẩm không tồn tại");
                builder.setMessage("Quay lại !!!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }


        }
    });

}