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


public class FragmentClass extends Fragment {

    ListView lv_class;
    View view;
    Context context;
    ArrayList al;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_class, container, false);
        lv_class = (ListView) view.findViewById(R.id.lv_class);

         al = new ArrayList();

        al.add(new studentValues("MCA-I", "101", "Vishwjeet Ramesh Ujgare", null));
        al.add(new studentValues("MCA-II", "102", "Sujal Ramesh Ujgare", null));
        al.add(new studentValues("MCA-III", "103", "Sampada Manoj ZOdge", null));
        al.add(new studentValues("MCA-IV", "104", "Sanjana S Pawale", null));
        al.add(new studentValues("MCA-V", "205", "aa bb aaaaa", null));


        ClassesListAdapter classAdapter = new ClassesListAdapter(getActivity(), R.layout.class_view_box_design, al);
        lv_class.setAdapter(classAdapter);
        lv_class.setClickable(true);
        lv_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, studentList.class);
                studentValues classNames=(studentValues) al.get(position);
                intent.putExtra("className",classNames.getStudentClassName());
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}