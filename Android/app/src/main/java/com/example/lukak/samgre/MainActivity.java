package com.example.lukak.samgre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = this;

        Button Login = (Button) this.findViewById(R.id.button);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    doPost("penis");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        final Button register = (Button) this.findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), Register.class));

            }
        });

    }

    void doPost(String url) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .build();

        EditText email = (EditText) findViewById(R.id.editText2);
        EditText pass = (EditText) findViewById(R.id.editText);

        Request request = new Request.Builder().addHeader("email", email.getText().toString()).
                addHeader("password", pass.getText().toString())
                .url(getResources().getString(R.string.serverurl) + "/login/authenticateUser").post(formBody)
                .build();


        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
//todo a ce vrne 400 gre sm?
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        String res = response.body().string();
                        SharedPreferences mpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        mpref.edit().putString("Token", res).apply();
                        startActivity(new Intent(con, NavActivity.class));
                        finish();


                    }


                });
    }
}
