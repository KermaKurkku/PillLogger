package com.example.hyteprojekti.loggable;

import java.io.Serializable;
import java.util.Calendar;

public class Date implements Loggable, Serializable {
    private int day;
    private int month;
    private int year;
    private java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MM");

    public Date(int day, int month, int year) {

        this.day = day;
        this.month = month;
        this.year = year;

    }

    public int getDay() {
        return this.day;
    }

    public String getDayString() {
        return Integer.toString(this.day);
    }

    public int getMonth() {
        return this.month + 1;
    }

    public String getMonthString() {
        String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct", "Nov", "Dec"};
        return monthName[this.month];
    }

    public int getYear() {
        return this.year;
    }

    public String getYearString() {
        return Integer.toString(this.year);
    }

    @Override
    public String toString() {
        return this.day+ "."+getMonthString()+ "."+ this.year;
    }

}
