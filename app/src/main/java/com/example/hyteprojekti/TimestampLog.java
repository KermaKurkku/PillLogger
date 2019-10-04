package com.example.hyteprojekti;

import android.content.Context;
import android.util.Log;

import com.example.hyteprojekti.loggable.Timestamp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class TimestampLog {
    private static final TimestampLog ourInstance = new TimestampLog();

    private ArrayList<Timestamp> log;


    static TimestampLog getInstance() {
        return ourInstance;
    }

    private TimestampLog() {
    }

    //Create a new log if one doess not exist
    public void initializeLog() {
        if (this.log == null)
            this.log = new ArrayList<>();
    }

    //Set the value of the log separately
    public void setLog(ArrayList<Timestamp> settable) {
        this.log = settable;
    }

    public void removeLog() {
        this.log = null;
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

    public void clearLog(Context context) {
        this.log.clear();
        savetoFile(context);
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

    private Boolean fileExists(Context context) {
        File file = context.getFileStreamPath("log.ser");
        if (file == null || !file.exists())
            return false;
        return true;
    }

    //Handle saving to file
    public void savetoFile(Context context) {
        //If log is null, initialize it so that null is not being saved
        if (this.log == null)
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
        ArrayList<Timestamp> settable;


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
