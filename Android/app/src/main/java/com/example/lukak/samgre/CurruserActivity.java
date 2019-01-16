package com.example.lukak.samgre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CurruserActivity extends Activity {

    ImageButton btnFacebook;
    ImageButton btnInstagram;
    ImageButton btnChrome;
    Button btnNastavitve;
    Button btnObjave;
    Button btnOdjava;
    TextView uporabnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_curruser);
        btnNastavitve = (Button)findViewById(R.id.btnNastavitve);
        btnObjave = (Button)findViewById(R.id.btnObvestila);
        btnOdjava = (Button)findViewById(R.id.btnOdajva);
        btnFacebook = (ImageButton) findViewById(R.id.btnFacebook);
        btnInstagram = (ImageButton) findViewById(R.id.btnInstagram);
        btnChrome = (ImageButton) findViewById(R.id.btnChrome);
        uporabnik = (TextView) findViewById(R.id.TextViewUporabnik);

        btnObjave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change fragment dude
            }
        });
    }
}
