package com.czarneBarany.korepetytorsi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.czarneBarany.korepetytorsi.Entitys.AccountEntity;
import com.czarneBarany.korepetytorsi.Entitys.AdvertisementEntity;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.List;
import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity {


    String Title[]=new String[100];
    String User[]=new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        ListView listView = findViewById(R.id.taskList);

        getAllAdvertisement();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        MyAdapter adapter = new MyAdapter(this, Title, User);
        listView.setAdapter(adapter);

    }



    private void getAllAdvertisement(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://40.76.9.138:8080/api//get/allAds/teacher/" + Integer.parseInt(Objects.requireNonNull(getSharedPreferences("myPrefs", MODE_PRIVATE).getString("accountId", "")));

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        VolleyLog.d(response.toString());
                        Gson gson=new Gson();
                        AdvertisementEntity[] obj=gson.fromJson(response.toString(), AdvertisementEntity[].class);

                        int x = 0;
                        for(int i=0;i<obj.length;i++){
                            List<AccountEntity> list = obj[i].getListOfStudents();
                            for(int j=0;j<list.size();j++){
                                Title[x]= obj[i].getTitle();
                                User[x] = list.get(j).getEmail();
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];

        MyAdapter(Context c, String title[], String description[]) {
            super(c, R.layout.row2, R.id.textView30, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView myTitle = row.findViewById(R.id.textView30);
            TextView myDescription = row.findViewById(R.id.textView31);

            myTitle.setText(Title[position]);
            myDescription.setText(User[position]);

            return row;
        }

    }
}
