package com.example.hyteprojekti.loggable;

import java.io.Serializable;
import java.util.Calendar;

/**
 * A class for storing time and date
 * @author Jere Salmensaari
 */

public class Timestamp implements Loggable, Serializable {

    private static final long serialVersionUID = -4191239894900698847L;



    private transient Calendar cal = Calendar.getInstance();
    private Date date;
    private Time time;
    private String name;

    /**
     * Constructor for the class. Creates a Timestamp of the current time.
     * @param name name of the timestamp
     */
    public Timestamp(String name) {
        this.date = new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH),
                                cal.get(Calendar.YEAR));
        this.time = new Time(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
        this.name = name;
    }

    /**
     * Returns the time object of the Timestamp
     * @return this.time    time object
     */
    public String getTime() {
        return this.time.toString();
    }

    /**
     * Returns the date object of the Timestamp
     * @return this.date    date object
     */
    public String getDate() {
        return this.date.toString();
    }

    /**
     * Returns the day of the date object
     * @return this.date.getDay()   day of the date object
     */
    public int getDay() {
        return this.date.getDay();
    }

    /**
     * Returns name, date and time of the Timestamp object in String form
     * @return name, date and time
     */
    @Override
    public String toString() {
        return this.name+ ": "+ this.date.toString()+ ", "+ this.time.toString();
    }





}
