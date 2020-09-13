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

@SuppressLint("Registered")
public class RegisterActivity extends AppCompatActivity {

    private EditText enterUsername,enterEmail,enterPassword;
    private Button btnRegister,btnloginregister;
    private FirebaseAuth auth;

    public void init(){
       enterUsername = (EditText) findViewById(R.id.enterusername);
       enterEmail= (EditText) findViewById(R.id.enteremail);
       enterPassword=(EditText) findViewById(R.id.enterpassword);
       btnRegister =(Button)findViewById(R.id.btncreateaccount);
       btnloginregister =(Button) findViewById(R.id.btncreate);
       auth = FirebaseAuth.getInstance();

    }

    private void createNewAccount() {
        String username =enterUsername.getText().toString();
        String email = enterEmail.getText().toString();
        String password = enterPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,R.string.email_field,Toast.LENGTH_LONG).show();

        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,R.string.password_field,Toast.LENGTH_LONG).show();
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        Toast.makeText(RegisterActivity.this,R.string.succesfull,Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this,R.string.errror,Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle(R.string.CREATE_BAR);
        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

        btnloginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLoginActivity();
            }
        });

    }

    private void gotoLoginActivity (){
        Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }

}
