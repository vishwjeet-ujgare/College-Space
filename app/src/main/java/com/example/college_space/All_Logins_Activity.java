package com.example.college_space;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.registration.Registration;

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
}