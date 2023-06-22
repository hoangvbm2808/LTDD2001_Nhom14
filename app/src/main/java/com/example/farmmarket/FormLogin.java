package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormLogin extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
//                    Toast.makeText(FormLogin.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(FormLogin.this, MainActivity.class));
//                } else {
//                    Toast.makeText(FormLogin.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
//                }
                startActivity(new Intent(FormLogin.this, MainActivity.class));
            }
        });
    }
}