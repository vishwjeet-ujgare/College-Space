package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class AttendanceDashboard extends AppCompatActivity {
TabLayout tabLayout;
ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_dashboard);

        tabLayout=(TabLayout) findViewById(R.id.tabLayoutDash);
        viewPager2=(ViewPager2) findViewById(R.id.viewPager2Layout);

        AttendanceAdapter attendanceAdapter=new AttendanceAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(attendanceAdapter);
    }

}