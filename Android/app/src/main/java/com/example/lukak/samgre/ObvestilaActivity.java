package com.example.lukak.samgre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.*;
import android.widget.*;

import java.util.List;

import adapters.ObjavaListAdapter;


public class ObvestilaActivity extends Activity {

    private List<Obvestilo> obvestila;
    private  ObvestilaRepositori repositori;
    private  ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obvestila);
        repositori = new ObvestilaRepositori();
        obvestila = repositori.PridobiObvestila();
        list = (ListView)findViewById(R.id.listviewObvsetila);
        list.setAdapter(new ObjavaListAdapter(this,obvestila));

    }
}
