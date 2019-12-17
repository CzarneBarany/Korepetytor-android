package com.czarneBarany.korepetytorsi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);
    }

    public void getData(View view){
        simpleRequest();
    }

    private void simpleRequest() {
        // 1. Uzyskanie referencji do kolejki
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://40.89.142.102:8080/api/get/allUsers";

        // 2. Utworzenie żądania
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        VolleyLog.d(response);
                        TextView v  = (TextView)findViewById(R.id.textView);
                        v.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error.getMessage());
            }
        });

        // 3. Dodanie żądania na kolejkę.
        queue.add(stringRequest);
    }
}
