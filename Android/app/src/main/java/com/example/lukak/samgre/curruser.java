package com.example.lukak.samgre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;


public class curruser extends Fragment {

    ImageButton btnFacebook;
    ImageButton btnInstagram;
    ImageButton btnChrome;
    Button btnNastavitve;
    Button btnObjave;
    Button btnOdjava;
    TextView uporabnik;


    public curruser() {
        // Required empty public constructor
    }

//TODO horizontaln pogled
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curruser, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        btnNastavitve = view.findViewById(R.id.btnNastavitve);
        btnObjave = view.findViewById(R.id.btnObvestila);
        btnOdjava = view.findViewById(R.id.btnOdajva);
        btnFacebook = view.findViewById(R.id.btnFacebook);
        btnInstagram = view.findViewById(R.id.btnInstagram);
        btnChrome = view.findViewById(R.id.btnChrome);
        uporabnik = view.findViewById(R.id.TextViewUporabnik);

        btnObjave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ObvestilaActivity.class));
            }
        });

        btnNastavitve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),UserSettings_activity.class));
            }
        });



    }



}

