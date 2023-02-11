package com.example.timetbale;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.example.timetbale.databinding.FragmentDayviewBinding;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DayviewFragment extends Fragment {

    public DayviewFragment() {
        // Required empty public constructor
    }

    FragmentDayviewBinding fragmentDayviewBinding;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        fragmentDayviewBinding = FragmentDayviewBinding.inflate(getLayoutInflater());
        View view = fragmentDayviewBinding.getRoot();
        // Inflate the layout for this fragment
//        inflater.inflate(R.layout.fragment_dayview, container, false)
        int imgeId = R.drawable.circle;
        String[] subjectName = {"Java Programming", "Computer Networking", "Python", "Optimisation Techniques", "Data Structure and Data Algorithms"};

        String[] sessionStartTime = {"08.30 am", "09:30 am", "10:30 am", "11:30 am", "12:30 pm"};
        String[] sessionEndTime = {"09:30 am", "10:30 am", "11:30 am", "12:30 pm", "01.30 pm"};
        String[] roomNo = {"A-Block 201", "B-Block 202", "C-Block 203", "D-Block 204", "E-Block 205"};

        ArrayList<SessionInformationValues> sessionsArrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
//            (String sessionStartTime, String sessionEndTime, String subjectName, String roomNo, Color
//            color,int imageID)
            SessionInformationValues sessionInfoValues = new SessionInformationValues(sessionStartTime[i], sessionEndTime[i], subjectName[i], roomNo[i], Color.GREEN, imgeId);
            sessionsArrayList.add(sessionInfoValues);
        }

        DayviewListAdapter dayviewListAdapter = new DayviewListAdapter(context, sessionsArrayList);
        fragmentDayviewBinding.dayViewLV.setAdapter(dayviewListAdapter);
        fragmentDayviewBinding.dayViewLV.setClickable(true);
        fragmentDayviewBinding.dayViewLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent =new Intent(context,SessionDetails.class);
                intent.putExtra("sessionStartTime",sessionStartTime[i]);
                intent.putExtra("sessionEndTime",sessionEndTime[i]);
                intent.putExtra("subjectName",subjectName[i]);
                intent.putExtra("roomNo",roomNo[i]);
                startActivity(intent);

            }
        });

        return view;
    }
}