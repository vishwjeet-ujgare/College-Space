package com.example.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;


public class FragmentRegistration extends Fragment {

    Spinner role_spinner;
    TextView firstNameRET, lastnameRET, mblNoRET, usernameRET, emailRET, createPassRET, secreteKeyRET, confirmPassRET, user_role;
    RadioGroup genderRadioGroup;
    String role;
    View view, progressDialogView;
    ProgressBar progressBar;
    String selectedGender;
    Button registrationBtn;
    AlertDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registration, container, false);

        progressDialogView = getLayoutInflater().inflate(com.example.common.R.layout.progress_dialog, null);
        progressBar = progressDialogView.findViewById(com.example.common.R.id.progress_bar);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(false);
        builder.setView(progressDialogView);
        progressDialog = builder.create();


        role_spinner = view.findViewById(R.id.role_spinner);
        firstNameRET = view.findViewById(R.id.firstNameRET);
        lastnameRET = view.findViewById(R.id.lastnameRET);
        mblNoRET = view.findViewById(R.id.mblNoRET);
        usernameRET = view.findViewById(R.id.usernameRET);
        emailRET = view.findViewById(R.id.emailRET);
        createPassRET = view.findViewById(R.id.createPassRET);
        confirmPassRET = view.findViewById(R.id.confirmPassRET);
        secreteKeyRET = view.findViewById(R.id.secreteKeyRET);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);

        registrationBtn = view.findViewById(R.id.registrationRBtn);

//        To set a combobox
        new spinner_formating(role_spinner, view.getContext());


//        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.spinner_role_items, R.layout.registration_selected_color_spinner_layout);
//        spinnerAdapter.setDropDownViewResource(R.layout.registration_spinner_dropdown_layout);
//        role_spinner.setAdapter(spinnerAdapter);


        role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                role = adapterView.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.maleGender) {

                    selectedGender = "male";

                } else if (checkedId == R.id.femaleGender) {
                    selectedGender = "female";
                } else if (checkedId == R.id.otherGender) {
                    selectedGender = "other";
                }
            }
        });


        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegistrationBtn(view);
            }
        });

        return view;
    }


    private void onRegistrationBtn(View view) {

        progressDialog.show();
        String firstName = firstNameRET.getText().toString();
        String lastName = lastnameRET.getText().toString();


        String username = usernameRET.getText().toString();
        String mblNo = mblNoRET.getText().toString();

        String email = emailRET.getText().toString();
        String secretKey = secreteKeyRET.getText().toString();

        String creatPass = createPassRET.getText().toString();
        String confirmPass = confirmPassRET.getText().toString();


        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.0.107:8080/College_Space_API/Registration", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                System.out.println("responsse"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                System.err.println(error);
                Toast.makeText(view.getContext(),"Error"+error,Toast.LENGTH_LONG).show();
            }
        }

        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();

                hm.put("key_first_name", firstName);
                hm.put("key_last_name", lastName);
                hm.put("key_mbl_no", mblNo);
                hm.put("key_username", username);
                hm.put("key_gender", selectedGender);
                hm.put("key_email", email);
                hm.put("key_pass", confirmPass);
                hm.put("key_user_role", role);
                hm.put("key_action", "registration");

                return hm;
            }
        };

        requestQueue.add(stringRequest);

//        String userRole = user_role.getText().toString();

//        System.out.println("First Name: " + firstName +
//                "\nLast Name: " + lastName +
//                "\nGender: " + selectedGender +
//                "\nUsername: " + username +
//                "\nMobile Number: " + mblNo +
//                "\nEmail: " + email +
//                "\nSecret Key: " + secretKey +
//                "\nPassword: " + createPass +
//                "\nConfirm Password: " + confimePass +
//                "\nUser Role: " + role);


    }
}