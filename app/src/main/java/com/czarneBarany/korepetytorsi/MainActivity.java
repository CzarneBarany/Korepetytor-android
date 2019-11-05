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
        startActivity(new Intent(MainActivity.this, ItemsActivity.class));
    }

    public void goToRegistration(View view){
        startActivity(new Intent(MainActivity.this,Registration.class));
    }
    public void goToLogIn(View view){
        startActivity(new Intent(MainActivity.this,Login.class));
    }
}
