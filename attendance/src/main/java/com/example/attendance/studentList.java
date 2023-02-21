package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class studentList extends AppCompatActivity {


    ListView listView;
    ArrayList<List> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        TextView tv = (TextView) findViewById(R.id.classNameTitle);
        tv.setText(this.getIntent().getStringExtra("className"));

        listView = findViewById(R.id.studentListView);


        List<String> list = new ArrayList<String>();
        list.add(0, "101");
        list.add(1, "Vishwjeet Ramesh Ujgare");


        List<String> list2 = new ArrayList<String>();
        list2.add(0, "102");
        list2.add(1, "Sujal Ramesh Ujgare");


        List<String> list3 = new ArrayList<String>();
        list3.add(0, "103");
        list3.add(1, "Sampada Manoj Zodge");


        List<String> list4 = new ArrayList<String>();
        list4.add(0, "104");
        list4.add(1, "Jatin S Ujgare");

        List<String> list5 = new ArrayList<String>();
        list5.add(0, "105");
        list5.add(1, "Mangesh Malhari Golhar");

arrayList=new ArrayList<>();
        arrayList.add(list);
        arrayList.add(list2);
        arrayList.add(list3);
        arrayList.add(list4);
        arrayList.add(list5);

        StudentListAdapter studentListAdapter = new StudentListAdapter(this, R.layout.student_list_box_design, arrayList);
        listView.setAdapter(studentListAdapter);
    }

    public void onBackBtnStudentList(View view)
    {
        finish();
    }
}