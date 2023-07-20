package com.example.farmmarket;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class RatingActivity extends Dialog {

    private float userRate = 0;

    public RatingActivity(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rating);
        final AppCompatButton ratingBtn = findViewById(R.id.btnRatingNow);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final ImageView ratingImg = findViewById(R.id.ratingImg);

        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 1) {
                    ratingImg.setImageResource(R.drawable.rating_1);
                } else if (rating <= 2) {
                    ratingImg.setImageResource(R.drawable.rating_2);
                } else if (rating <= 3) {
                    ratingImg.setImageResource(R.drawable.rating_3);
                } else if (rating <= 4) {
                    ratingImg.setImageResource(R.drawable.rating_4);
                } else if (rating <= 5) {
                    ratingImg.setImageResource(R.drawable.rating_5);
                }
                // animate emoji
                animateImg(ratingImg);

                userRate = rating;
            }
        });

        }
        private void animateImg(ImageView ratingImg){
            ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f, 0, 1f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration(200);
            ratingImg.startAnimation(scaleAnimation);
        }
}
