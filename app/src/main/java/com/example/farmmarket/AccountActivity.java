package com.example.farmmarket;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {
    Button btnBack;
    Button btnCall_Onclick, btnMess_Onclick, btnPolicy_Onclick, btnRating_Onclick;
    String numPhone = "tel: 1900 1880";

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
        btnCall_Onclick = findViewById(R.id.btnCall);

        //CallPhone
        btnCall_Onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse(numPhone));
                startActivity(intentCall);
            }
        });

        //Messenger
        btnMess_Onclick = findViewById(R.id.btnMess);
        btnMess_Onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMess = new Intent(AccountActivity.this, com.example.farmmarket.MessActivity.class);
                startActivity(intentMess);
            }
        });
        //Policy
        btnPolicy_Onclick = findViewById(R.id.btnPolicy);
        btnPolicy_Onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPolicy = new Intent(AccountActivity.this, com.example.farmmarket.PolicyActivity.class);
                startActivity(intentPolicy);
            }
        });
        //Rating
        btnRating_Onclick = findViewById(R.id.btnRate);
        btnRating_Onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.farmmarket.RatingActivity ratingUsDialog = new com.example.farmmarket.RatingActivity(AccountActivity.this);
                ratingUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                ratingUsDialog.setCancelable(false);
                ratingUsDialog.show();
//                Intent intentRating = new Intent(MainActivity.this, PolicyActivity.class);
//                startActivity(intentRating);
            }
        });
    }


}
