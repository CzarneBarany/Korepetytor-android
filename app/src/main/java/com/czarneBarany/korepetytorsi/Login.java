package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        final EditText email = findViewById(R.id.emailEditText);
        final EditText password = findViewById(R.id.passwordEditTexts);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject log = new JSONObject();

                try {
                    log.put("email", email.getText());
                    log.put("password", password.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                login(log);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
            }
        });



    }

    private void login(JSONObject log) {
        VolleyLog.e(log.toString());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://40.89.142.102:8080/api/login";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,log,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                        try {
                            preferences.edit().putString("accountId", response.getString("accountId")).apply();
                            preferences.edit().putString("jwtToken", response.getString("jwtToken")).apply();
                            preferences.edit().putString("role", response.getString("role")).apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        VolleyLog.e(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error.getMessage());
            }
        })
        {
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer "+ getSharedPreferences("myPrefs", MODE_PRIVATE).getString("jwtToken",""));
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
