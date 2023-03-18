package com.example.college_space.administration;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.college_space.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Semester_Details extends Fragment {

    View view;
    Spinner selectDepartmentSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__semester__details, container, false);
        selectDepartmentSpinner = view.findViewById(R.id.selectDepartmentSpinner);
        String url = "http://example.com/myservlet";
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, "http://192.168.1.76:8080/College_Space_API/TransferRequest", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Department_Values> department_valuesList = new ArrayList<>();

                // Parse the JSON response and add each department to the list
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        System.out.println("id : "+id+"name : "+name);
                        Department_Values department = new Department_Values(id, name);
                        department_valuesList.add(department);
                    }

                    // Create an ArrayAdapter to display the department names in the Spinner
                    ArrayAdapter<Department_Values> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, department_valuesList);
                    selectDepartmentSpinner.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle errors here
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
        requestQueue.add(jsonArrayRequest);

        return view;

    }
}