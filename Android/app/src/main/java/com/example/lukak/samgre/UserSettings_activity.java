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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import adapters.ObjavaListAdapter;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UserSettings_activity extends Activity {
    Dialog myDialog;
    Button btnSpremeniGeslo;
    EditText editTextElektronskaPosta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings_activity);
        editTextElektronskaPosta = (EditText)findViewById(R.id.editTextElektronskaPosta);


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




}


