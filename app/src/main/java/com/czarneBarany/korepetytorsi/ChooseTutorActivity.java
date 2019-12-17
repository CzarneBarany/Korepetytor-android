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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.czarneBarany.korepetytorsi.Entitys.AdvertisementEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ChooseTutorActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<String> Title=new ArrayList<>();
    ArrayList<String> Description=new ArrayList<>();
    ArrayList<Integer> ID=new ArrayList<>();
    ArrayList<Integer> Price=new ArrayList<>();
    ArrayList<Integer> TeacherID=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_tutor);

        listView = findViewById(R.id.englishList);

        //Title.add("Nowy");
        //Description.add("Opis");
        //ID.add(0);
        //Price.add(50);

        new DownloadData().execute();


    }
    private void getIDAdvertisement(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null,
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
    private void getAccountDetails(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url="http://40.89.142.102:8080/api/get/account/";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null,
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

    private void getAdvertisementDetails(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String category = getIntent().getStringExtra("subject2");
        String level = getIntent().getStringExtra("level");

        String url = "http://40.89.142.102:8080/api/get/allAds/categoryAndLevelOfEducation/"+category+"/"+level;

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson=new Gson();
                        AdvertisementEntity[] obj=gson.fromJson(response.toString(),AdvertisementEntity[].class);

                        for(int i=0;i<obj.length;i++){
                            Title.add(obj[i].getTitle());
                            Description.add(obj[i].getDescription());
                            ID.add(obj[i].getAdId());
                            Price.add(obj[i].getPricePerHour());
                            TeacherID.add(obj[i].getTeacherId());
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
            getAdvertisementDetails();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            MyAdapter adapter = new MyAdapter(ChooseTutorActivity.this, Title, Description);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ChooseTutorActivity.this);
                    builder1.setTitle(Title.get(position))
                    .setMessage(Description.get(position)+"\n"+"Cena za godzine: "+Price.get(position)+"\n")
                    .setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getIDAdvertisement("http://40.89.142.102:8080/api/add/studentToAdvertisement/"+ID.get(position)+"/"+Integer.parseInt(Objects.requireNonNull(getSharedPreferences("myPrefs", MODE_PRIVATE).getString("accountId", ""))));
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
            super(c, R.layout.row, R.id.textView20, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView myTitle = row.findViewById(R.id.textView20);
            TextView myDescription = row.findViewById(R.id.textView21);

            myTitle.setText(Title.get(position));
            myDescription.setText(Description.get(position));

            return row;
        }
    }
}
