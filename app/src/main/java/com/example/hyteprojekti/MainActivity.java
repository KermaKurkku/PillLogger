package com.example.hyteprojekti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Main activity of the app. Handles moving to the scanner and history activities.
 * @author Jere Salmensaari
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {

    private Button historyView;
    private Button scannerView;

    /**
     * Creates the main activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimestampLog.getInstance().initializeLog();
        TimestampLog.getInstance().readFromfile(this);

        //Creates a toolbar instead of the ActionBar
        Toolbar toolbar = findViewById(R.id.mainBar);
        setSupportActionBar(toolbar);

        //Creates the buttons used in the activity
        createButtons();

        //onClick for changing to the historyActivity
        historyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHistory(view);
            }
        });

        //onClick for changing to the scannerActivity
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TimestampLog.getInstance().checkDate()) {
                    Toast error = Toast.makeText(MainActivity.this, "Olet ottanut jo lääkkeet", Toast.LENGTH_LONG);
                    error.setGravity(Gravity.CENTER, 0, 0);
                    error.show();
                } else {
                    showScanner(view);
                }
            }
        });
    }

    /**
     * Creates an options menu
     * @param menu  default menu object of the activity to be overriden
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    /**
     * Creates onClick functionality for the menuitems
     * @param item  item clicked
     * @return true if click is successful
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.resultItem:
                Intent i = new Intent(this, ResultScreen.class);
                startActivity(i);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Switches the activity to the historyActivity
     * @param view  current view
     */
    public void showHistory(View view) {
        //Creates the intent to change the activity
        Intent changeView = new Intent(this, showHistoryActivity.class);
        //Changes the view
        try {
            startActivity(changeView);
        } catch(Exception e) {
            System.out.println("Error: "+ e.getMessage());
            Log.d("Error", e.getMessage());
        }
    }


    /**
     * Switches the activity to scannerView
     * @param view  current View
     */
    public void showScanner(View view) {
        //Creates the intent for switching the activity
        Intent changeView = new Intent(this, qrScannerActivity.class);
        Log.d("Pressed", "Tried to activate scanner");
        //Changes the view
        try {
            startActivity(changeView);
         } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    /**
     * Creates the buttons for the activity
     */
    private void createButtons() {
        historyView = (Button) findViewById(R.id.historyButton);
        scannerView = (Button) findViewById(R.id.scannerButton);
    }






}
