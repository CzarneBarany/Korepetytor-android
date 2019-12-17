package com.czarneBarany.korepetytorsi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

public class EditDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        Button saveButton = findViewById(R.id.loginButton);

        final EditText passwordEditText = findViewById(R.id.passordEditText);
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText surrnameEditText = findViewById(R.id.surrnameEditText);
        EditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        EditText cityEditText = findViewById(R.id.cityEditText);


        //Pobranie danych i ustawienie ich w inputach

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "URL NIEWIEM JAKI";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //passwordEditText.setText(response.get());
                        //ZROBIE POTEM

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error.getMessage());
            }
        });
        queue.add(stringRequest);

        //wysłanie z edytowanych danych do bazy

       /* AccountEntity accountEntity = new AccountEntity(email.getText().toString(),
                passwordEditText.getText().toString(),
                nameEditText.getText().toString(),
                surrnameEditText.getText().toString(),
                cityEditText.getText().toString(),
                phoneNumberEditText.getText().toString());
        Gson gson = new Gson();

        try {
            editAccount(new JSONObject(gson.toJson(accountEntity)));
        } catch (JSONException e) {
            e.printStackTrace();

        }*/


        }






    private void editAccount(JSONObject accountEntity) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "URL NIEWIEM JAKI";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,accountEntity,
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
