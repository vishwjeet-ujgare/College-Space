package com.example.college_space;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

         Thread thread =new Thread(){
             public void run(){
             try{
                 sleep(3000);

             }catch(Exception e){

             }finally{
                 Intent intent=new Intent(SplashActivity.this,All_Logins_Activity.class);
                 startActivity(intent);
             }
         }
         };
         thread.start();

    }
}