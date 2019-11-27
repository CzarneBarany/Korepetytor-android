package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);





        Button browseOfferButton = findViewById(R.id.browseOfferButon);
        Button addOfferButton = findViewById(R.id.addOfferButon);
        Button logOutButton = findViewById(R.id.logOutButton);
        Button myAccButton = findViewById(R.id.myAccButton);


        browseOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
                intent.putExtra("option number", 1);
                startActivity(intent);
            }
        });

        myAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyAccountActivity.class);
                startActivity(intent);
            }
        });

        addOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);

                intent.putExtra("option number2", 2);

                startActivity(intent);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSharedPreferences("myPrefs", MODE_PRIVATE).edit().remove("accountId").apply();
                getSharedPreferences("myPrefs", MODE_PRIVATE).edit().remove("role").apply();
                getSharedPreferences("myPrefs", MODE_PRIVATE).edit().remove("jwtToken").apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
