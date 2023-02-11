package com.example.timetbale;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TTDashboardFragment extends Fragment {

    LinearLayout newSessionLL;

    public TTDashboardFragment() {
        // Required empty public constructor




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_t_t_dashboard, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        newSessionLL=(LinearLayout) getView().findViewById(R.id.newSessionFromDashLL);
        newSessionLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),addNewSession.class);
                startActivity(intent);
            }
        });
    }
}