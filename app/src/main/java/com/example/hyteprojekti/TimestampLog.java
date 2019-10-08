package com.example.hyteprojekti;

import android.content.Context;
import android.util.Log;

import com.example.hyteprojekti.loggable.Timestamp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;;
import java.util.ArrayList;
import java.util.Calendar;



class TimestampLog {
    /**
     * @author Jere
     */
    private static final TimestampLog ourInstance = new TimestampLog();

    private ArrayList<Timestamp> log;


    static TimestampLog getInstance() {
        return ourInstance;
    }

    private TimestampLog() {
    }

    /**
     * Creates a new log if value is null
     */
    //Create a new log if one doess not exist
    public void initializeLog() {
        if (this.log == null)
            this.log = new ArrayList<>();
    }

    /**
     * Set the value of the log to be another List
     * @param settable value of the List to be set
     */
    //Set the value of the log separately
    public void setLog(ArrayList<Timestamp> settable) {
        this.log = settable;
    }

    /**
     * Changes the value of the log to null
     */
    public void removeLog() {
        this.log = null;
    }

    /**
     * Adds a stamp to the log
     * @param name name of the qr scanned
     */
    public void addStamp(String name) {
        Log.d("stamped", name);
        this.log.add(new Timestamp(name));
    }

    /**
     * Check if this date has already been logged
     * @return true if already logged
     */
    //Check if this date has already been logged
    public Boolean checkDate() {
        if (isEmpty())
            return false;
        int lastLog = this.log.get(this.log.size()-1).getDay();
        Log.d("lastLog", Integer.toString(lastLog));
        int dateNow = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Log.d("dateNow", Integer.toString(dateNow));
        if (lastLog == dateNow)
            return true;
        return false;
    }

    /**
     * Returns the log
     * @return log
     */
    // Return the log List
    public ArrayList<Timestamp> getLog() {
        return this.log;
    }

    /**
     * Checks if the list is empty or null
     * @return true if empty or null, otherwise false
     */
    public Boolean isEmpty() {
        if (this.log.isEmpty() || this.log == null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the log in reverse order
     * @return reversed ,log in reverse order
     */
    public ArrayList<Timestamp> getReverseLog() {
        ArrayList<Timestamp> reversed = new ArrayList<>();
        if (this.log == null)
            initializeLog();

        for (int i = this.log.size() - 1; i >= 0; i--) {
            reversed.add(this.log.get(i));
        }
        return reversed;
    }

    /**
     * Gets a specific timestamp
     * @param i index of the stamp
     * @return Timestamp in the specified index
     */
    public Timestamp getStamp(int i) {
        // Check if index is smaller than 0 or bigger than list
        if (i >= this.log.size() || i < 0) {
            throw new IndexOutOfBoundsException();
        }

        return this.log.get(i);
    }

    /**
     * Clears the log
     * @param context context for saving the cleared log to file
     */
    public void clearLog(Context context) {
        this.log.clear();
        savetoFile(context);
    }

    /**
     * Deletes a specified entry
     * @param i index of the entry to be deleted
     */
    public void deleteEntry(int i) {
        this.log.remove(i);
    }




    //Handle saving to file

    /**
     * Saves the log to file through Serializable interface
     * @param context context of the activity method is being used in
     */
    public void savetoFile(Context context) {
        //If log is null, initialize it so that null is not being saved
        initializeLog();

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(context.getFilesDir()+"/log.ser"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.log);
            objectOutputStream.close();
            fileOutputStream.close();
            Log.d("Saved", "Saved to file");

        } catch (IOException e) {
            e.printStackTrace();

        }
    }



    public void readFromfile(Context context) {
        ArrayList<Timestamp> settable = null;


        try {
            FileInputStream fileInputStream = context.openFileInput("log.ser");
            ObjectInputStream objectInputstream = new ObjectInputStream(fileInputStream);
            settable = (ArrayList<Timestamp>) objectInputstream.readObject();
            Log.d("Opened", "Opened file");
            objectInputstream.close();
            fileInputStream.close();
            setLog(settable);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }


}
