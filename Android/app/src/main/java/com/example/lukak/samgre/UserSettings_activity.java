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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.lukak.samgre.dummy.SimpleUser;
import com.google.android.gms.common.util.Base64Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import adapters.ObjavaListAdapter;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserSettings_activity extends Activity {
    Dialog myDialog;
    SimpleUser simpleUser;
    Button btnSpremeniGeslo;
    EditText editTextElektronskaPosta;
    EditText fullname;
    EditText kontakt;
    EditText pass1;
    EditText pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings_activity);
        editTextElektronskaPosta = (EditText)findViewById(R.id.editTextElektronskaPosta);
        myDialog = new Dialog(this);
        pass1 = findViewById(R.id.edit123);
        pass2 = findViewById(R.id.editText3);
        btnSpremeniGeslo = findViewById(R.id.btnGeslo);
        fullname = findViewById(R.id.editText6);



        btnSpremeniGeslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ShowPopup(v);
            }
        });
        kontakt = findViewById(R.id.editText12);
        TinyDB moju = new TinyDB(getApplicationContext());
        Gson gson = new Gson();
        simpleUser =  gson.fromJson(moju.getString("User"),SimpleUser.class);

        // Nastavi vrednosti uporabnika
        editTextElektronskaPosta.setText(simpleUser.email);
        fullname.setText(simpleUser.fullname);
        kontakt.setText(simpleUser.phone_number);

    }
    public void ShowPopup(View v) {
        Button btnClose;
        Button btnAdd;
        final EditText editTextStaroGeslo;
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
                if (oldPass.length() > 0)
                {
                    simpleUser.setEmail(editTextElektronskaPosta.getText().toString());
                    simpleUser.setFullname(fullname.getText().toString());
                    simpleUser.setPhone_number(kontakt.getText().toString());

                    SpremeniPodatke(pass1.getText().toString(),oldPass);
                    myDialog.dismiss();
                    finish();
                }
                else{
                    myDialog.dismiss();
                    finish();
                }

            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void SpremeniPodatke(String newPassword,String oldPassword){
        OkHttpClient client = new OkHttpClient();
        SharedPreferences mpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token = mpref.getString("Token", "nega");

        Request request = new Request.Builder().addHeader("token", token).addHeader("password", oldPassword).addHeader("newEmail", simpleUser.getPhone_number())
                .addHeader("newFullname", simpleUser.getFullname()).addHeader("newPhone_number", simpleUser.getPhone_number()).addHeader("newPassword", newPassword)
                .url(getResources().getString(R.string.serverurl) +"/user/changeData")
                .build();

        try {
            Response response =  client.newCall(request).execute();
            String res = response.message().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


