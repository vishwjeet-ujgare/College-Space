package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class FragmentLogin extends Fragment {

    TextView forgotPasswordTV, newUserQuestionTV, homeTV, LoginHereTV, newuserRegNowTV;

    EditText usernameET, passwordET;
    Button loginBtn;
    View view, progressDialogView;
    ProgressBar progressBar;
    AlertDialog alertProgressDialog;
    Spinner user_role_spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_login, container, false);
        progressDialogView = getLayoutInflater().inflate(com.example.common.R.layout.progress_dialog, null);
        progressBar = progressDialogView.findViewById(com.example.common.R.id.progress_bar);
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(false);
        builder.setView(progressDialogView);
        alertProgressDialog = builder.create();




         user_role_spinner = view.findViewById(R.id.role_spinner);
        //create progress dialog
//        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.spinner_role_items, R.layout.registration_selected_color_spinner_layout);
//        spinnerAdapter.setDropDownViewResource(R.layout.registration_spinner_dropdown_layout);
//
//        login_as_spinner.setAdapter(spinnerAdapter);

        new spinner_formating(user_role_spinner, view.getContext());
        user_role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//---------------------------------------
        forgotPasswordTV = view.findViewById(R.id.forgotPasswordTV);
        newUserQuestionTV = view.findViewById(R.id.newUserQuestionTV);
        homeTV = view.findViewById(R.id.homeTV);
        homeTV = view.findViewById(R.id.homeTV);

        LoginHereTV = view.findViewById(R.id.LoginHereTV);
        LoginHereTV.setVisibility(View.GONE);

        newuserRegNowTV = view.findViewById(R.id.newUserRegNowTV);
        newuserRegNowTV.setVisibility(View.GONE);

        usernameET = view.findViewById(R.id.usernameLET);
        passwordET = view.findViewById(R.id.passwordLET);

        loginBtn = view.findViewById(R.id.loginBtn);

//-----------------------------------------------------------
        forgotPasswordTV.setTranslationX(300);
        newUserQuestionTV.setTranslationX(300);
        LoginHereTV.setTranslationX(300);
        homeTV.setTranslationX(300);

        user_role_spinner.setTranslationX(300);

        usernameET.setTranslationX(300);
        passwordET.setTranslationX(300);

        loginBtn.setTranslationX(300);
//------------------------------------------------------
        forgotPasswordTV.setAlpha(0);
        newUserQuestionTV.setAlpha(0);
        LoginHereTV.setAlpha(0);
        homeTV.setAlpha(0);

        user_role_spinner.setAlpha(0);

        usernameET.setAlpha(0);
        passwordET.setAlpha(0);

        loginBtn.setAlpha(0);
//------------------------------------------

        user_role_spinner.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        usernameET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        passwordET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();

        forgotPasswordTV.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        loginBtn.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        newUserQuestionTV.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        LoginHereTV.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        homeTV.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();

//--------------------------------------------
        forgotPasswordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "You have clicked on forgot password", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

        return view;
    }

    private void login(View view) {

        alertProgressDialog.show();

        //getting values from activity
        String usernameOrEmail= usernameET.getText().toString();
        String pass=passwordET.getText().toString();
        String user_role = user_role_spinner.getSelectedItem().toString();

        //printing
        System.out.println("user name or password : "+usernameOrEmail+"\n passworrd :"+pass +"\n  user Role : "+user_role);

        //creating object of request queue
        RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());

        //stringRequest object where we pass all details like method , url, response which we get, error we get ans implement code to pass
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                "http://192.168.0.107:8080/College_Space_API/Registration",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertProgressDialog.dismiss();

                        if(response.equals("true"))
                        {
//                            onLoginResponse();
                            System.out.println("Hello");
                            System.out.println("responsse" + response);
                            Toast.makeText(view.getContext(),"Response"+response,Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            System.out.println("invalid Credentials");
                            Toast.makeText(view.getContext(),"please Enter Valid credentials",Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alertProgressDialog.dismiss();
                System.err.println(error);
                Toast.makeText(view.getContext(),"Error"+error,Toast.LENGTH_LONG).show();
            }
        }
        ){
            //call getParams methods and pass key values in map

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String , String > hm =new HashMap<>();
                hm.put("key_username_or_email",usernameOrEmail);
                hm.put("key_user_role",user_role);
                hm.put("key_pass",pass);
                hm.put("key_action","login");

                return hm;
            }
        };
        requestQueue.add(stringRequest);
    }


    private  void onLoginResponse()
    {
//        Intent intent= new Intent(this.getContext(), HomeWithLogin.class);
//        startActivity(intent);
//      getActivity().finish();


    }
}