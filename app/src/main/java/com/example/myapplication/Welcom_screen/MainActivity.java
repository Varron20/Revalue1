package com.example.myapplication.Welcom_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView heroImage = findViewById(R.id.topImage);
        TextView headlineText = findViewById(R.id.headlineText);
        TextView descriptionText = findViewById(R.id.descriptionText);

        animateHeroImage(heroImage);
        animateText(headlineText, 150);
        animateText(descriptionText, 250);

        Button getStartedBtn = findViewById(R.id.getStartedBtn);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Broading.class);
                startActivity(intent);
            }
        });
    }

    private void animateHeroImage(View view) {
        if (view == null) return;
        view.setAlpha(0f);
        view.setScaleX(1.08f);
        view.setScaleY(1.08f);
        view.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(400)
                .start();
    }

    private void animateText(View view, long delay) {
        if (view == null) return;
        view.setAlpha(0f);
        view.setTranslationY(24f);
        view.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay(delay)
                .setDuration(800)
                .start();
    }
}