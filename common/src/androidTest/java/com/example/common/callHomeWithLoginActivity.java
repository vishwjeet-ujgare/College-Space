package com.example.common;

import android.app.Activity;
import android.content.Intent;

import com.example.college_space.HomeWithLogin;

public class callHomeWithLoginActivity {
    private Activity activity;

    public callHomeWithLoginActivity(Activity activity) {
        this.activity = activity;
        callHomeWithLogin();
    }

    public void callHomeWithLogin() {
        Intent intent = new Intent(activity, HomeWithLogin.class);
        activity.startActivity(intent);
    }
}