package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.farmmarket.model.User;
import com.example.farmmarket.retrofit.RetrofitService;
import com.example.farmmarket.retrofit.UserApi;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FormLogin extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);



        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(username.getText()).trim();
                String pass = String.valueOf(password.getText()).trim();

                RequestQueue queue = Volley.newRequestQueue(FormLogin.this);

                String url = "http://10.17.37.245:9000/user/login";

                HashMap<String,String> params = new HashMap<String, String>();
                params.put("username",name);
                params.put("password",pass);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String first_name = (String) response.get("first_name");
                            String last_name = (String) response.get("last_name");
                            int user_id = (Integer) response.get("id");

                            Log.d("NAME", first_name);
                            Log.d("NAME", last_name);
                            Log.d("NAME", String.valueOf(user_id));

                            Intent gotoMainActivity = new Intent(FormLogin.this, MainActivity.class);
                            gotoMainActivity.putExtra("first_name", first_name);
                            gotoMainActivity.putExtra("last_name", last_name);
                            gotoMainActivity.putExtra("user_id", user_id);

                            startActivity(gotoMainActivity);
                            finish();


                        }catch (JSONException e){
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println(error.getMessage());
                        Toast.makeText(FormLogin.this, "Login fail", Toast.LENGTH_LONG).show();
                    }
                });

                queue.add(jsonObjectRequest);
            }
        });

    }
}