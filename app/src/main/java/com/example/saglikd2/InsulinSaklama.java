package com.example.saglikd2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsulinSaklama extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insulin_saklama_kosullari);
        getSupportActionBar().setTitle("İnsülin Saklama Koşulları");
    }
}
