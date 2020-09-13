package com.example.saglikd2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GirisYap extends AppCompatActivity {
    EditText dersAdiGirisET,sinifAdiGirisET,etsaat;
    CalendarView takvimGirisCV;
    Button dersekle,derslerigör,btnsaatsec,hatirlateklebtn,ayarlaragitbtn;

    FirebaseDatabase fd;
    DatabaseReference dr;
    Member menber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        dersAdiGirisET = findViewById(R.id.dersadi);
        sinifAdiGirisET = findViewById(R.id.dersinsinifi);
        etsaat = findViewById(R.id.edittext_saat);
        takvimGirisCV = findViewById(R.id.tarih);
        dersekle = findViewById(R.id.dersekle_btn);
        derslerigör = findViewById(R.id.derslerigör_btn);
        btnsaatsec = findViewById(R.id.saatsec_btn);
        hatirlateklebtn = findViewById(R.id.eklebtn);
        ayarlaragitbtn = findViewById(R.id.ayarlarbtn);
        menber= new Member();

        fd= FirebaseDatabase.getInstance();
        dr=fd.getReference().child("member");

        derslerigör.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisYap.this,RetrieveData.class);
                startActivity(intent);
            }
        });

        dersekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String data =dersAdiGirisET.getText().toString();
                String data1 =sinifAdiGirisET.getText().toString();
                String data2 = etsaat.getText().toString();


                menber.setDersadi(data);
                menber.setDersinifi(data1);
                menber.setTarih(data2);
                dr.push().setValue(menber);

                Toast.makeText(GirisYap.this,"data saved",Toast.LENGTH_SHORT).show();
            }
        });

        btnsaatsec.setOnClickListener(v -> showDateTimeDialog(btnsaatsec));
    }
    private void showDateTimeDialog(final Button btnsaatsec) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yy HH:mm");

                        etsaat.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(GirisYap.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
            }
        };

        new DatePickerDialog(GirisYap.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }




}
