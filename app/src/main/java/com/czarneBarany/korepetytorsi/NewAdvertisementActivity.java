package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.czarneBarany.korepetytorsi.Entitys.AdvertisementEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class NewAdvertisementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newadvertisement);

        final EditText title = findViewById(R.id.titleET);
        final EditText pricePerHour = findViewById(R.id.stawkaET);
        EditText availability = findViewById(R.id.availabilityET);
        final EditText description = findViewById(R.id.descriptionET);
        final EditText lan = findViewById(R.id.editText3);
        final EditText lon= findViewById(R.id.editText4);
        CheckBox drive = findViewById(R.id.driveCheckBox);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdvertisementEntity advertisementEntity = new AdvertisementEntity(title.getText().toString(),
                        description.getText().toString(),
                        getIntent().getStringExtra("subject2"),
                        getIntent().getStringExtra("level"),
                        Integer.parseInt(pricePerHour.getText().toString()),
                        1,
                        lan.getText().toString(),
                        lon.getText().toString()

                );


                Gson gson = new Gson();

                try {
                    createNewAdvertisement(new JSONObject(gson.toJson(advertisementEntity)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });






    }

    private void createNewAdvertisement(JSONObject advertisementEntity) {
        Log.d("XXX", advertisementEntity.toString());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://40.89.142.102:8080/api/add/ad";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,advertisementEntity,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(response.toString());
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
