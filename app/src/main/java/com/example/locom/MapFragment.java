package com.example.locom;


import static android.content.Context.LOCATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.hawk.Hawk;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class MapFragment extends Fragment {
    Context thiscontext;
    int which;
    protected Activity mActivity;
    volatile boolean someBoolean;
    LocationManager locationManager;
    LatLng posOfDragg;


    String chatonClickname;

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
        which = 0;

        Hawk.init(thiscontext).build();
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

                        BitmapDescriptor iconRed = BitmapDescriptorFactory.fromResource(R.drawable.red_dot);
                        // Creating a marker
                        MarkerOptions markerOptions = new MarkerOptions().icon(iconRed);

                        // Setting the position for the marker
                        markerOptions.position(latLng);
                        posOfDragg = latLng;
                        // Setting the title for the marker.
                        // This will be displayed on taping the marker
                        markerOptions.title("Новая метка");

                        // Clears the previously touched position
                        //googleMap.clear();


                        // Animating to the touched position
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                        // Placing a marker on the touched position
                        googleMap.addMarker(markerOptions)
                                .setDraggable(true);

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
//                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                    @Override
//                    public boolean onMarkerClick(Marker marker) {
//
//                        return false;
//                    }
//                });

                googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {


                    @Override
                    public void onMarkerDragStart(Marker marker) {


                        Log.e("myApp", "вы кликнули");

                        // showMarkerSet(googleMap, marker);
                        showMarker2(googleMap, marker);

                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        marker.setPosition(posOfDragg);
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {
                        marker.setPosition(posOfDragg);
                        // TODO Auto-generated method stub

                    }
                });
                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        enterChat(marker);
                        Hawk.put("chatName", marker.getTitle());
                        marker.showInfoWindow();
                        Log.e("myApp", "ПРОШЛО в чат");
                        // TODO Auto-generated method stub

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
    private void showMarkerSet(GoogleMap googleMap, Marker marker) {
        final String[] optionMenu = {"Происшествие", "Геолокационный чат", "Знакомства"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(thiscontext, android.R.layout.select_dialog_item, optionMenu);
        AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
        builder.setTitle("Выберите тип метки");


        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                BitmapDescriptor iconBlue = BitmapDescriptorFactory.fromResource(R.drawable.blue_dot);
                BitmapDescriptor iconGreen = BitmapDescriptorFactory.fromResource(R.drawable.green_dot);
                BitmapDescriptor iconRed = BitmapDescriptorFactory.fromResource(R.drawable.red_dot);
                switch (which) {
                    case 0:
                        marker.setIcon(iconRed);
                        break;
                    case 1:
                        marker.setIcon(iconBlue);
                        break;
                    case 2:
                        marker.setIcon(iconGreen);

                        break;


                }
                Log.e("myApp", Integer.toString(which));

            }
        });
        final AlertDialog a = builder.create();
        a.show();
    }

    LinearLayout addview;
    EditText nameMarker;
    Button buttonFirst;
    Button buttonSecond;
    Button buttonThird;
    int type;

    private void showMarker2(GoogleMap googleMap, Marker marker) {
        addview = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.mymenu, null);
        nameMarker = addview.findViewById(R.id.etComments);
        buttonFirst = addview.findViewById(R.id.button2);
        buttonSecond = addview.findViewById(R.id.button3);
        buttonThird = addview.findViewById(R.id.button4);

        String textButton1 = (String) buttonFirst.getText();
        String textButton2 = (String) buttonSecond.getText();
        String textButton3 = (String) buttonThird.getText();

        type = 0;
        buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFirst.setText(textButton1 + " ✔");
                buttonSecond.setText(textButton2);
                buttonThird.setText(textButton3);
                type = 1;
            }
        });
        buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFirst.setText(textButton1);
                buttonSecond.setText(textButton2 + " ✔");
                buttonThird.setText(textButton3);
                type = 2;
            }
        });
        buttonThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFirst.setText(textButton1);
                buttonSecond.setText(textButton2);
                buttonThird.setText(textButton3 + " ✔");
                type = 3;
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(thiscontext);
        alert.setTitle("Создание новой метки");
        alert.setView(addview);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nameforcurrentmarker = nameMarker.getText().toString();

                marker.setTitle(nameforcurrentmarker);


                Toast toast = Toast.makeText(thiscontext,
                        "Метка успешно сохранена", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                googleMap.clear();
            }
        });
        alert.show();


    }

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
    public void setWhich(int which) {
        this.which = which;

    }

    public int getWhich() {
        return which;
    }

    private void enterChat(Marker marker) {
        startActivity(new Intent(thiscontext, ChatActivity.class));
    }


}

