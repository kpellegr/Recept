package com.example.recept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv_ingredient_name = findViewById(R.id.tv_ingredient_detail);
        Intent intent = getIntent();
        tv_ingredient_name.setText(
                String.format("%.1f %s",
                intent.getFloatExtra("INGREDIENT_QTY", 3.141592f),
                intent.getStringExtra("INGREDIENT_NAME")));
    }
}