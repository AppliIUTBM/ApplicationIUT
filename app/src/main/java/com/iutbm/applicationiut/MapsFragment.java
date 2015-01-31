package com.iutbm.applicationiut;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsFragment extends MapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng defaultCoord = new LatLng(47.5721162,6.8403918);

        LatLng coordLP = new LatLng(47.642941,6.839285);
        LatLng coordCV = new LatLng(47.640044,6.857059);
        LatLng coordRT = new LatLng(47.49482,6.802994);

        LatLng coordABLP = new LatLng(47.643786,6.840887);

        BitmapDescriptor iconIUT = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET);
        BitmapDescriptor iconBus = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
        BitmapDescriptor iconVelo = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

        String iutLP = "IUT Belfort LP";
        String iutCV = "IUT Belfort CV";
        String iutRT = "IUT Montbéliard RT";

        String abOptymo = "Arrêt de bus Optymo";
        String abCTPM = "Arrêt de bus CTPM";

        CameraPosition defaultPosition = new CameraPosition(defaultCoord,11,0.0F,0);

        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(defaultPosition));
        googleMap.addMarker(new MarkerOptions()
                .position(coordLP)
                .title(iutLP)
                .icon(iconIUT));
        googleMap.addMarker(new MarkerOptions()
                .position(coordRT)
                .title(iutRT)
                .icon(iconIUT));
        googleMap.addMarker(new MarkerOptions()
                .position(coordCV)
                .title(iutCV)
                .icon(iconIUT));
        googleMap.addMarker(new MarkerOptions()
                .position(coordABLP)
                .title(abOptymo)
                .icon(iconBus));
    }
}
