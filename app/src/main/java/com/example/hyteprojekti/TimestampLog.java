package com.example.hyteprojekti;

import com.example.hyteprojekti.loggable.Loggable;
import com.example.hyteprojekti.loggable.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;

public class TimestampLog implements Serializable {
    private ArrayList<Timestamp> log;

    public TimestampLog() {
        this.log = new ArrayList<>();
    }

    public void addStamp() {
        this.log.add(new Timestamp());
    }

    // Return the log List
    public ArrayList<Timestamp> getLog() {
        return this.log;
    }

    public Timestamp getStamp(int i) {
        // Check if index is smaller than 0 or bigger than list
        if (i >= this.log.size() || i < 0) {
            throw new IndexOutOfBoundsException();
        }

        return this.log.get(i);
    }

    public void clearLog() {
        this.log.clear();
    }

    public void deleteEntry(int i) {
        this.log.remove(i);
    }


    // Print out contents of the list for testing purposes
    public void printStamps() {
        for (Timestamp stamp : this.log) {
            System.out.println(stamp.toString());
        }
    }


}
