package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.czarneBarany.korepetytorsi.Entitys.AccountEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void postRegiestration(View view){
        EditText email = ((EditText)findViewById(R.id.emailId));
        EditText password = ((EditText)findViewById(R.id.passwordId));
        EditText firstName = ((EditText)findViewById(R.id.firstNameId));
        EditText lastName = ((EditText)findViewById(R.id.lastNameId));
        EditText city = ((EditText)findViewById(R.id.cityId));
        EditText phoneNumber = ((EditText)findViewById(R.id.phoneNumberId));
        AccountEntity accountEntity = new AccountEntity(email.getText().toString(),
                password.getText().toString(),
                firstName.getText().toString(),
                lastName.getText().toString(),
                city.getText().toString(),
                phoneNumber.getText().toString());
        Gson gson = new Gson();

        try {
            registtation(new JSONObject(gson.toJson(accountEntity)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void registtation(JSONObject accountEntity) {
        Log.d("XXX", accountEntity.toString());
        // 1. Uzyskanie referencji do kolejki
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://40.89.142.102:8080/api/add/account";

        // 2. Utworzenie żądania
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,accountEntity,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(response.toString());
                        TextView v  = (TextView)findViewById(R.id.textView);
                        v.setText(response.toString());
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
