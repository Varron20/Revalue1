package com.example.myapplication.Welcom_screen;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class SplashOptionsActivity extends AppCompatActivity {

    private Button recycledClothesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_options);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splashRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView logoImage = findViewById(R.id.logoImage);
        Button recycledClothesButton = findViewById(R.id.recycledClothesButton);
        Button decorButton = findViewById(R.id.decorButton);

        runLogoAnimation(logoImage);

        recycledClothesButton.setOnClickListener(v -> {
            Intent intent = new Intent(SplashOptionsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        decorButton.setOnClickListener(v ->
                Toast.makeText(this, "Decors coming soon!", Toast.LENGTH_SHORT).show()
        );
    }

    private void runLogoAnimation(ImageView logoImage) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(logoImage, "alpha", 0f, 1f);
        fadeIn.setDuration(2000);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(logoImage, "scaleX", 0.7f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(logoImage, "scaleY", 0.7f, 1f);
        scaleX.setDuration(900);
        scaleY.setDuration(900);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(fadeIn, scaleX, scaleY);
        animatorSet.start();
    }

}

