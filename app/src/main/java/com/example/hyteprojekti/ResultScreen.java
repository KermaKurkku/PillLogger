package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hyteprojekti.loggable.Timestamp;


/**
 * @author Henri
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

        scanResult = findViewById(R.id.scanResult);
        scanResult.setText(getIntent().getStringExtra("Result")+ "na\nLääkkeet otettu!");
        stamp = findViewById(R.id.stampView);
        stamp.setText(new Timestamp(getIntent().getStringExtra("Result")).toString());
        TimestampLog.getInstance().addStamp(getIntent().getStringExtra("Result"));
    }


    @Override
    protected void onPause() {
        TimestampLog.getInstance().savetoFile(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        TimestampLog.getInstance().savetoFile(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        TimestampLog.getInstance().savetoFile(this);
        super.onDestroy();
    }
}
