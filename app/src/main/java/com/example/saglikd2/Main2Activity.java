package com.example.saglikd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.r0adkll.slidr.model.SlidrInterface;

public class Main2Activity extends AppCompatActivity {
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
    }


    public void gotokatologlar(View view){
        Intent intent = new Intent(Main2Activity.this, EgitimKatologlari.class);
        startActivity(intent);
    }

    public void goToAlarm(View view){
        Intent intent = new Intent(Main2Activity.this, MainAlarmActivity.class);
        startActivity(intent);
    }

    public void kanTakip(View view) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }
    public void gotoyillik(View view) {
        Intent intent = new Intent(Main2Activity.this, GirisYap.class);
        startActivity(intent);
    }
}
