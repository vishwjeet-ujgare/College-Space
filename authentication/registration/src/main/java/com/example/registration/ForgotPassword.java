package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {

    TextView newUserSwiftLeftTV;
    Spinner user_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        newUserSwiftLeftTV = findViewById(R.id.newUserQuestionTV);
        newUserSwiftLeftTV.setVisibility(View.GONE);
        user_role = findViewById(R.id.role_spinner);


        new spinner_formating(user_role,getApplicationContext());
    }
}