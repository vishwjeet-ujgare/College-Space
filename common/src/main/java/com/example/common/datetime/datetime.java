package com.example.common.datetime;

// Java program for the above approach
import java.util.Calendar;
import java.util.Date;
// Java program for the above approach

public class datetime {
public static int DAY;
public  static String MONTHS;
public  static int YEAR;
public  static String WEEK_DAYS;

public datetime()
{
    String arr[]={ "Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday",};
    String[] month = new String[] {"January", "February", "March", "April", "May", "June", "July", "August","September", "October", "November", "December" };
    Calendar cal=Calendar.getInstance();
    cal.setTime(new Date());

    DAY=cal.get(Calendar.DAY_OF_MONTH);
    MONTHS=month[cal.get(Calendar.MONTH)];
    YEAR=cal.get(Calendar.YEAR);
    int dayodweek=cal.get(Calendar.DAY_OF_WEEK)-1;
    WEEK_DAYS=arr[dayodweek];
}

}
