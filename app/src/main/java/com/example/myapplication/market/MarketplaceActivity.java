package com.example.myapplication.market;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.card.MaterialCardView;

public class MarketplaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(com.example.myapplication.R.layout.activity_marketplace);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.example.myapplication.R.id.marketplaceRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the GridLayout that contains the product cards
        GridLayout gridLayout = findViewById(com.example.myapplication.R.id.productGrid);
        
        if (gridLayout != null) {
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                View child = gridLayout.getChildAt(i);
                if (child instanceof MaterialCardView) {
                    child.setClickable(true);
                    child.setFocusable(true);
                    child.setOnClickListener(v -> openDetailsActivity());
                }
            }
        }
    }

    private void openDetailsActivity() {
        // DetailsActivity is in the same package/module now (com.example.a2)
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}
