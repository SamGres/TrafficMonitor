package com.example.lukak.samgre;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;


public class curruser extends Fragment {



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


}

