package com.example.saglikd2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class AboutUs extends Fragment {
    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentabout_us, container, false);
        btn = (Button) view.findViewById(R.id.indir);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1AlOIHYuecouXILvX4Ws0Cwv5LbVbaTqO/view?usp=sharing"));
                startActivity(instaIntent);
            }
        });
        return view;
    }

}