package com.example.hyteprojekti.loggable;

import java.io.Serializable;

/**
 * Class for saving Time
 */

public class Time implements Loggable, Serializable {

    public static final long serialVersionUID = -356888365442792143L;

    private int minutes;
    private int hours;

    /**
     * Constructor for saving time
     * @param hours     hours to be saved
     * @param minutes   minutes to be saved
     */
    public Time(int hours, int minutes) {
        this.minutes = minutes;
        this.hours = hours;
    }

    /**
     * Returns the saved minutes
     * @return this.minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Returns the minutes as string in specified format
     * @return minutes  String object with formatted minutes
     */
    public String getMinuteFormat() {
        String minutes;
        if (this.minutes < 10) {
            minutes = "0"+ Integer.toString(this.minutes);
        } else {
            minutes = Integer.toString(this.minutes);
        }

        return minutes;
    }

    /**
     * Returns the saved hours
     * @return this.hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Returns the saved hours as String object
     * @return this.hours   as String object
     */
    public String getHoursString() {
        return Integer.toString(this.hours);
    }

    /**
     * Returns the values of the time object as String
     * @return
     */
    @Override
    public String toString() {
        return this.hours+ ":"+ getMinuteFormat();
    }
}
