package com.example.myapplication.market;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.Welcom_screen.HomeActivity;

public class UploadItemActivity extends AppCompatActivity {

    private ImageView itemImage;
    private EditText itemName, categoryField, conditionField, descriptionField;
    private Button uploadBtn;
    private ImageView backBtn;

    private ActivityResultLauncher<Void> takePictureLauncher;
    private ActivityResultLauncher<String> pickImageLauncher;
    private ActivityResultLauncher<String> requestCameraPermissionLauncher;
    private ActivityResultLauncher<String> requestGalleryPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupResultLaunchers();
        setupListeners();
    }

    private void initializeViews() {
        itemImage = findViewById(R.id.itemImage);
        itemName = findViewById(R.id.itemName);
        categoryField = findViewById(R.id.categoryField);
        conditionField = findViewById(R.id.conditionField);
        descriptionField = findViewById(R.id.descriptionField);
        uploadBtn = findViewById(R.id.uploadBtn);
        backBtn = findViewById(R.id.backBtn);
    }

    private void setupResultLaunchers() {
        takePictureLauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), result -> {
            if (result != null) {
                itemImage.setImageBitmap(result);
            }
        });

        pickImageLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            if (result != null) {
                itemImage.setImageURI(result);
            }
        });

        requestCameraPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                takePictureLauncher.launch(null);
            } else {
                Toast.makeText(this, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show();
            }
        });

        requestGalleryPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                pickImageLauncher.launch("image/*");
            } else {
                Toast.makeText(this, "Gallery permission is required to select photos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupListeners() {
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> finish());
        }

        if (itemImage != null) {
            itemImage.setOnClickListener(v -> showImageSourceDialog());
        }

        if (uploadBtn != null) {
            uploadBtn.setOnClickListener(v -> {
                if (validateInput()) {
                    Toast.makeText(this, "Upload Successfully", Toast.LENGTH_SHORT).show();
                    
                    // Go back to MainActivity2 (Home Page)
                    Intent intent = new Intent(UploadItemActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private void showImageSourceDialog() {
        String[] options = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image Source");
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                checkCameraPermissionAndOpen();
            } else {
                checkGalleryPermissionAndOpen();
            }
        });
        builder.show();
    }

    private void checkCameraPermissionAndOpen() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            takePictureLauncher.launch(null);
        } else {
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void checkGalleryPermissionAndOpen() {
        String permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            pickImageLauncher.launch("image/*");
        } else {
            requestGalleryPermissionLauncher.launch(permission);
        }
    }

    private boolean validateInput() {
        boolean isValid = true;
        
        if (itemName.getText().toString().trim().isEmpty()) {
            itemName.setError("Item Name is required");
            isValid = false;
        }
        
        if (categoryField.getText().toString().trim().isEmpty()) {
            categoryField.setError("Category is required");
            isValid = false;
        }
        
        if (conditionField.getText().toString().trim().isEmpty()) {
            conditionField.setError("Condition is required");
            isValid = false;
        }

        return isValid;
    }
}
