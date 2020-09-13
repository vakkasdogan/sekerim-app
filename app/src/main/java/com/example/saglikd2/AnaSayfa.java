package com.example.saglikd2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.saglikd2.Helper.LocaleHelper;

import java.util.Locale;

public class AnaSayfa extends AppCompatActivity {
    private Button btnLogin,btnRegister,btnLanguage;

    public void face1 (View view){
        Intent faceIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tr-tr.facebook.com/Ege-Diyabetliler-Derne%C4%9Fi-154141664628940/"));
        startActivity(faceIntent);
    }

    public void insta1 (View view){
        Intent instaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/explore/locations/296750870/ege-diyabetliler-dernegi?hl=tr"));
        startActivity(instaIntent);
    }
    public void twit1 (View view){
        Intent twit1Intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/egediyabetliler"));
        startActivity(twit1Intent);
    }

    public void init(){
        btnLogin = (Button) findViewById(R.id.alreadyAccount);
        btnRegister = (Button) findViewById(R.id.newaccount);
        btnLanguage = (Button) findViewById(R.id.changebuton);
        loadLocale();


    }
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(LocaleHelper.onAttach(newbase,"tr"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
        init();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogin = new Intent(AnaSayfa.this,LoginActivity.class);
                startActivity(intentLogin);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRegister = new Intent(AnaSayfa.this,RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });


    }



    private void showChangeLanguageDialog() {

        final  String [] listItems ={"Türkçe","İngilizce"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(AnaSayfa.this);
        mBuilder.setTitle("...");
        mBuilder.setTitle("dil seçiniz...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which==0){
                    setLocale("tr");
                    recreate();
                }
                else if (which==1){
                    setLocale("en");
                    recreate();
                }
                dialog.dismiss ();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config =  new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor =getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My Lang",lang);
        editor.apply();

    }
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

}


