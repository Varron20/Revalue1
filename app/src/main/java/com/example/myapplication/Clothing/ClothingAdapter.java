package com.example.myapplication.Clothing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

import java.util.List;

public class ClothingAdapter extends RecyclerView.Adapter<ClothingAdapter.ViewHolder> {

    Context context;
    List<ClothingItem> list;

    public ClothingAdapter(Context context, List<ClothingItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_clothing, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int pos) {
        ClothingItem item = list.get(pos);

        h.type.setText(item.type + " (" + item.material + ")");
        h.color.setText("Color: " + item.color);
        h.date.setText("Date: " + item.date);
        h.status.setText(item.status);

        if (item.type != null && item.type.contains("T-Shirt"))
            h.image.setImageResource(R.drawable.ic_shirt);
        else
            h.image.setImageResource(R.drawable.ic_jeans);

        // Steps UI
        if ("Received".equals(item.status)) {
            h.step1.setBackgroundResource(R.drawable.step_active);
            h.step2.setBackgroundResource(R.drawable.step_inactive);
            h.step3.setBackgroundResource(R.drawable.step_inactive);
            h.step4.setBackgroundResource(R.drawable.step_inactive);
        }
        else if ("Sorting".equals(item.status)) {
            h.step1.setBackgroundResource(R.drawable.step_active);
            h.step2.setBackgroundResource(R.drawable.step_active);
            h.step3.setBackgroundResource(R.drawable.step_inactive);
            h.step4.setBackgroundResource(R.drawable.step_inactive);
        }
        else if ("Recycling".equals(item.status)) {
            h.step1.setBackgroundResource(R.drawable.step_active);
            h.step2.setBackgroundResource(R.drawable.step_active);
            h.step3.setBackgroundResource(R.drawable.step_active);
            h.step4.setBackgroundResource(R.drawable.step_inactive);
        }
        else {
            h.step1.setBackgroundResource(R.drawable.step_active);
            h.step2.setBackgroundResource(R.drawable.step_active);
            h.step3.setBackgroundResource(R.drawable.step_active);
            h.step4.setBackgroundResource(R.drawable.step_active);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView type, color, date, status;
        ImageView image;
        View step1, step2, step3, step4;
        public ViewHolder(View v) {
            super(v);
            type = v.findViewById(R.id.itemType);
            color = v.findViewById(R.id.itemColor);
            date = v.findViewById(R.id.itemDate);
            status = v.findViewById(R.id.itemStatus);
            image = v.findViewById(R.id.itemImage);
            step1 = v.findViewById(R.id.step1);
            step2 = v.findViewById(R.id.step2);
            step3 = v.findViewById(R.id.step3);
            step4 = v.findViewById(R.id.step4);
        }
    }
}
