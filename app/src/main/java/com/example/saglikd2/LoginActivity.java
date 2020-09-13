package com.example.saglikd2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("Registered")
public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail,txtPassword;
    private Button btnlogin, btnlognRegister;
    private FirebaseUser currentUser;

    private FirebaseAuth auth;
    public void init(){
        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword =(EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlognRegister = (Button) findViewById(R.id.btncreateAccount);

        auth = FirebaseAuth.getInstance();
        currentUser =auth.getCurrentUser();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(R.string.login_bar);


        init();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();

            }

        });

        btnlognRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegisterActivity();


            }
        });


    }

    private void gotoRegisterActivity(){

        Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }

    private void loginUser() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, R.string.email_field, Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.password_field, Toast.LENGTH_LONG).show();

        }else{
            btnlogin.setEnabled(false);
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, R.string.succesfull,Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(mainIntent);
                    }else {
                        Toast.makeText(LoginActivity.this, R.string.errror,Toast.LENGTH_LONG).show();
                        btnlogin.setEnabled(true);
                    }
                }
            });

        }


    }
}
