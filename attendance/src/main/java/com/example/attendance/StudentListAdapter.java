package com.example.attendance;

import android.content.Context;
import android.service.controls.templates.TemperatureControlTemplate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class StudentListAdapter extends ArrayAdapter {
    ArrayList<List> al;

    public StudentListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<List> objects) {
        super(context, resource, objects);
        al = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.student_list_box_design, null);

        TextView rollNo_tv=(TextView) view.findViewById(R.id.studentRollNoTV);
        TextView studentName_tv=(TextView) view.findViewById(R.id.studentNameTV);

        List list=al.get(position);
        rollNo_tv.setText(list.get(0).toString());
        studentName_tv.setText(list.get(1).toString());

        return view;
    }

}
