package com.example.lukak.samgre;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.util.Base64Utils;

import java.io.IOException;

import adapters.ObjavaListAdapter;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UserSettings_activity extends Activity {
    Dialog myDialog;
    User uporabnik;
    Button btnSpremeniGeslo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings_activity);

        myDialog = new Dialog(this);

        btnSpremeniGeslo = findViewById(R.id.btnGeslo);

        btnSpremeniGeslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ShowPopup(v);
            }
        });


    }
    public void ShowPopup(View v) {
        Button btnClose;
        Button btnAdd;
        final EditText text = (EditText)findViewById(R.id.editTextGeslo);
        myDialog.setContentView(R.layout.custompopup);
        btnClose = (Button) myDialog.findViewById(R.id.btnClose);
        btnAdd = (Button) myDialog.findViewById(R.id.btnAdd);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String geslo = text.getText().toString();
                if (geslo != "geslo123")
                {
                    myDialog.dismiss();
                    finish();
                }
                else{
                    //aksdjalskdjalsk
                    myDialog.dismiss();
                    finish();
                }

            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public User PridobiUsera(View v){


        RequestBody formBody = new FormBody.Builder()
                .build();
        SharedPreferences mpref = PreferenceManager.getDefaultSharedPreferences(v.getContext().getApplicationContext());
        String token = mpref.getString("Token", "nega");

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

                    }

                });
        return  null;
    }

}


