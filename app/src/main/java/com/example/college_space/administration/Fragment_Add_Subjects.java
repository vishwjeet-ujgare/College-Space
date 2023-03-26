package com.example.college_space.administration;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.college_space.R;
import com.example.common.Abbreviation;
import com.example.common.dialog.CommonAlertDialog;
import com.example.common.spinner.common_spinner_formating;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.StatementEvent;

public class Fragment_Add_Subjects extends Fragment {
    private Context context;

    private Button selectDeptSemBtn, saveAddAnotherBtn, addSubjectsDataBtn;
    private LinearLayout subjectDetailsLinearLayout, selectedDataLinearLayout;


    private View custom_alert_dialog_layout = null, view;
    private Spinner departmentSpinner, semNumSpinner, teachersSpinner;
    private final String Request_URL = "http://192.168.1.76:8080/College_Space_API/TransferRequest";
    private CommonAlertDialog commonAlertDialog;
    private List<Department_Values> department_valuesList;
    private List<Teacher_Values> teacher_values_list;
    private List<SubjestTableValues> subject_table_values_list;
    private List<String> semList = null;
    private TextView selectedDepNameTV, selectedSemNameTV;

    private int selectedDeptID = 0, selectedSem = 0;
    TextView selectSemNumTVCustomeDia;

    private TableLayout subjectTable;

    private EditText subjectNameET, subjectAbbrET;

    private int srno = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__add__subjects, container, false);
        context = view.getContext();


        selectDeptSemBtn = view.findViewById(R.id.selectDeptSemBtn);
        subjectDetailsLinearLayout = view.findViewById(R.id.subjectDetailsLinearLayout);
        selectedDataLinearLayout = view.findViewById(R.id.selectedDataLinearLayout);
        saveAddAnotherBtn = view.findViewById(R.id.saveAddAnotherBtn);
        subjectNameET = view.findViewById(R.id.subjectNameET);
        subjectAbbrET = view.findViewById(R.id.subjectAbbrET);
        addSubjectsDataBtn = view.findViewById(R.id.addSubjectsDataBtn);


        subjectTable = view.findViewById(R.id.subject_table);
        subject_table_values_list = new ArrayList<>();

        saveAddAnotherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open dialog to add new subject
                String subject_name = subjectNameET.getText().toString().trim();
                String subjectAbbr = subjectAbbrET.getText().toString().trim();
                String teacherName = teachersSpinner.getSelectedItem().toString();
                int teacherID = teacher_values_list.get(teachersSpinner.getSelectedItemPosition()).getId();

                // Check if all fields are filled out
                if (subject_name.isEmpty() || subjectAbbr.isEmpty() || teacherName.isEmpty()) {
                    // Display error message
                    commonAlertDialog.showErrorDialog(context, "Please Fill out all values", "Fields can't be Empty");
                    return;
                }

                SubjestTableValues newSubjestTableValues = new SubjestTableValues(subject_name, subjectAbbr, teacherName);

                // Add subject to ArrayList
                subject_table_values_list.add(newSubjestTableValues);

                // Add subject to table
                addSubjectTableRow(newSubjestTableValues);


            }
        });


        addSubjectsDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData(custom_alert_dialog_layout.getContext());
            }
        });

        subjectNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Handle when the user leaves the field after entering a string
                    String courseName = subjectNameET.getText().toString().trim();
                    // update the abbreviation EditText with the first letters of each word
                    String abbreviation = Abbreviation.abbreviation(courseName);
                    subjectAbbrET.setText(abbreviation);
                }
            }
        });

        //initialize the inflater object
        LayoutInflater customInflater = LayoutInflater.from(context);

        //getting cusotm alert dialog layout
        custom_alert_dialog_layout = customInflater.inflate(R.layout.custom_alert_dialog_layout, null);

        View includeDepartmentSpinner = custom_alert_dialog_layout.findViewById(R.id.deptSpinnerInclude);
        departmentSpinner = includeDepartmentSpinner.findViewById(com.example.common.R.id.common_spinner);


        View includeSemNumSpinner = custom_alert_dialog_layout.findViewById(R.id.semNumSpinnerInclude);
        semNumSpinner = includeSemNumSpinner.findViewById(com.example.common.R.id.common_spinner);

        selectSemNumTVCustomeDia = custom_alert_dialog_layout.findViewById(R.id.selectSemTVCustomeDialog);

        View includeTeachersSpinner = view.findViewById(R.id.selectTeacherInclude);
        teachersSpinner = includeTeachersSpinner.findViewById(com.example.common.R.id.common_spinner);

        setDepartments(custom_alert_dialog_layout.getContext());
        setAllTeachers(custom_alert_dialog_layout.getContext());

