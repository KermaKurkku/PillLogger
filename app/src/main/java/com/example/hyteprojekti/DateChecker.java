package com.example.hyteprojekti;

import android.util.Log;

import java.util.Calendar;

/**
 * A class for checking if date is in the correct format according to the current date
 * @author Jere Salmensaari
 */

public class DateChecker {
    private String[] dates = {"Sunnuntai", "Maanantai", "Tiistai", "Keskiviikko",
                "Torstai", "Perjantai", "Lauantai"};

    public DateChecker() {
    }

    /**
     * Checks if the date is in the correct format
     * @param checkabble    date to be checked
     * @return true         if date is of the correct format, otherwise false
     */
    public boolean checkdate(String checkabble) {
        String todayDate = dates[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) -1];
        Log.d("Todays date", todayDate);
        if (todayDate.equals(checkabble))
            return true;

        return false;
    }
}
