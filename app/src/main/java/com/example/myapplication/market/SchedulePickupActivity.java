package com.example.myapplication.market;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SchedulePickupActivity extends AppCompatActivity {

    TextView dateText;
    ImageView calendarBtn;
    RadioGroup timeGroup;
    RadioButton rb10to3, rb3to9;
    EditText pickupLocationEdit, notesEdit;
    Button confirmBtn;

    Calendar calendar;
    SimpleDateFormat sdf;
    String selectedTime = "10AM-3PM";

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_pickup);

        dateText = findViewById(R.id.dateText);
        calendarBtn = findViewById(R.id.calendarBtn);
        timeGroup = findViewById(R.id.timeGroup);
        rb10to3 = findViewById(R.id.rb10to3);
        rb3to9 = findViewById(R.id.rb3to9);
        pickupLocationEdit = findViewById(R.id.pickupLocationEdit);
        notesEdit = findViewById(R.id.notesEdit);
        confirmBtn = findViewById(R.id.confirmBtn);

        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        dateText.setText(sdf.format(calendar.getTime()));

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(SchedulePickupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        dateText.setText(sdf.format(calendar.getTime()));
                    }
                }, y, m, d);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.show();
            }
        });

        timeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb10to3) selectedTime = "10AM-3PM";
                else if (checkedId == R.id.rb3to9) selectedTime = "3PM-9PM";
            }
        });

        rb10to3.setChecked(true);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateText.getText().toString().trim();
                String location = pickupLocationEdit.getText().toString().trim();
                String notes = notesEdit.getText().toString().trim();

                if (location.isEmpty()) {
                    Toast.makeText(SchedulePickupActivity.this, "Please enter pickup location", Toast.LENGTH_SHORT).show();
                    return;
                }

                String summary = "Date: " + date + "\nTime: " + selectedTime + "\nLocation: " + location;
                if (!notes.isEmpty()) summary += "\nNotes: " + notes;

                Toast.makeText(SchedulePickupActivity.this, summary, Toast.LENGTH_LONG).show();
            }
        });
    }
}
