package com.example.college_space.administration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.college_space.R;
import com.google.android.material.navigation.NavigationView;

public class Administration_dashboard extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
FragmentContainerView administration_fragment_container_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration_dashboard);
        navigationView = findViewById(R.id.administrationNavigationView);
        drawerLayout = findViewById(R.id.administrationDrawerLayout);
        toolbar = findViewById(R.id.administrationToolbar);
        administration_fragment_container_view=findViewById(R.id.administration_fragment_container_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("JSPM College Hadapsar");

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.start,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {



                }
                return false;
            }
        });

    }
}