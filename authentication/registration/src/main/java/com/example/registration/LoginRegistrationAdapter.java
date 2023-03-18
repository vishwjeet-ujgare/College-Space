package com.example.registration;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginRegistrationAdapter extends FragmentPagerAdapter {

    Context context;
    int totaltabs;
    Fragment fragment;
    public LoginRegistrationAdapter( FragmentManager fm, Context context ,int totaltabs) {
//        super(fm);
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context=context;
        this.totaltabs=totaltabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                fragment=new FragmentLogin();
                return  fragment;
            case 1:
                 fragment=new FragmentRegistration();
                return fragment;
            default:
                return fragment;
        }

    }

    @Override
    public int getCount() {
        return totaltabs;
    }
}
