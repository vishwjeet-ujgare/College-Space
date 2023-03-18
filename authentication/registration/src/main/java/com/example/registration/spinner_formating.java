package com.example.registration;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class spinner_formating {
    spinner_formating(Spinner spinner, Context context)
    {
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(context, R.array.spinner_role_items, R.layout.registration_selected_color_spinner_layout);
        spinnerAdapter.setDropDownViewResource(R.layout.registration_spinner_dropdown_layout);
        spinner.setAdapter(spinnerAdapter);
    }
}
