package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

public class GgMap extends AppCompatActivity {

   // FusedLocatio fusedLocationProviderClient;
   // GoogleMap
    TextView country,city,address,longitude,lagitude;
    Button getLocation;
    private  final static  int REQUEST_CODE = 100;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gg_map);
        country =findViewById(R.id.country);
        city =findViewById(R.id.city);
        address =findViewById(R.id.address);
        longitude =findViewById(R.id.longitude);
        lagitude =findViewById(R.id.lagitude);
        getLocation =findViewById(R.id.btnGetLocation);

    }
}