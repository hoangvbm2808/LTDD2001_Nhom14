package com.example.farmmarket;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {
    Button btnBack;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btnBack = (Button) findViewById(R.id.btnBack);
        name = findViewById(R.id.name);

        String first_name = getIntent().getStringExtra("first_name");
        String last_name = getIntent().getStringExtra("last_name");
        Integer user_id = getIntent().getIntExtra("user_id", 0);


        name.setText(first_name+" "+last_name);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAccountActivity = new Intent(AccountActivity.this, MainActivity.class);
                goToAccountActivity.putExtra("first_name", first_name);
                goToAccountActivity.putExtra("last_name", last_name);
                goToAccountActivity.putExtra("user_id", user_id);
                finish();
                startActivity(goToAccountActivity);
            }
        });
    }


}
