package com.czarneBarany.korepetytorsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SubjectActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        final Spinner spinnerSubject = findViewById(R.id.spinnerSubject);
        final Spinner spinnerSubject2 = findViewById(R.id.spinnerSubject2);
        final Spinner spinnerLevel= findViewById(R.id.spinnerLevel);

        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(SubjectActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.subjects));
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(subjectAdapter);

        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(SubjectActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.level));
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(levelAdapter);


        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String [] subjects = getResources().getStringArray(R.array.subjects);
                ArrayAdapter<String> subject2Adapter;

                switch (subjects[position]) {
                    case "Nauki ścisłe": {
                        subject2Adapter = new ArrayAdapter<String>(SubjectActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectsScience));
                        subject2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        break;
                    }
                    case "Nauki humanistyczne": {
                        subject2Adapter = new ArrayAdapter<String>(SubjectActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectsHuman));
                        subject2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        break;
                    }
                    case "Nauki artystyczne": {
                        subject2Adapter = new ArrayAdapter<String>(SubjectActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectsArt));
                        subject2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        break;
                    }
                    case "Języki obce": {
                         subject2Adapter = new ArrayAdapter<String>(SubjectActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.subjectsLanguage));
                        subject2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + subjects[position]);
                }


                spinnerSubject2.setAdapter(subject2Adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        Button buttonNext = findViewById(R.id.buttonNext);
        final Integer option = getIntent().getIntExtra("option number",0);
        final Integer option2 = getIntent().getIntExtra("option number2",0);

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(option2==2) {
                        Intent intent = new Intent(getApplicationContext(), NewAdvertisementActivity.class);
                        //intent.putExtra("subject",spinnerSubject.getSelectedItem().toString() );
                        intent.putExtra("subject2", spinnerSubject2.getSelectedItem().toString());
                        intent.putExtra("level", spinnerLevel.getSelectedItem().toString());
                        startActivity(intent);
                    } else if(option==1){
                        Intent intent = new Intent(getApplicationContext(), ChooseTutorActivity.class);

                        startActivity(intent);
                    }

                }
            });



    }
}
