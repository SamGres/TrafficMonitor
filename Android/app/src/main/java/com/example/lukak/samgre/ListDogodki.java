package com.example.lukak.samgre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;


public class ListDogodki extends Fragment {
    ArrayList<Post> data;
    ListView Mojlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_list_dogodki, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        data = new ArrayList<>();
        TinyDB tinydb = new TinyDB(getActivity().getApplicationContext());
        String out = tinydb.getString("Posts");
        final Gson gson = new Gson();
        data = new ArrayList<Post>(Arrays.asList(gson.fromJson(out, Post[].class)));

        listview_adapter adapter1 = new listview_adapter(view.getContext(), data);
        Mojlist = view.findViewById(R.id.listviewdogodki);
        Mojlist.setAdapter(adapter1);

        Mojlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get selected post
                Post post = (Post) Mojlist.getItemAtPosition(position);

                //create intent and pass through data
                Intent intent = new Intent(getContext(), PostDetailsActivity.class);
                intent.putExtra("post", gson.toJson(post));
                startActivity(intent);
            }
        });


        final String[] arraySpinner = new String[]{
                "Vse", "Radar", "Zastoj", "Izredni dogodek", "Nesreča", "Prepoved za tovornjake", "Zaprta cesta", "Delo na cesti", "Sneg", "Veter"
        };
        Spinner s = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter2);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (position != 0) {
                    ArrayList<Post> filtered = new ArrayList<Post>();
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).cause.equals(arraySpinner[position])) {
                            filtered.add(data.get(i));
                        }
                    }

                    listview_adapter adapter2 = new listview_adapter(getActivity(), filtered);
                    Mojlist.setAdapter(null);
                    Mojlist.setAdapter(adapter2);
                } else {
                    Mojlist.setAdapter(new listview_adapter(getActivity(), data));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // nc i guess?

            }

        });

    }
}
