package com.techtown.lastapplication;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback {


    private double lat;
    private double lon;
    private String title,nickname,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        lat = (double) intent.getDoubleExtra("lat",999);
        lon = (double) intent.getDoubleExtra("lon", 999);
        title = (String) intent.getStringExtra("title");
        nickname = (String) intent.getStringExtra("nickname");
        content = (String) intent.getStringExtra("content");


        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap map) {

        LatLng Point = new LatLng(lat, lon);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(Point);
        markerOptions.title(title);
        markerOptions.snippet(content);
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(Point));
        map.animateCamera(CameraUpdateFactory.zoomTo(16));
    }
}