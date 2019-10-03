package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class showHistoryActivity extends AppCompatActivity {

    //TODO  all functionality
    //TODO collect data
    //TODO show collected data as list or graph

    private Button testButton1;
    private Button testButton2;

    private TimestampLog log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        log = new TimestampLog();

        testButton1 = findViewById(R.id.addButton);
        testButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log.addStamp();
                Log.d("Pressed", "Added stamp");
            }
        });
        testButton2 = findViewById(R.id.printButton);
        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Pressed", "Printed stamps");
                log.printStamps();
            }
        });


    }

    //Go back to main screen when pressing button
    public void showMain(View view) {
        Intent changeMain = new Intent(this, MainActivity.class);
        //Show main
        try {
            startActivity(changeMain);
        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
