package com.czarneBarany.korepetytorsi;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> Title=new ArrayList<>();
    ArrayList<String> User=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        listView = findViewById(R.id.taskList);

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
                                Title.add(obj[i].getTitle());
                                User.add(list.get(j).getEmail());
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

    class DownloadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getAllAdvertisement();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("background: ", "tlo");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            MyAdapter adapter = new MyAdapter(ScheduleActivity.this, Title, User);
            listView.setAdapter(adapter);
            Log.d("onpost: ","Post");

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ScheduleActivity.this);
                    builder1.setMessage("Czy chcesz się zapisać?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   // getAllAdvertisement("http://40.76.9.138:8080/api/add/studentToAdvertisement/"+ID.get(position)+"/"+Integer.parseInt(Objects.requireNonNull(getSharedPreferences("myPrefs", MODE_PRIVATE).getString("accountId", "")))
                                  //  );
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });
        }
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> rTitle;
        ArrayList<String> rDescription;

        MyAdapter(Context c, ArrayList<String> title, ArrayList<String> description) {
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

            myTitle.setText(Title.get(position));
            myDescription.setText(User.get(position));

            return row;
        }

    }
}
