package com.example.saglikd2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class HomeFragment extends Fragment {
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton cikis,ileri;

    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        floatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.floatingActionMenu);
        cikis = (FloatingActionButton) view.findViewById(R.id.floatinActionItem2);
        ileri = (FloatingActionButton) view.findViewById(R.id.floatinActionItem3);
        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getActivity(), Main2Activity.class);
                startActivity(registerIntent);
            }
        });
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loinIntent = new Intent(getActivity(),LoginActivity.class);
                startActivity(loinIntent);
            }
        });
        return view;
    }

}