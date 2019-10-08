package com.example.hyteprojekti.loggable;

import java.io.Serializable;
import java.util.Calendar;

public class Timestamp implements Loggable, Serializable {

    private static final long serialVersionUID = -4191239894900698847L;


    private transient Calendar cal = Calendar.getInstance();
    private Date date;
    private Time time;
    private String name;

    public Timestamp(String name) {
        this.date = new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH),
                                cal.get(Calendar.YEAR));
        this.time = new Time(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
        this.name = name;
    }

    public String getTime() {
        return this.time.toString();
    }

    public String getDate() {
        return this.date.toString();
    }

    public int getDay() {
        return this.date.getDay();
    }

    @Override
    public String toString() {
        return this.name+ ": "+ this.date.toString()+ ", "+ this.time.toString();
    }





}
