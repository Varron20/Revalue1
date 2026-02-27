package com.example.myapplication.Account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.Welcom_screen.HomeActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button loginButton = findViewById(R.id.loginButton);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        TextView guestText = findViewById(R.id.guestText);
        TextView forgotPasswordText = findViewById(R.id.forgotPasswordText);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, HomeActivity.class);
            startActivity(intent);
        });

        createAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, CreateAccountActivity.class);
            startActivity(intent);
        });

        guestText.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, HomeActivity.class);
            startActivity(intent);
        });

        forgotPasswordText.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }
}
