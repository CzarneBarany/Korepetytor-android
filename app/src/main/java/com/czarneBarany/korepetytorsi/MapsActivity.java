package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.czarneBarany.korepetytorsi.Entitys.AdvertisementEntity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    String category;
    String level;
    private GoogleMap mMap;
    private List<Marker> mPerth;
    private ArrayList<AdvertisementEntity> adEntityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getIntent().getStringExtra("subject2");
        level = getIntent().getStringExtra("level");
        getAdvertisementDetails();
        mPerth = new ArrayList<Marker>();
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
        //d51.919438!4d19.145136
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(51.919438, 19.145136);

        LatLng sydney2 = new LatLng(34, 151);
        LatLng sydney3 = new LatLng(-44, 151);

        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }
        for(AdvertisementEntity ar : adEntityList){
            if(ar.getXcoord() != null && ar.getXcoord() != null) {
                LatLng perth = new LatLng(Double.valueOf(ar.getXcoord()), Double.valueOf(ar.getYcoord()));
                Marker mark = mMap.addMarker(new MarkerOptions()
                        .position(perth)
                        .title(ar.getTitle()));
                mark.setTag(ar.getAdId());
                mPerth.add(mark);
            }
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        startActivity(new Intent(MapsActivity.this,MainPage.class));
        return false;
    }

    private void getAdvertisementDetails(){
        RequestQueue queue = Volley.newRequestQueue(this);


        String url = "http://40.89.142.102:8080/api/get/allAds/categoryAndLevelOfEducation/"+category+"/"+level;
        Log.d("************", url);

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson=new Gson();
                        adEntityList = new ArrayList<AdvertisementEntity>(Arrays.asList(gson.fromJson(response.toString(),AdvertisementEntity[].class)));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error.getMessage());
            }
        });

        queue.add(stringRequest);

    }
}
