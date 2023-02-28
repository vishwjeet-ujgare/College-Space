package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comman.datetime.datetime;

import java.util.ArrayList;
import java.util.Calendar;
public class TakeAttendance extends AppCompatActivity {
    ListView listView;
    ArrayList<studentValues> al;

    TextView dateTV, timeTV, weekDaySTV, classNameTV;
    static boolean clockSet = false;
    int hour, minute, sec;

    CountDownTimer countDownTimer;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        al = new ArrayList<>();

        dateTV = (TextView) findViewById(R.id.dateTakeAttendanceTV);
        timeTV = (TextView) findViewById(R.id.timeTakeAttendanceTV);
        weekDaySTV = (TextView) findViewById(R.id.weekDaySTV);
        classNameTV = (TextView) findViewById(R.id.classNameTakeAttendance);

        datetime dt = new datetime();
        classNameTV.setText(this.getIntent().getStringExtra("className"));
        dateTV.setText(dt.DAY + "-" + dt.MONTHS + "-" + dt.YEAR);
        weekDaySTV.setText(dt.WEEK_DAYS);



        // Create the list view and adapter
        listView = (ListView) findViewById(R.id.selectStudentListView);

        al.add(new studentValues(null, "101", "Vishwjeet Ramesh Ujgare", null));
        al.add(new studentValues(null, "102", "Sujal Ramesh Ujgare", null));
        al.add(new studentValues(null, "103", "Sampada Manoj ZOdge", null));
        al.add(new studentValues(null, "104", "Sanjana S Pawale", null));
        al.add(new studentValues(null, "205", "aa bb aaaaa", null));

        TakeAttendanceAdapter attendanceAdapter = new TakeAttendanceAdapter(this, R.layout.select_student_attendance_design_box, al);
        listView.setAdapter(attendanceAdapter);

        // Start a thread to update the time every second
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        handler.post(new Runnable() {
                            public void run() {
                                updateTime();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    // Update the time
    private void updateTime() {
        Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour > 12) {
            hour = hour - 12;
        }
        minute = cal.get(Calendar.MINUTE);
        sec = cal.get(Calendar.SECOND);
        timeTV.setText(String.format(" %d:%d:%d", hour, minute, sec));
    }

    public void onBackTakeAttendance(View view) {
        finish();
    }
}
