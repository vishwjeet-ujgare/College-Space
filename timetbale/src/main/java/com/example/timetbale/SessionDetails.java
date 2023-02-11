package com.example.timetbale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SessionDetails extends AppCompatActivity {
TextView subjectName,roomNoTV,sessionTimming;
ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_details);

        subjectName=findViewById(R.id.subjectNameTVSD);
        roomNoTV=findViewById(R.id.roomNoTVSD);
        sessionTimming=findViewById(R.id.sessionTimeTVSD);

        back=findViewById(R.id.backIVSessionDetail);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionDetails.super.onBackPressed();
            }
        });

        Intent intent=this.getIntent();

        if(intent !=null)
        {
            String subject=intent.getStringExtra("subjectName");
            String roomNo=intent.getStringExtra("roomNo");
            String startTime=intent.getStringExtra("sessionStartTime");
            String endTime=intent.getStringExtra("sessionEndTime");

            subjectName.setText(subject);
            sessionTimming.setText(startTime+" : "+endTime);
            roomNoTV.setText(roomNo);
        }
    }
}