package com.example.lukak.samgre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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
    Button submit;
    EditText desc;
    Spinner s;

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
        s = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);

        lokacija = (TextView) view.findViewById(R.id.textviewlokacija);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter2);

        img = (ImageButton) view.findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPlacePicker();
            }
        });

        desc = (EditText) view.findViewById(R.id.editText5);
        submit = (Button) view.findViewById(R.id.button4);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    doSignup(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    void doSignup(final View v) throws IOException {
        //create request body
        RequestBody formBody = new FormBody.Builder().build();

        OkHttpClient client = new OkHttpClient();
        SharedPreferences mpref = PreferenceManager.getDefaultSharedPreferences(v.getContext().getApplicationContext());
        String token = mpref.getString("Token", "nega");
        //create request
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date formattedDate = new Date();
        try {
            formattedDate = format.parse(String.valueOf(formattedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder()
                .addHeader("token", token)
                .addHeader("description", desc.getText().toString())
                .addHeader("category", "")
                .addHeader("cause", s.getSelectedItem().toString())
                .addHeader("x", (String.valueOf(loc.longitude)))
                .addHeader("y", (String.valueOf(loc.latitude)))
                .addHeader("region", "")
                .addHeader("date", String.valueOf(new Date()))
                .url(getResources().getString(R.string.serverurl) + "/post/add").post(formBody)
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

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_container, new Map());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                });
    }
}
