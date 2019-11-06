package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Subject2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject2);

        Button engButton = findViewById(R.id.engButton);
        Button gerButton = findViewById(R.id.gerButton);
        Button spaButton = findViewById(R.id.spaButton);
        Button freButton = findViewById(R.id.freButton);
        Button itaButton = findViewById(R.id.itaButton);


        engButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewAdvertisementActivity.class);
                startActivity(intent);
            }
        });
    }
}
