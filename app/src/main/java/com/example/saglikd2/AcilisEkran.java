package com.example.saglikd2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class AcilisEkran extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acilis_ekran);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try
                {
                    sleep(3000);

                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }
                finally
                {
                    Intent mainIntent = new Intent(AcilisEkran.this,AnaSayfa.class);
                    startActivity(mainIntent);

                }
            }
        };
        thread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }

    public void gotogiris(View view ){
        Intent intent = new Intent(AcilisEkran.this, AnaSayfa.class);
        startActivity(intent);
    }
}
