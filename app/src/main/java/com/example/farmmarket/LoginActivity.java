package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnRegister;
    ImageView ggLogin,fbLogin;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        ggLogin = findViewById(R.id.ggLogin);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String token = preferences.getString("token","");

        if (!token.equals("")) {
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

            String url = "http://192.168.1.4:9000/user/current-user";
            HashMap<String,String> params = new HashMap<String, String>();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (!response.equals(null)) {

                                    try {
                                        String first_name = (String) response.get("first_name");
                                        String last_name = (String) response.get("last_name");
                                        int user_id = (Integer) response.get("id");
                                        finish();
                                        Intent gotoMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                                        gotoMainActivity.putExtra("first_name", first_name);
                                        gotoMainActivity.putExtra("last_name", last_name);
                                        gotoMainActivity.putExtra("user_id", user_id);
                                        startActivity(gotoMainActivity);
                                        Log.e("Your Array Response", String.valueOf(user_id));
                                    }
                                    catch (JSONException e){
                                        e.printStackTrace();
                                        System.out.println(e.getMessage());
                                    }
                                } else {
                                    Log.e("Your Array Response", "Data Null");
                                }
                            }

                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                System.out.println(error.getMessage());
                                Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_LONG).show();
                            }
                        }) {

                //This is for Headers If You Needed
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", token);
                    return params;
                }
            };

            queue.add(request);
        }

        ggLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, FormLogin.class));

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, FormRegister.class));
            }
        });
    }

    void signInGoogle(){
        Intent signInGoogleItent = gsc.getSignInIntent();
        startActivityForResult(signInGoogleItent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }

        }
    }

    void navigateToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

}