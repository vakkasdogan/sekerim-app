package com.example.saglikd2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class KanSekeriOlcum extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kansekeriolcum_layout);
        getSupportActionBar().setTitle("Kan Şekeri Ölçümü");
    }
}
