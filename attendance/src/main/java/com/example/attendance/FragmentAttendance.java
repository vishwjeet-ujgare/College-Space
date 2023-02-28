package com.example.attendance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentAttendance extends Fragment {

    ListView listView;
    View view;
    Context context;
    ArrayList<studentValues>  al;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentV
        context=getContext();
        view=inflater.inflate(R.layout.fragment_attendance, container, false);
        listView=view.findViewById(R.id.attendanceClassesListView);

        al = new ArrayList<>();

        al.add(new studentValues("MCA-I","4"));
        al.add(new studentValues("MCA-II","3"));
        al.add(new studentValues("MCA-III","3"));
        al.add(new studentValues("MCA-IV","100"));
        al.add(new studentValues("MCA-V","200"));

        AttendanceClassesListAdapter attendanceClassesListAdapter =new AttendanceClassesListAdapter(getActivity(),R.layout.take_attendance_classes_design_box,al);
        listView.setAdapter(attendanceClassesListAdapter);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,TakeAttendance.class);
                studentValues classNames=(studentValues) al.get(position);

                intent.putExtra("className",classNames.getStudentClassName());
                startActivity(intent);
            }
        });

        return view;
    }
}