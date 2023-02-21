package com.example.attendance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AttendanceClassesListAdapter extends ArrayAdapter {

    ArrayList al;

    public AttendanceClassesListAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        al = objects;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.take_attendance_classes_design_box, null);

        TextView className = (TextView) view.findViewById(R.id.attendanceClassNameTV);
        TextView TotalClassStudentCount = (TextView) view.findViewById(R.id.attendanceTotalStdClassCountTV);

        studentValues studentValues = (studentValues) al.get(position);
        className.setText(studentValues.getStudentClassName());
        TotalClassStudentCount.setText(studentValues.getTotalStudentClassCount());


        return view;
    }
}
