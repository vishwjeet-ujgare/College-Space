package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AttendanceAdapter  extends FragmentStateAdapter {
    public AttendanceAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch(position)
       {
           case 0:
               return new FragmentClass();
           case 1:
              return new FragmentAttendance();
           case 2:
               return new FragmentStatistics();
           default:
               return null;


       }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
