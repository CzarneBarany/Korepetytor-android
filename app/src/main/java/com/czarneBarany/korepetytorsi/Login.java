package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.saveButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject log = new JSONObject();

                try {
                    log.put("email", "123@gmail.com");
                    log.put("password", "123");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                login(log);

                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
            }
        });



    }

    private void login(JSONObject log) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://40.76.9.138:8080/api/login";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,log,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.e(response.toString());
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
