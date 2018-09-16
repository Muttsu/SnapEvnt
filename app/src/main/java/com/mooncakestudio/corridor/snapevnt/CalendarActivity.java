package com.mooncakestudio.corridor.snapevnt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarActivity extends Activity {

    CalendarView calendar;
    TextView calendarGreetingTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CalendarView) findViewById(R.id.calendar);
        calendarGreetingTxt = (TextView) findViewById(R.id.calendarGreetingTxt);

        calendarGreetingTxt.setText(dayTime());

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Intent I = new Intent(CalendarActivity.this, MainGroups.class);
                startActivity(I);
            }
        });
    }

    public String dayTime(){

        String dayPeriod = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String formattedDate = df.format(c.getTime());

        int timeOfTheDay = Integer.parseInt(formattedDate);

        if (timeOfTheDay >= 6 && timeOfTheDay < 12)
            dayPeriod = "Morning! How are you?";
        else if (timeOfTheDay >= 12 && timeOfTheDay < 17)
            dayPeriod = "Good afternoon! Any plans?";
        else if (timeOfTheDay >= 17 && timeOfTheDay < 20)
            dayPeriod = "Good evening!";
        else if (timeOfTheDay >= 20 || timeOfTheDay < 6)
            dayPeriod = "Good Night! Sleep well.";
        else
            dayPeriod = "Hello, what are your plans?";

        return dayPeriod;
    }
}
