package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormRegister extends AppCompatActivity implements View.OnClickListener {
    private Button registerBtn, btnBack;
    private EditText edtFName, edtLName, edtSDT, edtEmail, edtUserName, edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);

        registerBtn = findViewById(R.id.registerbtn);
        edtFName = findViewById(R.id.edtFName);
        edtLName = findViewById(R.id.edtLName);
        edtEmail = findViewById(R.id.edtEmail);
        edtSDT = findViewById(R.id.edtSDT);
        edtUserName = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        btnBack = findViewById(R.id.btnBack);
        registerBtn.setOnClickListener(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private boolean isValid(String email) {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
                "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
                "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))" +
                "\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:" +
                "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern patten = Pattern.compile(regex);
        Matcher matcher = patten.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onClick(View v) {
        String fName = edtFName.getText().toString().trim();
        String lName = edtLName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String sdt = edtSDT.getText().toString().trim();
        String username = edtUserName.getText().toString().trim();
        String password = edtPass.getText().toString().trim();

        Log.d("EMAIL", email);

        if (fName.isEmpty()) {
            edtFName.setError("Empty!!!");
        } else if (lName.isEmpty()) {
            edtLName.setError("Empty!!!");
        } else if (email.isEmpty()) {
            edtEmail.setError("Empty!!!");
        } else if (sdt.isEmpty()) {
            edtSDT.setError("Empty!!!");
        } else if (username.isEmpty()) {
            edtUserName.setError("Empty!!!");
        } else if (password.isEmpty()) {
            edtPass.setError("Empty!!!");
        } else {
            if (isValid(email)) {
                if (sdt.length() == 10 && sdt.startsWith("0")) {
                    if (password.length() > 5) {
                        Toast.makeText(FormRegister.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        RequestQueue queue = Volley.newRequestQueue(FormRegister.this);

                        String url = "http://192.168.1.7:9000/user/save";

                        HashMap<String,String> params = new HashMap<String, String>();
                        params.put("first_name", fName);
                        params.put("last_name", lName);
                        params.put("email", email);
                        params.put("phone",sdt);
                        params.put("username",username);
                        params.put("password",password);

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                url, new JSONObject(params), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                    Intent gotoFormLogin= new Intent(FormRegister.this, FormLogin.class);
                                    startActivity(gotoFormLogin);
                                    finish();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                System.out.println(error.getMessage());
                                Toast.makeText(FormRegister.this, "Register fail", Toast.LENGTH_LONG).show();
                            }
                        });

                        queue.add(jsonObjectRequest);
                    } else {
                        Toast.makeText(FormRegister.this, "Mat khau khong hop le.Vui long nhap lai", Toast.LENGTH_SHORT).show();
                        edtPass.setText("");
                    }

                } else {
                    Toast.makeText(FormRegister.this, "SDT khong hop le.Vui long nhap lai", Toast.LENGTH_SHORT).show();
                    edtSDT.setText("");
                }
            } else {
                Toast.makeText(FormRegister.this, "Email khong hop le.Vui long nhap lai", Toast.LENGTH_SHORT).show();
                edtEmail.setText("");
            }
        }
    }
}

