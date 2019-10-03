package com.example.hyteprojekti.loggable;

import java.io.Serializable;

public class Time implements Loggable, Serializable {
    private int minutes;
    private int hours;

    public Time(int hours, int minutes) {
        this.minutes = minutes;
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getMinutesString() {
        return Integer.toString(this.minutes);
    }

    public int getHours() {
        return hours;
    }

    public String getHoursString() {
        return Integer.toString(this.hours);
    }

    @Override
    public String toString() {
        return this.hours+ ":"+ this.minutes;
    }
}
