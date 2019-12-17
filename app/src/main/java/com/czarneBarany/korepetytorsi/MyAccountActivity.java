package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.czarneBarany.korepetytorsi.Entitys.AccountEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MyAccountActivity extends AppCompatActivity {

    ArrayList<String> Name=new ArrayList<>();
    ArrayList<String> PhoneNumber=new ArrayList<>();
    ArrayList<String> Surname= new ArrayList<>();
    TextView nameTextView;
    TextView surnameTextView;
    TextView numberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        nameTextView = findViewById(R.id.nameTextView);
        surnameTextView = findViewById(R.id.surnameTextView);
        numberTextView = findViewById(R.id.numberTextView);


        new DownloadAccountData().execute();


        Button editButton=findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, EditDataActivity.class);
                startActivity(intent);
            }
        });

        Button scheduleButton=findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, ScheduleActivity.class);
                intent.putExtra("option", "1");
                startActivity(intent);
            }
        });

        Button scheduleButton2=findViewById(R.id.scheduleButton2);
        scheduleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccountActivity.this, ScheduleActivity.class);
                intent.putExtra("option", "2");
                startActivity(intent);
            }
        });

    }


    private void getAccountDetails(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url="http://40.89.142.102:8080/api/get/account/" + Integer.parseInt(Objects.requireNonNull(getSharedPreferences("myPrefs", MODE_PRIVATE).getString("accountId", "")));

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson=new Gson();
                        AccountEntity obj=gson.fromJson(response.toString(),AccountEntity.class);

                            nameTextView.setText(obj.getFirstname());
                            surnameTextView.setText(obj.getLastname());
                            numberTextView.setText(obj.getPhoneNumber());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    class DownloadAccountData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getAccountDetails();
            return null;
        }

    }

}
