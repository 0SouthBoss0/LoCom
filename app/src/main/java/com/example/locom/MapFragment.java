package com.example.locom;


import static android.content.Context.LOCATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Service;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment {
    Context thiscontext;

    protected Activity mActivity;

    LocationManager locationManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;

    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    double lat;
    double lon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();
//        getLocation();

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);


        ////////////
        //getActivity().startService(new Intent(getActivity(),MyService.class));


//        Intent intent = new Intent(thiscontext, BackgroundService.class);
//
//        startService(intent);
//MyAsyncTask myAsyncTask = new MyAsyncTask(getContext());
//myAsyncTask.execute();

        //Test test = new Test();


/////////////////////

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(final GoogleMap googleMap) {

//                if (isGPSCatched()) {
//                    MarkerOptions markerOptions2 = new MarkerOptions();
//                    LatLng latLng2 = new LatLng(lat, lon);
//                    markerOptions2.position(latLng2);
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng2));
//                    googleMap.addMarker(markerOptions2);
//                }


                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                Log.e("myApp", "map_activated");

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                    @Override
                    public void onMapClick(LatLng latLng) {


                        // Creating a marker
                        MarkerOptions markerOptions = new MarkerOptions();

                        // Setting the position for the marker
                        markerOptions.position(latLng);

                        // Setting the title for the marker.
                        // This will be displayed on taping the marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        // Clears the previously touched position
                        //googleMap.clear();

                        // Animating to the touched position
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                        // Placing a marker on the touched position
                        googleMap.addMarker(markerOptions);

//                        if (isGPSCatched()) {
//                            Log.w("myApp", "Маркёр готов");
//                            MarkerOptions markerOptions2 = new MarkerOptions();
//                            LatLng latLng2 = new LatLng(lat, lon);
//                            markerOptions2.position(latLng2);
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng2));
//                            googleMap.addMarker(markerOptions2);
//
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 14));
//                        }

                    }

                });

            }
        });

        return view;
    }

//    private boolean isGPSCatched() {
//        if (lon != 0) {
//            return true;
//        }
//
//        return false;
//    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) thiscontext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

//    @SuppressLint("MissingPermission")
//    private void getLocation() {
//
//        try {
//            locationManager = (LocationManager) mActivity.getApplicationContext().getSystemService(LOCATION_SERVICE);
//            Log.w("myApp", "ловлю жпс");
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//
//        } catch (Exception e) {
//            Log.w("myApp", "проблема с жпс");
//            e.printStackTrace();
//
//        }
//
//    }

//    @Override
//    public void onLocationChanged(Location location) {
//        Log.e("myApp", location.getLatitude() + "," + location.getLongitude());
//
//        lat = location.getLatitude();
//        lon = location.getLongitude();
//
//
//        try {
//            Geocoder geocoder = new Geocoder(thiscontext, Locale.getDefault());
//            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            String address = addresses.get(0).getAddressLine(0);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }


}

