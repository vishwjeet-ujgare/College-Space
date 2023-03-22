package com.example.college_space.administration;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.college_space.R;
import com.example.common.Abbreviation;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Create_Dept extends Fragment {
    private  View view;
    private EditText courseNameET, abbET, totalSemET;
    private TextView startDateTV, endDateTV;
    private Date courseStartDate, courseEndDate;
    private DatePickerDialog datePickerDialog;

    private Button createBtn;
    private AlertDialog progressAlertDialog;
    private View progressDialogView;
    private SimpleDateFormat dateFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__create__dept, container, false);
        courseNameET = view.findViewById(R.id.deptCourseNameET);
        abbET = view.findViewById(R.id.abbrET);
        totalSemET = view.findViewById(R.id.totalSemET);

        startDateTV = view.findViewById(R.id.startingDateTV);
        endDateTV = view.findViewById(R.id.endingDateTV);
        createBtn = view.findViewById(R.id.createDeptBtn);

        //creating a progress dialog
        progressDialogView = getLayoutInflater().inflate(com.example.common.R.layout.progress_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(false);
        builder.setView(progressDialogView);
        progressAlertDialog = builder.create();

        //date formater
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        // Add an OnFocusChangeListener to the courseNameET EditText
        courseNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Handle when the user leaves the field after entering a string
                    String courseName = courseNameET.getText().toString().trim();
                    // update the abbreviation EditText with the first letters of each word
                    String abbreviation = Abbreviation.abbreviation(courseName);
                    abbET.setText(abbreviation);
                }
            }
        });
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        startDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        courseStartDate = new Date(calendar.getTimeInMillis());
                        startDateTV.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        endDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        courseEndDate = new Date(calendar.getTimeInMillis());
                        endDateTV.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateBtn();

            }
        });


        return view;
    }


    private void onCreateBtn() {
        String courseName = (courseNameET.getText().toString().trim());
        String Abbr = abbET.getText().toString().trim();
        String totalSemString = totalSemET.getText().toString().trim();

        String totalSem;

        if (courseName.equals("")) {
            showErrorDialog("Please Enter Course /Department name");
            return;
        } else if (Abbr.equals("")) {
            showErrorDialog("Abbreviation Field can't be empty");
            return;
        } else if (totalSemString.equals("") || totalSemString.equals("0")) {
            showErrorDialog("total semester cannot be empty or 0");
            return;
        } else if (courseStartDate == null) {
            showErrorDialog("Please select course starting date");
            return;
        } else if (courseEndDate == null) {
            showErrorDialog("Please select course ending date");
            return;
        }
        try {
            int tempTotalSem = Integer.parseInt(totalSemString);
            totalSem=String.valueOf(tempTotalSem);
        } catch (NumberFormatException e) {
            showErrorDialog("Please enter a valid number for Total Semesters" + "\n" + e);
            return;
        }

        if (courseStartDate.compareTo(courseEndDate) > 0) {
            showErrorDialog("Course start date cannot be after course end date");
            return;
        }

        sendData(courseName, Abbr, totalSem, courseStartDate, courseEndDate);
        System.out.println("Course Name : " + courseName + "\n" + "abbr : " + Abbr + "\n" + "Totalsem : " + totalSem + "\nStarting date :" + courseStartDate + "\n Ending date : " + courseEndDate);
    }



    private void sendData(String courseName, String abbr, String totalSem, Date startDate, Date endDate) {
        progressAlertDialog.show();

        RequestQueue requestQueue =Volley.newRequestQueue(view.getContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.1.76:8080/College_Space_API/TransferRequest", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressAlertDialog.dismiss();
                if(response.equals("Success"))
                {
                    clearAllFields();
                    courseNameET.requestFocus();
                    showMSGDialog(response,"Response Message");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressAlertDialog.dismiss();
                showErrorDialog("Error has  occurred during sending a data to server : \n Please Try Again \n Error Description : "+error);
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hm =new HashMap<>();
                hm.put("key_courseDept_name",courseName);
                hm.put("Key_abbr",abbr);
                hm.put("key_totalSem",totalSem );
                hm.put("key_course_startDate", dateFormat.format(courseStartDate));
                hm.put("key_course_endDate", dateFormat.format(courseEndDate));
                hm.put("key_action","createCourse");
                return hm;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void showMSGDialog(String msg,String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(title).setCancelable(false).setMessage(msg);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void showErrorDialog(String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Error").setCancelable(false).setMessage(errorMessage);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void clearAllFields()
    {

        courseNameET.setText("");
        abbET.setText("");
        totalSemET.setText("");
        startDateTV.setText("");
        endDateTV.setText("");
        courseStartDate = null;
        courseEndDate = null;

    }
}