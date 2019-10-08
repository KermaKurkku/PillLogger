package com.example.hyteprojekti;

import android.util.Log;

import java.util.Calendar;

public class DateChecker {
    String[] dates = {"Sunnuntai", "Maanantai", "Tiistai", "Keskiviikko",
                "Torstai", "Perjantai", "Lauantai"};

    public DateChecker() {
    }

    public Boolean checkdate(String checkabble) {
        String todayDate = dates[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) -1];
        Log.d("Todays date", todayDate);
        if (todayDate.equals(checkabble))
            return true;

        return false;
    }
}