//text view
        selectedDepNameTV = view.findViewById(R.id.selectedDepNameTV);
        selectedSemNameTV = view.findViewById(R.id.selectedSemNameTV);
        //geting commenon alertbox for displaying error and msg
        commonAlertDialog = new CommonAlertDialog();

        makeHideOnNotSelectedDeptSem();


        selectDeptSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectingDeptSem();
            }


        });


        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Department_Values department_values = department_valuesList.get(parent.getSelectedItemPosition());
                int deptID = department_values.getId();

                getMaxSemCountForDept(department_values.getId());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;

    }

    private void onSelectingDeptSem() {

        //alertdialBuilder
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        //custome view for alertboc
        alertDialogBuilder.setView(custom_alert_dialog_layout);
        alertDialogBuilder.setTitle("Select Department and Semester");

        alertDialogBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do something when the Yes button is clicked
                if (semList == null) {
                    makeHideOnNotSelectedDeptSem();
                } else {
                    Department_Values department_values = department_valuesList.get(departmentSpinner.getSelectedItemPosition());
                    selectedDeptID = department_values.getId();

                    String tempSemName = semList.get(semNumSpinner.getSelectedItemPosition());
                    String[] separateSemNo = tempSemName.split(" ");
                    selectedSem = Integer.parseInt(separateSemNo[1]);

                    selectedDepNameTV.setText(department_values.getName());
                    selectedSemNameTV.setText(tempSemName);

                    selectedDataLinearLayout.setVisibility(View.VISIBLE);
                    subjectDetailsLinearLayout.setVisibility(View.VISIBLE);

//                    String dep = departmentSpinner.getSelectedItem().toString();


                }


            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do something when the No button is clicked
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (custom_alert_dialog_layout.getParent() != null) {
                    ((ViewGroup) custom_alert_dialog_layout.getParent()).removeView(custom_alert_dialog_layout); // Remove the child view from its parent

                }


            }
        });
    }

    private void setDepartments(Context custome_alert_context) {

        RequestQueue requestQueue = Volley.newRequestQueue(custome_alert_context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Request_URLS.AdminRequset_URLS, new Response.Listener<String>() {
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
                    new common_spinner_formating(custom_alert_dialog_layout.getContext(), departmentSpinner, spinnerValuesList);

                } catch (JSONException e) {
                    // Show error dialog if error occurred while converting data into JSON
                    commonAlertDialog.showErrorDialog(getContext(), "Error occur while converting  department data into JSON \n try again or contact to administration \n " + e, "Error Occurred");

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                commonAlertDialog.showErrorDialog(custome_alert_context, "Error while fetching departments : \nError Details : " + error, "Error Occured");
                return;
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("key_action", "selectDepartments");
                return hm;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void getMaxSemCountForDept(int course_id) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Request_URLS.AdminRequset_URLS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    commonAlertDialog.showErrorDialog(context, "Sorry, no semesters were found for the selected course in the " + departmentSpinner.getSelectedItem().toString() + " department. In order to add subjects, you must first have at least one semester created for this course. Please create a semester first and then try again.", "No semester Details Found");
                    selectSemNumTVCustomeDia.setVisibility(View.GONE);
                    semNumSpinner.setVisibility(View.GONE);
                    semList = null;

                    return;
                } else {
                    selectSemNumTVCustomeDia.setVisibility(View.VISIBLE);
                    semNumSpinner.setVisibility(View.VISIBLE);
                }
                try {
                    int maxSemCount = Integer.parseInt(response);
                    semList = new ArrayList<String>();
                    for (int i = 1; i <= maxSemCount; i++) {
                        semList.add("Semester " + i);
                    }
                    new common_spinner_formating(custom_alert_dialog_layout.getContext(), semNumSpinner, semList);

                } catch (Exception e) {
                    commonAlertDialog.showErrorDialog(context, "Error Occurred \n" + e, "Error Occurred");
                    return;
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("key_action", "maxSemForDept");
                hm.put("key_course_id", String.valueOf(course_id));
                return hm;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void makeHideOnNotSelectedDeptSem() {
        selectedDeptID = 0;
        selectedSem = 0;

        selectedDataLinearLayout.setVisibility(View.GONE);
        subjectDetailsLinearLayout.setVisibility(View.GONE);

        selectedDepNameTV.setText("");
        selectedSemNameTV.setText("");

    }

    private void setAllTeachers(Context custome_alert_context) {
        RequestQueue requestQueue = Volley.newRequestQueue(custome_alert_context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Request_URLS.AdminRequset_URLS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                teacher_values_list = new ArrayList<>();
                List<String> spinnerValuesList = new ArrayList<>();

                try {
                    // Convert the response into JSONObject
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonArray = jsonobject.optJSONArray("teachers");

                    // Loop through the JSON array to fetch the department data
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);

                            // Fetch the department id and name from JSONObject
                            int teacher_id = jsonObject1.optInt("id");
                            String first_name = jsonObject1.optString("first_name").toString();
                            String last_name = jsonObject1.optString("last_name").toString();
                            String email = jsonObject1.optString("email").toString();

                            String final_string = first_name + " " + last_name + " || " + email;

                            // Add department name into the spinnerValuesList
                            spinnerValuesList.add(final_string);

                            // Create Department_Values object and add it to department_valuesList
                            Teacher_Values teacher_values = new Teacher_Values(teacher_id, first_name, last_name, email);
                            teacher_values_list.add(teacher_values);
                        }

                    } else {
                        // Show error dialog if no departments found
                        commonAlertDialog.showMsgDialog(getContext(), "Currently, there are no registered teachers in the system. Please add a teacher to continue. or do the teacher registration process", "No Teachers Found");

                        return;
                    }

                    // Format the spinner using common_spinner_formating class
                    new common_spinner_formating(custom_alert_dialog_layout.getContext(), teachersSpinner, spinnerValuesList);

                } catch (JSONException e) {
                    // Show error dialog if error occurred while converting data into JSON
                    commonAlertDialog.showErrorDialog(getContext(), "Error occur while converting data into JSON \n try again or contact to administration \n " + e, "Error Occurred");

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                commonAlertDialog.showErrorDialog(custome_alert_context, "Error while fetching Teachers data : \nError Details : " + error, "Error Occured");
                return;
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("key_action", "selectTeachers");
                return hm;
            }
        };
        requestQueue.add(stringRequest);


    }

    private void addSubjectTableRow(SubjestTableValues subjestTableValues) {
        // Create new row
        TableRow row = new TableRow(context);

        // Create TextViews for each column
        TextView srNoTextView = new TextView(context);
        TextView nameTextView = new TextView(context);
        TextView teacherTextView = new TextView(context);
        TextView abbreviationTextView = new TextView(context);

        srNoTextView.setTextColor(Color.BLACK);
        nameTextView.setTextColor(Color.BLACK);
        teacherTextView.setTextColor(Color.BLACK);
        abbreviationTextView.setTextColor(Color.BLACK);

        srNoTextView.setBackgroundResource(R.drawable.tab_layout_border);
        nameTextView.setBackgroundResource(R.drawable.tab_layout_border);
        teacherTextView.setBackgroundResource(R.drawable.tab_layout_border);
        abbreviationTextView.setBackgroundResource(R.drawable.tab_layout_border);

        srNoTextView.setPadding(10, 5, 10, 5);
        nameTextView.setPadding(10, 5, 10, 5);
        teacherTextView.setPadding(10, 5, 10, 5);
        abbreviationTextView.setPadding(10, 5, 10, 5);
// Set the bottom margin on the child views
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 10); // Set the bottom margin to 16dp
        srNoTextView.setLayoutParams(params);
        nameTextView.setLayoutParams(params);
        teacherTextView.setLayoutParams(params);
        abbreviationTextView.setLayoutParams(params);


        srNoTextView.setTextSize(20);
        nameTextView.setTextSize(20);
        teacherTextView.setTextSize(20);
        abbreviationTextView.setTextSize(20);

        // Set text for TextViews

        srNoTextView.setText(String.valueOf(srno + 1));
        nameTextView.setText(subjestTableValues.getName());
        teacherTextView.setText(subjestTableValues.getTeacher());
        abbreviationTextView.setText(subjestTableValues.getAbbreviation());

        // Create delete button
        Button deleteButton = new Button(context);
        deleteButton.setBackgroundResource(com.example.common.R.drawable.edit_text_bg);
        deleteButton.setBackgroundResource(R.color.red); // Set background color
        deleteButton.setTextColor(Color.WHITE); // Set text color
        deleteButton.setTypeface(null, Typeface.BOLD); // Set text style to bold
        deleteButton.setText("Delete");

        // Add columns to the row
        row.addView(srNoTextView);
        row.addView(nameTextView);
        row.addView(teacherTextView);
        row.addView(abbreviationTextView);
        row.addView(deleteButton);


        // Add row to the table
        subjectTable.addView(row);
        // Add click listener to delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get index of row
                int rowIndex = subjectTable.indexOfChild((View) v.getParent()) - 1;

                // Remove subject from ArrayList
                subject_table_values_list.remove(rowIndex);

                // Remove row from table
                subjectTable.removeView((View) v.getParent());
            }
        });


    }

    private void sendData(Context custome_alert_context) {
        RequestQueue requestQueue = Volley.newRequestQueue(custome_alert_context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Request_URLS.AdminRequset_URLS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                commonAlertDialog.showErrorDialog(context, "Here is Reponse : \n" + response, "Studnet data");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Gson gson = new Gson();
                String jsonResponse = gson.toJson(subject_table_values_list);
                // Create a JSON object to store the JSON array as a value of a key
                JSONObject responseObject = new JSONObject();
                try {
                    responseObject.put("sem_subject_details", new JSONArray(jsonResponse));
                } catch (JSONException e) {
                    commonAlertDialog.showMsgDialog(context, "Error Occured " + e, "Error");

                }
                HashMap<String, String> hm = new HashMap<>();
                hm.put("key_action", "insertSubData");
                hm.put("key_response", responseObject.toString());
                hm.put("key_dept_id",String.valueOf(selectedDeptID));
                hm.put("key_sem",String.valueOf(selectedSem));

                return hm;
            }
        };
        requestQueue.add(stringRequest);

    }
}