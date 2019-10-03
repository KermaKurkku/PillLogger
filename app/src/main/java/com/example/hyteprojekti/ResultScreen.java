package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hyteprojekti.loggable.Loggable;
import com.example.hyteprojekti.loggable.Timestamp;
import java.util.Calendar;

public class ResultScreen extends AppCompatActivity {

    private TextView scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        scanResult = findViewById(R.id.scanResult);
        scanResult.setText(getIntent().getStringExtra("Result"));
        TextView tv = findViewById(R.id.textView2);
        tv.setText(new Timestamp().toString());
    }
}
