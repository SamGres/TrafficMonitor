package com.example.lukak.samgre;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;


import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static android.content.Context.LOCATION_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;


public class Map extends Fragment implements OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyC-GjITQrAeucuUZj6104a5EL0uQIi1WwU";
    private MapView mapView;
    private GoogleMap gmap;
    ArrayList<Post> AllPosts = new ArrayList<>();
    OkHttpClient client = new OkHttpClient();
    LocationManager locationManager;
    Context con;
    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        return inflater.inflate(R.layout.fragment_map, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.

    @SuppressLint("MissingPermission")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        con = getActivity().getBaseContext();
    /*    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(view.getContext());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                                          @Override
                                          public void onSuccess(Location location) {
                                              gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 9.25f));
                                          }
                                      });*/


                        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);


        SharedPreferences mpref = PreferenceManager.getDefaultSharedPreferences(view.getContext().getApplicationContext());
        String token = mpref.getString("Token", "nega");


        try {
            doGetRequest(getResources().getString(R.string.serverurl) + "/traffic/getEvents", token);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





    public void DodajMarkerje() {
        for (int i = 0; i < AllPosts.size(); i++) {

            MarkerOptions marac = new MarkerOptions().position(new LatLng(AllPosts.get(i).y, AllPosts.get(i).x)).title(AllPosts.get(i).description);
            marac.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nevarnost), 92, 92, false)));

            try {
                switch (AllPosts.get(i).cause) {
                    case "Zastoj":

                        marac.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.prometnizastoj), 92, 92, false)));

                        break;

                    case "Izredni dogodek":

                        marac.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nevarnost), 92, 92, false)));


                        break;

                    case "Nesreča":
                        marac.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.prometnanesreca), 92, 92, false)));

                        break;

                    case "Prepoved za tovornjake":
                        marac.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.prepovedtovorna), 92, 92, false)));

                        break;

                    case "Zaprta cesta":
                        marac.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.prepovedanpromet), 92, 92, false)));

                        break;

                    case "Delo na cesti":
                        marac.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.delonacesti), 92, 92, false)));

                        break;

                    case "Sneg":
                        marac.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sneg), 92, 92, false)));

                        break;


                }
            } catch (Exception e) {
            }


            gmap.addMarker(marac);


        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.0761518, 14.2494279), 9.25f));
        gmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
        gmap.setTrafficEnabled(true);

        //TODO marker on click
        //TODO marker oblikovanje


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

    }


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();

    }

    void doGetRequest(String url, String token) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .addHeader("token", token)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        String res = response.body().string();
                        //TODO tu bo jebal kr ne ves kak java compare
                        if (!res.equals("false")) {
                            Gson gson = new Gson();
                            AllPosts = new ArrayList<Post>(Arrays.asList(gson.fromJson(res, Post[].class)));

                            TinyDB tinydb = new TinyDB(getActivity().getApplicationContext());
                            tinydb.putString("Posts", gson.toJson(AllPosts));


                            getActivity().runOnUiThread((new Runnable() {
                                @Override
                                public void run() {
                                    //Handle UI here
                                    DodajMarkerje();

                                }
                            }));

                        }

                    }


                });
    }

}
