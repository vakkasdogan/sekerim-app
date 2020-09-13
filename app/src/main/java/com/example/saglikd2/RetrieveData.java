package com.example.saglikd2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

@SuppressLint("Registered")
public class RetrieveData extends AppCompatActivity {
    private ListView listView;
    FirebaseDatabase fd;
    DatabaseReference dr;
    private ArrayList<String> list1   ;
    ArrayAdapter<String> adapter1;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieved);
        listView=findViewById(R.id.listView);
        user = new User();
        list1 =new ArrayList<>();
        adapter1 = new ArrayAdapter<String>(this, R.layout.user_info,R.id.textview,list1);
        dr = FirebaseDatabase.getInstance().getReference().child("member");
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    user=ds.getValue(User.class);
                    list1.add(user.getDersadi()+"\n "+user.getDersinifi()+"\n "+ user.getTarih());
                }listView.setAdapter(adapter1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
