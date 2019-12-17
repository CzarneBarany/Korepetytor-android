package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startNewActivity(View view){
        Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
        intent.putExtra("option", 1);
        startActivity(intent);

    }

    public void goToRegistration(View view){
        Intent intent = new Intent(getApplicationContext(), Registration.class);
        startActivity(intent);
    }
    public void goToLogIn(View view){
        startActivity(new Intent(MainActivity.this,Login.class));
    }
}
