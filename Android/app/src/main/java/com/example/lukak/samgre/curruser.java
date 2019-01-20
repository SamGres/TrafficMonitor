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

import com.example.lukak.samgre.dummy.SimpleUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class curruser extends Fragment {

    SimpleUser simpleUser;
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
        TinyDB moju = new TinyDB(this.getActivity().getApplicationContext());
        Gson gson = new Gson();
        simpleUser =  gson.fromJson(moju.getString("User"),SimpleUser.class);
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



    }






}

