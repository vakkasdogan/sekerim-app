package com.example.saglikd2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsulinBolgeleri extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insulin_bolgeler);
        getSupportActionBar().setTitle("İnsülin Uygulama Bölgeleri");
    }
}
