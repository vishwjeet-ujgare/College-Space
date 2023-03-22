package com.example.college_space.administration;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.college_space.R;
import com.example.common.dialog.CommonAlertDialog;
import com.example.registration.spinner_formating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.example.common.spinner.common_spinner_formating;

public class Fragment_Semester_Details extends Fragment {

    private View view;
    private Spinner selectDepartmentSpinner;
    private CommonAlertDialog commonAlertDialog;
    private List<Department_Values> department_valuesList;

    private EditText semNoET;
    private TextView startDateTV, endDateTV;
    private Button createSemBtn;
    DatePickerDialog datePickerDialog;
    private final String Request_URL="http://192.168.1.76:8080/College_Space_API/TransferRequest";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__semester__details, container, false);
        selectDepartmentSpinner = view.findViewById(com.example.common.R.id.common_spinner);
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        commonAlertDialog = new CommonAlertDialog();

        //getting id
        semNoET = view.findViewById(R.id.createdSemNumET);
        startDateTV = view.findViewById(R.id.semStartDateTV);
        endDateTV = view.findViewById(R.id.semEndDateTV);
        createSemBtn = view.findViewById(R.id.createSemBtn);

        // Create a StringRequest to fetch department data from server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Request_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                department_valuesList = new ArrayList<>();
                List<String> spinnerValuesList = new ArrayList<>();

                try {
                    // Convert the response into JSONObject
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonArray = jsonobject.optJSONArray("departments");

                    // Loop through the JSON array to fetch the department data
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);

                            // Fetch the department id and name from JSONObject
                            int dept_id = jsonObject1.optInt("id");
                            String dept_name = jsonObject1.optString("name").toString();

                            // Add department name into the spinnerValuesList
                            spinnerValuesList.add(dept_name);

                            // Create Department_Values object and add it to department_valuesList
                            Department_Values department = new Department_Values(dept_id, dept_name);
                            department_valuesList.add(department);
                        }

                    } else {
                        // Show error dialog if no departments found
                        commonAlertDialog.showMsgDialog(getContext(), "No department Found \n try after some time  or contact to administration", "No department Found");
                        return;
                    }

                    // Format the spinner using common_spinner_formating class
                    new common_spinner_formating(getContext(), selectDepartmentSpinner, spinnerValuesList);

                } catch (JSONException e) {
                    // Show error dialog if error occurred while converting data into JSON
                    commonAlertDialog.showErrorDialog(getContext(), "Error occur while converting data into JSON \n try again or contact to administration \n " + e, "Error Occurred");

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                commonAlertDialog.showErrorDialog(getContext(), "on Response error occurred  \n try again or contact to administration \n " + error, "Error Occurred");

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hm = new HashMap<>();
                hm.put("key_action", "selectDepartments");
                return hm;
            }
        };
        requestQueue.add(stringRequest);

        createSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateSemBtn();

            }
        });

        startDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(startDateTV);
            }
        });

        endDateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//call here datepicker and store date when click
                showDatePickerDialog(endDateTV);
            }
        });
        return view;

    }
    private void onCreateSemBtn() {

        // Group related variables together
        int semNum, selectedDeptPos;
        Date startDate, endDate;
        String startingDate, endingDate, selectedDept, name, id;

        // Get input values from UI components
        String semesterNumber = semNoET.getText().toString().trim();
        String startingDateStr = startDateTV.getText().toString();
        String endingDateStr = endDateTV.getText().toString();
        selectedDept = selectDepartmentSpinner.getSelectedItem().toString();
        selectedDeptPos = selectDepartmentSpinner.getSelectedItemPosition();

        // Validate input values
        if (TextUtils.isEmpty(selectedDept)) {
            commonAlertDialog.showMsgDialog(view.getContext(), "Please select a department / course.", "Select Department / Course");
            return;
        }
        if (TextUtils.isEmpty(semesterNumber) || Integer.parseInt(semesterNumber) <= 0) {
            commonAlertDialog.showMsgDialog(view.getContext(), "Please enter a valid academic semester number greater than 0.", "Enter Valid Semester Number");
            semNoET.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(startingDateStr) || TextUtils.isEmpty(endingDateStr)) {
            commonAlertDialog.showMsgDialog(view.getContext(), "Please select a semester start and end date. Note: The starting date cannot be greater than the end date.", "Select Dates");
            return;
        }

        // Convert the selected dates to Date objects
        startDate = Date.valueOf(startingDateStr);
        endDate = Date.valueOf(endingDateStr);

        // Check if starting date is greater than ending date
        if (startDate.after(endDate)) {
            commonAlertDialog.showMsgDialog(view.getContext(), "The starting date cannot be greater than the end date. Please select appropriate dates.", "Select Appropriate Dates");
            return;
        }

        // Get department ID and name from selected position
        Department_Values dept = department_valuesList.get(selectedDeptPos);
        id = String.valueOf(dept.getId());
        name = dept.getName();

        // Validate selected department
        if (!name.equals(selectedDept)) {
            commonAlertDialog.showMsgDialog(view.getContext(), "The selected department does not match. Please refresh the app and try again.", "Department Mismatch");
            return;
        }

        // Get input values as integers and strings

        startingDate = startingDateStr;
        endingDate = endingDateStr;

        // Display selected data to user
        commonAlertDialog.showMsgDialog(view.getContext(), "selected Position : " + selectedDeptPos + "\nID : " + id + "\nDepartment: " + name + "\nSemester: " + semesterNumber + "\nStart Date: " + startingDate + "\nEnd Date: " + endingDate, "Selected Data");
        insertSemesterData(semesterNumber, startingDate, endingDate, id);

    }

    private void showDatePickerDialog(TextView textView) {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);

                String selectedDate = year + "-" + month + "-" + dayOfMonth;
                textView.setText(selectedDate);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void insertSemesterData(String sem_num, String sem_start_date, String sem_end_date, String dept_id) {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Request_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {

                    clearAllFields();
                    commonAlertDialog.showMsgDialog(getContext(), "Data inserted successfully", "Successful");
                } else {
                    commonAlertDialog.showErrorDialog(getContext(), "Data insertion failed\n please try again..!", "Faild");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                commonAlertDialog.showErrorDialog(getContext(), "Error occurredm : " + error, "Error");

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("key_dept_id", dept_id);
                hm.put("key_sem_num", sem_num);
                hm.put("key_start_date", sem_start_date);
                hm.put("key_end_date", sem_end_date);
                hm.put("key_action", "createSemester");
                return hm;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void clearAllFields()
    {
        semNoET.setText("");
        startDateTV.setText("");
        endDateTV.setText("");
        selectDepartmentSpinner.setSelection(0);

    }
}