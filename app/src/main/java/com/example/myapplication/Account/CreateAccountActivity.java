package com.example.myapplication.Account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.Welcom_screen.HomeActivity;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.createAccountRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText fullNameField = findViewById(R.id.fullNameField);
        EditText emailField = findViewById(R.id.emailField);
        EditText passwordField = findViewById(R.id.passwordField);
        EditText confirmPasswordField = findViewById(R.id.confirmPasswordField);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        TextView loginRedirectText = findViewById(R.id.loginRedirectText);

        createAccountButton.setOnClickListener(v -> {
            String fullName = fullNameField.getText().toString().trim();
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString();
            String confirmPassword = confirmPasswordField.getText().toString();

            if (TextUtils.isEmpty(fullName)) {
                fullNameField.setError("Full name is required");
                fullNameField.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailField.setError("Enter a valid email");
                emailField.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                passwordField.setError("Password is required");
                passwordField.requestFocus();
                return;
            }

            if (password.length() < 6) {
                passwordField.setError("Password must be at least 6 characters");
                passwordField.requestFocus();
                return;
            }

            if (!password.equals(confirmPassword)) {
                confirmPasswordField.setError("Passwords do not match");
                confirmPasswordField.requestFocus();
                return;
            }

            Toast.makeText(this, "Account created!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        loginRedirectText.setOnClickListener(v -> finish());
    }
}
