package com.example.attendance;

import android.widget.CheckBox;

public class studentValues {
    private String studentClassName;
    private String studentName;
    private CheckBox studentCheckBox;
    private String studentRoleNo;

    private  String totalStudentClassCount;

    public studentValues(String className,String totalClassStudents)
    {
        this.studentClassName=className;
        this.totalStudentClassCount=totalClassStudents;


    }

    public String getTotalStudentClassCount() {
        return totalStudentClassCount;
    }

    public studentValues(String className , String studentRoleNo, String studentName, CheckBox studentCheckBox)
    {
        this.studentClassName=className;
        this.studentName=studentName;
        this.studentRoleNo=studentRoleNo;
        this.studentCheckBox=studentCheckBox;
    }

    public String getStudentClassName() {
        return studentClassName;
    }

    public String getStudentName() {
        return studentName;
    }


    public String getStudentRoleNo() {
        return studentRoleNo;
    }


}
