package com.kogicodes.bravetest.ui.map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.kogicodes.bravetest.R;
import com.kogicodes.bravetest.models.room.AirportModel;

import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AirportModel airportModelOrigin;
    private AirportModel airportModelDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        Intent intent = getIntent();
        if (intent != null) {
            airportModelDestination = (AirportModel) intent.getSerializableExtra(Const.KEY_DESTINATION);
            airportModelOrigin = (AirportModel) intent.getSerializableExtra(Const.KEY_ORIGIN);


        }
        com.google.android.gms.maps.SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng departureLatLng = new LatLng(airportModelOrigin.getAirportLatitude(), airportModelOrigin.getAirportLongitude());
        mMap.addMarker(new MarkerOptions().position(departureLatLng).title(airportModelOrigin.getAirportNames()));

        LatLng arrivalLatLng = new LatLng(airportModelDestination.getAirportLatitude(), airportModelDestination.getAirportLongitude());
        mMap.addMarker(new MarkerOptions().position(arrivalLatLng).title(airportModelDestination.getAirportNames()));


        mMap.addPolyline(new PolylineOptions()
                .add(departureLatLng, arrivalLatLng)
                .width(5)
                .color(Color.RED));

        LatLngBounds latLngBounds = new LatLngBounds.Builder()
                .include(departureLatLng)
                .include(arrivalLatLng)
                .build();

        int zoomPadding = 200;
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, width, height, zoomPadding));
    }

}
