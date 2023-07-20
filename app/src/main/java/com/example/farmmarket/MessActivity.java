package com.example.farmmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MessActivity extends AppCompatActivity {
    Button btnMess_complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        Button btncomplete = findViewById(R.id.btnMess_complete);
        btncomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MessActivity.this,"Cảm ơn bạn đã gửi tin nhắn cho chúng tôi."
                       , Toast.LENGTH_SHORT).show();
                Toast.makeText(MessActivity.this,"Chúng tôi sẽ phản hồi sớm nhất ♡♡♡"
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }
}