package com.example.myapplication.Account;

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

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.forgotRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText emailField = findViewById(R.id.forgotEmailField);
        Button sendResetButton = findViewById(R.id.sendResetButton);
        TextView backToLoginText = findViewById(R.id.backToLoginText);

        sendResetButton.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailField.setError("Enter a valid email");
                emailField.requestFocus();
                return;
            }

            Toast.makeText(this, "Reset link sent to " + email, Toast.LENGTH_LONG).show();
        });

        backToLoginText.setOnClickListener(v -> finish());
    }
}
