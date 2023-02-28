package com.example.college_space;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeWithoutLogin extends AppCompatActivity {
    Toolbar withoutLoginToolbar;
    DrawerLayout homeWithoutLoginDrawerLayout;
    NavigationView homeWithoutLoginSideNavigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
     BottomNavigationView homeWithoutLoginBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_without_login);

        withoutLoginToolbar = findViewById(R.id.withoutLoginToolbar);
        homeWithoutLoginDrawerLayout = findViewById(R.id.homeWithoutLoginDrawerLayout);
        homeWithoutLoginSideNavigationView = findViewById(R.id.homeWithoutLoginSideNavigationView);
        homeWithoutLoginBottomNavigation=findViewById(R.id.homeWithoutLoginBottomNavigation);

        setSupportActionBar(withoutLoginToolbar);
        if(getSupportActionBar()!=null)
        {

            getSupportActionBar().setTitle("JSPM");
        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, homeWithoutLoginDrawerLayout, withoutLoginToolbar, R.string.start, R.string.close);
        homeWithoutLoginDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavController navController= Navigation.findNavController(this, R.id.homeWithoutLoginFrameLayout);
        NavigationUI.setupWithNavController(homeWithoutLoginBottomNavigation,navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.home_without_login_toolbar_opt, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.menuStudentLoginHWLT)
        {
            Toast.makeText(this,"Clicked on student login ",Toast.LENGTH_SHORT).show();
        }
        else if(itemId==R.id.menuOtherLoginHWLT)
        {
            Toast.makeText(this,"Clicked on Other  login ",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}