package com.example.registration;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Login_Registration extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        floatingActionButton = findViewById(R.id.fab_google);



        tabLayout.addTab(tabLayout.newTab().setText("login"));
        tabLayout.addTab(tabLayout.newTab().setText("Registration"));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.purple_500) );


        LoginRegistrationAdapter LoginRegistrationAdapter = new LoginRegistrationAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(LoginRegistrationAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Get the position of the selected tab
                int position = tab.getPosition();

                // Set the current item of the ViewPager to the selected tab position
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not used in this example
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not used in this example
            }
        });






    }
}
