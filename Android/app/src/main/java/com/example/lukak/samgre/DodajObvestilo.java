package com.example.lukak.samgre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class DodajObvestilo extends android.support.v4.app.Fragment {

    TextView lokacija;
    private GoogleApiClient mGoogleApiClient;
    private static final int PLACE_PICKER_REQ_CODE = 2;
    private TextView tvPlaceDetails;
    private FloatingActionButton fabPickPlace;
    ImageButton img;
    LatLng loc;
    Place plac;

    public DodajObvestilo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        return inflater.inflate(R.layout.fragment_dodaj_obvestilo, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final String[] arraySpinner = new String[]{
                "Radar", "Zastoj", "Izredni dogodek", "Nesreča", "Prepoved za tovornjake", "Zaprta cesta", "Delo na cesti", "Sneg", "Veter"
        };
        Spinner s = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);

        lokacija = (TextView)view.findViewById(R.id.textviewlokacija);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter2);

        img = (ImageButton) view.findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPlacePicker();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            plac = PlacePicker.getPlace(getContext(), data);
            loc = plac.getLatLng();
            lokacija.setText(loc.latitude + " , " + loc.longitude);

        }

    }


    private void showPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(getActivity()), 1);
        } catch (Exception e) {
            Log.e(TAG, e.getStackTrace().toString());
        }
    }
}
