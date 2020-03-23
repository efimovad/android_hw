package com.technopark.firsthw;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedActivity extends AppCompatActivity {
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        String num = getIntent().getStringExtra("NUM");
        String color = getIntent().getStringExtra("COLOR");
        TextView textView = findViewById(R.id.title_details);
        textView.setText(num);
        textView.setTextColor(Integer.parseInt(color));
    }
}
