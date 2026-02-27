package com.example.myapplication.Welcom_screen;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homeRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialCardView uploadCard = findViewById(R.id.uploadCard);
        if (uploadCard != null) {
            uploadCard.setClickable(true);
            uploadCard.setFocusable(true);

            uploadCard.setOnClickListener(v -> openUploadItem());
        }

        MaterialCardView browseCard = findViewById(R.id.browseCard);
        if (browseCard != null) {
            browseCard.setClickable(true);
            browseCard.setFocusable(true);
            browseCard.setOnClickListener(v -> openMarketplace());
        }

        MaterialCardView myItemsCard = findViewById(R.id.myItemsCard);
        if (myItemsCard != null) {
            myItemsCard.setClickable(true);
            myItemsCard.setFocusable(true);
            myItemsCard.setOnClickListener(v -> openMyItems());
        }

        // Bottom Navigation Logic
        ImageView navNotification = findViewById(R.id.navNotification);
        if (navNotification != null) {
            navNotification.setOnClickListener(v -> openNotification());
        }

        ImageView navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> openProfile());
        }

        ImageView navChat = findViewById(R.id.navChat);
        if (navChat != null) {
            navChat.setOnClickListener(v -> openMarketplace());
        }
    }

    private void openUploadItem() {Intent intent = new Intent();
        ComponentName componentName =
                new ComponentName("com.example.myapplication", "com.example.myapplication.market.UploadItemActivity");
        intent.setComponent(componentName);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "upload item screen not available yet.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openMarketplace() {
        Intent intent = new Intent();
        ComponentName componentName =
                new ComponentName("com.example.myapplication", "com.example.myapplication.market.MarketplaceActivity");
        intent.setComponent(componentName);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Marketplace screen not available yet.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openMyItems() {
        Intent intent = new Intent();
        // This assumes ClothingListActivity is the name of the activity displaying activity_clothing_list.xml
        // and that it is located in the same package/module as marketplace activity (app.2).
        ComponentName componentName =
                new ComponentName("com.example.myapplication", "com.example.a2.ClothingListActivity");
        intent.setComponent(componentName);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Clothing List screen not available yet.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openNotification() {
        Intent intent = new Intent();
        ComponentName componentName =
                new ComponentName("com.example.myapplication", "com.example.myapplication.Profile.NotificationActivity");
        intent.setComponent(componentName);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Notification screen not available yet.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openProfile() {
        Intent intent = new Intent();
        ComponentName componentName =
                new ComponentName("com.example.myapplication", "com.example.myapplication.Profile.ProfileActivity");
        intent.setComponent(componentName);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Profile screen not available yet.", Toast.LENGTH_SHORT).show();
        }
    }
}
