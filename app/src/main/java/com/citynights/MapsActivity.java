package com.citynights;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.*;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng grandhostelBerlin = new LatLng(52.498556, 13.384473);
        mMap.addMarker(new MarkerOptions().position(grandhostelBerlin).title("Grand Hostel Berlin"));

        LatLng smarthostelBerlin = new LatLng(52.547610, 13.353473);
        mMap.addMarker(new MarkerOptions().position(smarthostelBerlin).title("Smarthostel Berlin"));

        LatLng cityhostelBerlin = new LatLng(52.511369, 13.386487);
        mMap.addMarker(new MarkerOptions().position(cityhostelBerlin).title("Cityhostel Berlin"));

        LatLng generatorhostelHamburg = new LatLng(53.552905, 10.009320);
        mMap.addMarker(new MarkerOptions().position(generatorhostelHamburg).title("Generator Hostel Hamburg"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(grandhostelBerlin, 13));


    }
}
