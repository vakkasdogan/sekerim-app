package com.example.saglikd2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


public class Alarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alarm_layout);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });


        Button buton2 = findViewById(R.id.send_button);
        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed1 = findViewById(R.id.alarm_notu);
                String title = ed1.getText().toString().trim();


                NotificationManager notif = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notifx = new Notification.Builder(getApplicationContext()).setContentTitle("Şekerim").setContentText(title).setSmallIcon(R.drawable.app_icon).build();
                notif.notify(0, notifx);

            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = findViewById(R.id.textView);
        textView.setText("Saat: " + hourOfDay + ":" + minute);

        int saat = calendar.get(Calendar.HOUR_OF_DAY);
        int dakika = calendar.get(Calendar.MINUTE);

        if (saat == hourOfDay && dakika == minute) {
            sendNot();
        }
    }

    public void sendNot(){
        EditText ed1 = findViewById(R.id.alarm_notu);
        String title = ed1.getText().toString().trim();


        NotificationManager notif = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notifx = new Notification.Builder(getApplicationContext()).setContentTitle("Şekerim").setContentText(title).setSmallIcon(R.drawable.app_icon).build();
        notif.notify(0, notifx);
    }

}
