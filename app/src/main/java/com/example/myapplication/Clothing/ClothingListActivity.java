package com.example.myapplication.Clothing;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ClothingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_list);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            // Navigate back to HomeActivity in module app.3
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName("com.example.myapplication", "com.example.a3.HomeActivity");
            intent.setComponent(componentName);
            // Clearing the task stack so the user can't go "back" to this screen from Home
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            try {
                startActivity(intent);
                finish(); // Optional: finish this activity
            } catch (Exception e) {
                // Fallback if HomeActivity is not found (e.g., running this module standalone)
                Toast.makeText(this, "Could not return to Home.", Toast.LENGTH_SHORT).show();
                finish(); // Just close this activity
            }
        });
    }
}
