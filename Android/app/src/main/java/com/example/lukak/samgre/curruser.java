package com.example.lukak.samgre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lukak.samgre.dummy.SimpleUser;
import com.google.gson.Gson;


public class curruser extends Fragment {

    SimpleUser simpleUser;
    ImageButton btnFacebook;
    ImageButton btnInstagram;
    ImageButton btnChrome;
    ImageButton btnTwiter;
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
        TinyDB moju = new TinyDB(this.getActivity().getApplicationContext());
        Gson gson = new Gson();
        simpleUser =  gson.fromJson(moju.getString("User"),SimpleUser.class);
        btnTwiter = (ImageButton)view.findViewById(R.id.btnTwitter);
        btnNastavitve = view.findViewById(R.id.btnNastavitve);
        btnObjave = view.findViewById(R.id.btnObvestila);
        btnOdjava = view.findViewById(R.id.btnOdajva);
        btnFacebook = view.findViewById(R.id.btnFacebook);
        btnInstagram = view.findViewById(R.id.btnInstagram);
        btnChrome = view.findViewById(R.id.btnChrome);
        uporabnik = view.findViewById(R.id.TextViewUporabnik);


        uporabnik.setText(simpleUser.fullname);

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

        btnChrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.dars.si/"));
                startActivity(intent);
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
            }
        });

        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.instagram.com/"));
                startActivity(intent);
            }
        });

        btnTwiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://twitter.com/"));
                startActivity(intent);
            }
        });

        btnOdjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mpref = PreferenceManager.getDefaultSharedPreferences(v.getContext().getApplicationContext());
                mpref.edit().putString("Token", "").apply();

                startActivity(new Intent(v.getContext(),MainActivity.class));
                getActivity().getFragmentManager().popBackStack();
            }
        });

    }






}

