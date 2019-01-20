package com.example.lukak.samgre;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lukak.samgre.dummy.SimpleUser;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UserSettings_activity extends Activity {
    Dialog myDialog;
    SimpleUser simpleUser;
    Button btnSpremeniGeslo;
    EditText editTextElektronskaPosta;
    EditText fullname;
    EditText kontakt;
    EditText pass1;
    EditText pass2;
    EditText editTextStaroGeslo;
    FloatingActionButton btnNazaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings_activity);
        editTextElektronskaPosta = (EditText) findViewById(R.id.editTextElektronskaPosta);
        myDialog = new Dialog(this);
        pass1 = findViewById(R.id.edit123);
        pass2 = findViewById(R.id.editText3);
        btnSpremeniGeslo = findViewById(R.id.btnGeslo);
        fullname = findViewById(R.id.editText6);
        btnNazaj = findViewById(R.id.floatingActionButton2);

        btnNazaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSpremeniGeslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup(v);
            }
        });
        kontakt = findViewById(R.id.editText12);
        TinyDB moju = new TinyDB(getApplicationContext());
        Gson gson = new Gson();
        simpleUser = gson.fromJson(moju.getString("User"), SimpleUser.class);

        // Nastavi vrednosti uporabnika
        editTextElektronskaPosta.setText(simpleUser.email);
        fullname.setText(simpleUser.fullname);
        kontakt.setText(simpleUser.phone_number);

    }

    public void ShowPopup(View v) {
        Button btnClose;
        Button btnAdd;

        myDialog.setContentView(R.layout.custompopup);
        btnClose = (Button) myDialog.findViewById(R.id.btnClose);
        btnAdd = (Button) myDialog.findViewById(R.id.btnAdd);
        editTextStaroGeslo = (EditText) myDialog.findViewById(R.id.editTextGeslo);

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
                String oldPass = editTextStaroGeslo.getText().toString();
                if (oldPass.length() > 0) {
                    simpleUser.setEmail(editTextElektronskaPosta.getText().toString());
                    simpleUser.setFullname(fullname.getText().toString());
                    simpleUser.setPhone_number(kontakt.getText().toString());

                    try {
                        doSignup(v);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myDialog.dismiss();
                    finish();
                } else {
                    myDialog.dismiss();
                    finish();
                }

            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    void doSignup(final View v) throws IOException {
        //create request body
        OkHttpClient client = new OkHttpClient();
        SharedPreferences mpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token = mpref.getString("Token", "nega");

        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder().addHeader("token", token).addHeader("password", editTextStaroGeslo.getText().toString())
                .addHeader("newEmail", simpleUser.getEmail())
                .addHeader("newFullname", simpleUser.getFullname())
                .addHeader("newPhone_number", simpleUser.getPhone_number())
                .addHeader("newPassword", pass1.getText().toString())
                .url(getResources().getString(R.string.serverurl) + "/user/changeData")
                .post(formBody)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        //get response

                    }
                });

    }
}


