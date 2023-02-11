package com.example.timetbale;

import android.graphics.Color;

public class SessionInformationValues {
    String sessionStartTime,sessionEndTime,subjectName,roomNo;
    int color;
    int imageId;

    public SessionInformationValues(String sessionStartTime, String sessionEndTime, String subjectName, String roomNo, int color,int imageID) {
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.subjectName = subjectName;
        this.roomNo = roomNo;
        this.color=color;
        this.imageId = imageId;
    }
}
