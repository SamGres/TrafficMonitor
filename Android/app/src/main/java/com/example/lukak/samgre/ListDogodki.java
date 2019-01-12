package com.example.lukak.samgre;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ListDogodki extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_list_dogodki, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ArrayList<Post> data = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++)
        {
            Post Zapop = new Post();
            Zapop.description = "Post"+ i;
            data.add(Zapop);
        }
        listview_adapter adapter1 = new listview_adapter(view.getContext(),data);
        ListView Mojlist = view.findViewById(R.id.listviewdogodki);
        Mojlist.setAdapter(adapter1);

    }
}
