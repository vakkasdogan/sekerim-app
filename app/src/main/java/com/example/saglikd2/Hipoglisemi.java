package com.example.saglikd2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class Hipoglisemi extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hipoglisemi);
        getSupportActionBar().setTitle("Hipoglisemi");
    }
}

