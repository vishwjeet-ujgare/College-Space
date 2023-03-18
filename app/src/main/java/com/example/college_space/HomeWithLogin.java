package com.example.college_space;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.attendance.AttendanceDashboard;
import com.example.attendance.TakeAttendance;
import com.example.timetbale.TTDashBoard;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeWithLogin extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_with_login);

        toolbar = findViewById(R.id.homeForLoginToolbar);
        drawerLayout = findViewById(R.id.homeForLoginDrawerLayout);
        bottomNavigationView = findViewById(R.id.homeForLoginBottomNavigationView);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle("JSPM College");
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.homeForLoginBottomTimetableMI:
                        Toast.makeText(HomeWithLogin.this, "You clicked on timetable", Toast.LENGTH_SHORT).show();
                        intent = new Intent(HomeWithLogin.this, TTDashBoard.class);
                        startActivity(intent);
                        return true;

                    case R.id.homeForLoginBottomAttendanceMI:
                        Toast.makeText(HomeWithLogin.this, "You clicked on Attendance", Toast.LENGTH_SHORT).show();
                        intent = new Intent(HomeWithLogin.this, AttendanceDashboard.class);
                        startActivity(intent);
                        return true;


                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.home_for_login_toolbar_opt, menu);
        return super.onCreateOptionsMenu(menu);
    }


}