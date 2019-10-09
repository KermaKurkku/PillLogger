package com.example.hyteprojekti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.hyteprojekti.loggable.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Here we create a ListView of the log
 * @author Vilho Voutilainen
 *
 */

public class showHistoryActivity extends AppCompatActivity {



    private ListView lv;


    /**
     * Creates a view for viewing the log through ListView
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        //TimestampLog.getInstance().readFromfile(this);
        //TimestampLog.getInstance().initializeLog();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        lv= findViewById(R.id.histolog);

        updateUI();


    }

    /**
     * Updates ListVIew element
     */
    public void updateUI() {
        if (TimestampLog.getInstance().isEmpty()) {
            ArrayList<String> errorList = new ArrayList<>();
            errorList.add("Tyhjää täynnä");
            lv.setAdapter(new ArrayAdapter<String>(
                    this,
                    R.layout.listitemview,
                    errorList

            ));
        } else {
            lv.setAdapter(new ArrayAdapter<Timestamp>(
                    this,
                    R.layout.listitemview,
                    TimestampLog.getInstance().getReverseLog()

            ));
        }
    }

    /**
     * Creates an options menu
     * @param menu  menu to be created
     * @return      always returns true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.historymenu, menu);
        return true;
    }

    /**
     * Creates functionality for a menu item press
     * @param item  menu item pressed
     * @return      true or false
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                TimestampLog.getInstance().addStamp("Torstai");
                updateUI();
                return true;

            case R.id.resetItem:
                TimestampLog.getInstance().clearLog(this);
                updateUI();
                return true;

            default:    return super.onOptionsItemSelected(item);
        }

    }



    @Override
    protected void onPause() {
        Log.d("onPause", "Paused");
        TimestampLog.getInstance().savetoFile(this);
        super.onPause();

    }

    @Override
    protected void onStop() {
        Log.d("onStop", "Stopped");
        TimestampLog.getInstance().savetoFile(this);
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("onDestroy", "Destroyd");
        TimestampLog.getInstance().savetoFile(this);
        super.onDestroy();
    }


}
