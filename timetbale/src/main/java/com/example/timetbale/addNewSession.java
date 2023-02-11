package com.example.timetbale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addNewSession extends AppCompatActivity {


    TimePickerDialog timePickerDialog;
    LinearLayout startTimingLL,endTimingLL;
    TextView StartSessionTV,EndSessionTV;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_session);

        startTimingLL = (LinearLayout) findViewById(R.id.llStartSession);
        endTimingLL = (LinearLayout) findViewById(R.id.llEndSession);

        StartSessionTV=(TextView)findViewById(R.id.tvStartSession) ;
        EndSessionTV=(TextView)findViewById(R.id.tvEndSession);

      imageView=(ImageView)findViewById(R.id.cancelNewSessionImg);
      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              addNewSession.super.onBackPressed();
          }
      });


        List<String> list=new ArrayList<String>();
        list.add("08:30 am");

        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR) ;
        int  min =c.get(Calendar.MINUTE);

    startTimingLL.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(addNewSession.this, "Hello", Toast.LENGTH_SHORT).show();
            timePickerDialog=new TimePickerDialog(addNewSession.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hour, int min) {
                    String AM_PM ;
                    if(hour < 12) {
                        AM_PM = "AM";

                    } else {
                        AM_PM = "PM";
                        hour=hour-12;
                    }
                    StartSessionTV.setText(hour+" : "+min+" "+AM_PM);
                }
            },hour ,min,false);
            timePickerDialog.show();
        }
    });


        endTimingLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(addNewSession.this, "Hello", Toast.LENGTH_SHORT).show();
                timePickerDialog=new TimePickerDialog(addNewSession.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int min) {
                        String AM_PM ;
                        if(hour < 12) {
                            AM_PM = "AM";

                        } else {
                            AM_PM = "PM";
                            hour=hour-12;
                        }
                        EndSessionTV.setText(hour+" : "+min+" "+AM_PM);
                    }
                },hour ,min,false);
                timePickerDialog.show();
            }
        });


    }
}