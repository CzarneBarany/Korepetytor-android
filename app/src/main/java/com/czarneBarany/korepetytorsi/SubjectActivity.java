package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);


        Button scienceButton = findViewById(R.id.scienceButton);
        Button humanButton = findViewById(R.id.humanButton);
        Button artButton = findViewById(R.id.artButton);
        Button languageButton = findViewById(R.id.languageButton);


        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Subject2Activity.class);
                startActivity(intent);
            }
        });



    }
}
