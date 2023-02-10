package com.example.timetbale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TimePicker;

public class addNewSession extends AppCompatActivity {

    Spinner startTimingSpinner,endTimingSpinner;
    TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_session);

        startTimingSpinner=findViewById(R.id.sessionStartingTimw);
        endTimingSpinner=findViewById(R.id.sessionEndingTime);

        startTimingSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog=new TimePickerDialog(addNewSession.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    }
                },0,0,false);
            }
        });
    }
}