package com.example.myapplication.Welcom_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Account.Login;
import com.example.myapplication.R;

public class Broading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView topBanner = findViewById(R.id.topBanner);
        View card1 = findViewById(R.id.card1);
        View card2 = findViewById(R.id.card2);
        View card3 = findViewById(R.id.card3);
        Button joinNowBtn = findViewById(R.id.joinNowBtn);

        animateEntrance(topBanner, 0);
        animateEntrance(card1, 100);
        animateEntrance(card2, 200);
        animateEntrance(card3, 300);
        animateEntrance(joinNowBtn, 400);

        joinNowBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Broading.this, Login.class);
            startActivity(intent);
        });

    }

    private void animateEntrance(View view, long delay) {
        if (view == null) {
            return;
        }
        view.setAlpha(0f);
        view.setTranslationY(40f);
        view.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(delay)
                .setDuration(500)
                .start();
    }
}
