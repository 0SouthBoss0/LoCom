//package com.example.locom;
//
//import java.util.Date;
//import java.util.Map;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Intent;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//
//public class Test extends Activity implements MyInterface{
//    private LocationManager locationManager;
//    StringBuilder sbGPS = new StringBuilder();
//    StringBuilder sbNet = new StringBuilder();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//    }
//
//    @SuppressLint("MissingPermission")
//    @Override
//    protected void onResume() {
//        super.onResume();
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                1000 * 10, 10, locationListener);
//        locationManager.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
//                locationListener);
//        checkEnabled();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        locationManager.removeUpdates(locationListener);
//    }
//
//    private LocationListener locationListener = new LocationListener() {
//
//        @Override
//        public void onLocationChanged(Location location) {
//            showLocation(location);
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            checkEnabled();
//        }
//
//        @SuppressLint("MissingPermission")
//        @Override
//        public void onProviderEnabled(String provider) {
//            checkEnabled();
//            showLocation(locationManager.getLastKnownLocation(provider));
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            if (provider.equals(LocationManager.GPS_PROVIDER)) {
//            } else if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
//            }
//        }
//    };
//
//    public void showLocation(Location location) {
//        if (location == null)
//            return;
//        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
//            Log.e("myApp", (formatLocation(location)));
//        } else if (location.getProvider().equals(
//                LocationManager.NETWORK_PROVIDER)) {
//            Log.e("myApp", (formatLocation(location)));
//        }
//    }
//
//    private String formatLocation(Location location) {
//        if (location == null)
//            return "";
//        MapFragment mapFragment = new MapFragment();
//        mapFragment.setLat(location.getLatitude());
//        mapFragment.setLon(location.getLongitude());
//        return String.format(
//                "Coordinates: lat = %1$.4f, lon = %2$.4f",
//                location.getLatitude(), location.getLongitude());
//
//
//
//    }
//
//    private void checkEnabled() {
//        Log.e("myApp", ("Enabled: "
//                + locationManager
//                .isProviderEnabled(LocationManager.GPS_PROVIDER)));
//        Log.e("myApp", ("Enabled: "
//                + locationManager
//                .isProviderEnabled(LocationManager.NETWORK_PROVIDER)));
//    }
//
//    public void onClickLocationSettings(View view) {
//        startActivity(new Intent(
//                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//    }
//
//    @Override
//    public void getcoor(double coor) {
//
//
//    }
//
//    ;
//
//}
