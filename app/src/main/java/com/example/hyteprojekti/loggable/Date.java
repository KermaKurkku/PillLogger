package com.example.hyteprojekti.loggable;

import java.io.Serializable;

/**
 * Class for saving the date
 */

public class Date implements Loggable, Serializable {
    private int day;
    private int month;
    private int year;
    private transient java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MM");

    /**
     * Constructor for creating date
     * @param day   day to be saved
     * @param month month to be saved
     * @param year  year to be saved
     */
    public Date(int day, int month, int year) {

        this.day = day;
        this.month = month;
        this.year = year;

    }

    /**
     * Returns the saved day
     * @return this.day saved day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Returns the saved day as String object
     * @return this.day as string object
     */
    public String getDayString() {
        return Integer.toString(this.day);
    }

    /**
     * Returns the saved month
     * @return this.month   saved month
     */
    public int getMonth() {
        return this.month + 1;
    }

    /**
     * Return the saved month as String object
     * @return this.month as string object
     */
    public String getMonthString() {
        String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct", "Nov", "Dec"};
        return monthName[this.month];
    }

    /**
     * Returns the saved year
     * @return this.year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Returns the saved year as String object
     * @return this.day as String object
     */
    public String getYearString() {
        return Integer.toString(this.year);
    }

    /**
     * Returns the values of the Date object as String
     * @return value of Date object
     */
    @Override
    public String toString() {
        return this.day+ "."+getMonthString()+ "."+ this.year;
    }

}
