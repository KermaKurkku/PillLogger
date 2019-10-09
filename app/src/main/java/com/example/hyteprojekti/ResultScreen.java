package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hyteprojekti.loggable.Timestamp;


/**
 * @author Henri Röning
 *
 */
public class ResultScreen extends AppCompatActivity {

    private TextView scanResult;
    private TextView stamp;

    /**
     * Creates a view for viewing scanned qr result
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        //Create toolbar in place of the ActionBar
        Toolbar toolbar = findViewById(R.id.resultBar);
        setSupportActionBar(toolbar);

        //Adds back button to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        scanResult = findViewById(R.id.scanResult);
        //Checks if the result of the scan is null. Used mainly for testing purposes
        if (getIntent().getStringExtra("Result") != null)
                scanResult.setText(getIntent().getStringExtra("Result")+ "na\nLääkkeet otettu!");

        stamp = findViewById(R.id.stampView);
        stamp.setText(new Timestamp(getIntent().getStringExtra("Result")).toString());

        //Checks if the result of the scan is null. Used mainly for testing purposes
        if (getIntent().getStringExtra("Result") != null)
                TimestampLog.getInstance().addStamp(getIntent().getStringExtra("Result"));
    }


    @Override
    protected void onPause() {
        //Saves file on program pausing
        TimestampLog.getInstance().savetoFile(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        //Saves file on program stopping
        TimestampLog.getInstance().savetoFile(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //Saves file on program being destroyed
        TimestampLog.getInstance().savetoFile(this);
        super.onDestroy();
    }
}
