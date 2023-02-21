package com.example.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TakeAttendanceAdapter extends ArrayAdapter {
    ArrayList al;

    public TakeAttendanceAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
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
        View view = layoutInflater.inflate(R.layout.select_student_attendance_design_box, null);


        TextView rollNo_tv = (TextView) view.findViewById(R.id.selectStdRollNoTV);
        TextView studentName_tv = (TextView) view.findViewById(R.id.selectStdNameTV);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.selectStdCheck);

        studentValues studentValues = (studentValues) al.get(position);
        rollNo_tv.setText(studentValues.getStudentRoleNo());
        studentName_tv.setText(studentValues.getStudentName());


        return view;

    }
}
