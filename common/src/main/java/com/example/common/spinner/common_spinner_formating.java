package com.example.common.spinner;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.common.R;

import java.util.List;

public class common_spinner_formating {
    public common_spinner_formating(Context context,Spinner spinner,List<String> items)
    {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, R.layout.common_selected_spinner_color_layout, items);
        spinnerAdapter.setDropDownViewResource(R.layout.common_spinner_dropdown_layout);
        spinner.setAdapter(spinnerAdapter);
    }

}
