package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TakeAttendance extends AppCompatActivity {
    ListView listView;
    ArrayList<studentValues> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        listView = (ListView) findViewById(R.id.selectStudentListView);

        al.add(new studentValues(null, "101", "Vishwjeet Ramesh Ujgare", null));
        al.add(new studentValues(null, "102", "Sujal Ramesh Ujgare", null));
        al.add(new studentValues(null, "103", "Sampada Manoj ZOdge", null));
        al.add(new studentValues(null, "104", "Sanjana S Pawale", null));
        al.add(new studentValues(null, "205", "aa bb aaaaa", null));

        TakeAttendanceAdapter attendanceAdapter = new TakeAttendanceAdapter(this, R.layout.select_student_attendance_design_box, al);
        listView.setAdapter(attendanceAdapter);
    }
}