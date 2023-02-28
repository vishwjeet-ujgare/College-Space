package com.example.college_space;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import com.example.attendance.AttendanceDashboard;

import com.example.registration.Registration;
import com.example.timetbale.TTDashBoard;
//import com.example.admin_representation.AdminDashboard;


public class All_Logins_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_logins);
    }

    public void registrationForm(View view)
    {
        Intent intent=new Intent(All_Logins_Activity.this, Registration.class);
        startActivity(intent);

    }

    public void onAdminDash(View view)
    {
//        Intent intent =new Intent(All_Logins_Activity.this, AdminDashboard.class);
//        startActivity(intent);
    }
    public void onTimeTableBtn(View view)
    {

Intent intent=new Intent(All_Logins_Activity.this, TTDashBoard.class);
startActivity(intent);

    }

    public void onAttendaceBtn(View view)
    {
        Intent intent=new Intent(All_Logins_Activity.this, AttendanceDashboard.class);
        startActivity(intent);
    }
}