package com.example.lukak.samgre;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Register extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        con = this;

        //create buttons
        final Button register = (Button) this.findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    doSignup(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button back = (Button) this.findViewById(R.id.button3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void doSignup(final View v) throws IOException {
        //create request body
        RequestBody formBody = new FormBody.Builder().build();

        //get elements
        EditText email = (EditText) findViewById(R.id.txtRegisterEmail);
        EditText fullname = (EditText) findViewById(R.id.txtRegisterFullname);
        EditText phone = (EditText) findViewById(R.id.txtRegisterPhone);
        EditText pass1 = (EditText) findViewById(R.id.txtRegisterPassword1);
        EditText pass2 = (EditText) findViewById(R.id.txtRegisterPassword2);
        if (!pass1.getText().toString().equals(pass2.getText().toString())) { //flag
            return;
        }

        //create request
        Request request = new Request.Builder()
                .addHeader("email", email.getText().toString())
                .addHeader("fullname", fullname.getText().toString())
                .addHeader("phone", phone.getText().toString())
                .addHeader("password", pass1.getText().toString())
                .url(getResources().getString(R.string.serverurl) + "/login/registerUser").post(formBody)
                .build();

        //call request
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        //get response
                        String res = response.body().string();

                        if (res.equals("true")) {
                            finish();
                        }
                    }
                });
    }
}
