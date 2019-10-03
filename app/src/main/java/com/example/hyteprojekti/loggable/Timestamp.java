package com.example.hyteprojekti.loggable;

import java.util.Calendar;

public class Timestamp implements Loggable {


    private Calendar cal = Calendar.getInstance();
    private Loggable date;
    private Loggable time;
    private String name;

    public Timestamp() {
        this.date = new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH),
                                cal.get(Calendar.YEAR));
        this.time = new Time(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
    }

    public String getTime() {
        return this.time.toString();
    }

    public String getDate() {
        return this.date.toString();
    }

    @Override
    public String toString() {
        return this.date.toString()+ ", "+ this.time.toString();
    }





}
