package com.example.lukak.samgre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.*;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.*;

import java.util.List;

import adapters.ObjavaListAdapter;


public class ObvestilaActivity extends Activity {

    private List<Obvestilo> obvestila;
    private  ObvestilaRepositori repositori;
    private  ListView list;
    FloatingActionButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obvestila);
        back =  (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        repositori = new ObvestilaRepositori();
        obvestila = repositori.PridobiObvestila();
        list = (ListView)findViewById(R.id.listviewObvsetila);
        list.setAdapter(new ObjavaListAdapter(this,obvestila));
        list.setDivider(null);

        View Header = getLayoutInflater().inflate(R.layout.activity_naslov_obvestila, null);
        Header.setPadding(0, 0, 0, 0);
        list.addHeaderView(Header);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}
