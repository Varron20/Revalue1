package com.example.myapplication.market;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;

public class DetailsActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    LinearLayout dotsLayout;
    Button btnNext;
    ImageView backButton;
    Button button;

    int[] images = {
            com.example.a2.R.mipmap.firstmarket_foreground,
            com.example.a2.R.mipmap.secmarket_foreground,
            com.example.a2.R.mipmap.thirdmarket_foreground,
            com.example.a2.R.mipmap.forthmarket_foreground
    };

    TextView[] dots;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotsLayout);
        btnNext = findViewById(R.id.btnNext);
        backButton = findViewById(R.id.backButton);
        button = findViewById(R.id.button);

        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        if (button != null) {
            button.setOnClickListener(v -> {
                Intent intent = new Intent(DetailsActivity.this, SchedulePickupActivity.class);
                startActivity(intent);
            });
        }

        if (images == null || images.length == 0) {
             images = new int[]{};
        }

        ImageAdapter adapter = new ImageAdapter(images);
        viewPager.setAdapter(adapter);

        addDots(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                addDots(position);
            }
        });

        if (btnNext != null) {
            btnNext.setOnClickListener(v -> {
                int current = viewPager.getCurrentItem();
                if (current < images.length - 1) {
                    viewPager.setCurrentItem(current + 1);
                } else {
                    viewPager.setCurrentItem(0);
                }
            });
        }
    }

    private void addDots(int position) {
        dots = new TextView[images.length];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText("●");
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.GRAY);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(Color.BLACK);
        }
    }

    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

        private int[] images;

        public ImageAdapter(int[] images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new ViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder,
                                     int position) {
            holder.imageView.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = (ImageView) itemView;
            }
        }
    }
}
