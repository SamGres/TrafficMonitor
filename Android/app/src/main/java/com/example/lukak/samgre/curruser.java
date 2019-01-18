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
        simpleUser = PridobiUsera();
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

    public SimpleUser PridobiUsera(){
        SharedPreferences mpref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String token = mpref.getString("Token", "nega");
        SimpleUser simpleUser = new SimpleUser();
        RequestBody formBody = new FormBody.Builder()
                .build();


        Request request = new Request.Builder().addHeader("token", token)
                .url(getResources().getString(R.string.serverurl) + "/user/getData").post(formBody)
                .build();

        OkHttpClient clinet = new OkHttpClient();
        clinet.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {


                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                        String res = response.body().string();
                        JSONObject movieObject = null;
                        try {
                            movieObject = new JSONObject(res);
                            String title = movieObject.getString("fullname");
                            uporabnik.setText(title);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
        return simpleUser;
    }




}

