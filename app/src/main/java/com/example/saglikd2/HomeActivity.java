package com.example.saglikd2;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.navigation.NavigationView;

@SuppressLint("Registered")
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Button next_page_btn;
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton next1,next2,next3;


    @SuppressLint({"WrongViewCast", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.color.white);
        }

    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_chat:
                Intent Intent8 = new Intent(HomeActivity.this,Main3Activity.class);
                startActivity(Intent8);
                break;
            case R.id.nav_diyabet:
                Intent Intent9 = new Intent(HomeActivity.this,VideoActivity.class);
                startActivity(Intent9);
                break;
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUs()).commit();
                break;
            case R.id.nav_profile:
                Intent Intent2 = new Intent(HomeActivity.this,EgitimKatologlari.class);
                startActivity(Intent2);

                break;
            case R.id.nav_alarm:
                Intent registerIntent = new Intent(HomeActivity.this,MainAlarmActivity.class);
                startActivity(registerIntent);
                break;
            case R.id.nav_news:
                Intent Intent23 = new Intent(HomeActivity.this,Diabetes_org.class);
                startActivity(Intent23);
                break;

            case R.id.nav_predictState:
                Intent Intent3 = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(Intent3);
                break;
            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my app");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Intent instaIntent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/wakkasdgn/?hl=tr"));
                startActivity(instaIntent1);
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_email:
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","vkksdgn2@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "message");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void gotokatologlar1(View view){
        Intent intent = new Intent(HomeActivity.this, EgitimKatologlari.class);
        startActivity(intent);
    }

    public void goToAlarm1(View view){
        Intent intent = new Intent(HomeActivity.this, MainAlarmActivity.class);
        startActivity(intent);
    }

    public void kanTakip1(View view) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void gotoyillik1(View view) {
        Intent intent = new Intent(HomeActivity.this, GirisYap.class);
        startActivity(intent);
    }

}
