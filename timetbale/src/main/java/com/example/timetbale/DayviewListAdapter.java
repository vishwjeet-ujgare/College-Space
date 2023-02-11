package com.example.timetbale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DayviewListAdapter extends ArrayAdapter<SessionInformationValues> {

    public DayviewListAdapter(Context context, ArrayList<SessionInformationValues> sessionInformationValuesArrayAdapter) {
        super(context, R.layout.a_list_item_view_for_dayview,sessionInformationValuesArrayAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        SessionInformationValues sessionInforValues=getItem(position);

        if(convertView == null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.a_list_item_view_for_dayview,parent,false);

        }

        ImageView img=convertView.findViewById(R.id.circleImgDVLV);
        TextView sessionTimeTV=convertView.findViewById(R.id.sessionTimeTVDVLV);
        TextView subjectNameTV=convertView.findViewById(R.id.subjectNameTVDVLV);
        TextView roomNoTV=convertView.findViewById(R.id.roomNoTVDVLV);


        img.setImageResource(sessionInforValues.imageId);
        sessionTimeTV.setText(sessionInforValues.sessionStartTime +" : "+sessionInforValues.sessionEndTime);
        subjectNameTV.setText(sessionInforValues.subjectName);
        roomNoTV.setText(sessionInforValues.roomNo);




        return convertView;
    }
}
