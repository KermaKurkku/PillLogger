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

    private Button testButton1, testButton2, clearButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        TimestampLog.getInstance().readFromfile(this);
        TimestampLog.getInstance().initializeLog();

        testButton1 = findViewById(R.id.addButton);
        testButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimestampLog.getInstance().addStamp();
                Log.d("Pressed", "Added stamp");
            }
        });
        testButton2 = findViewById(R.id.printButton);
        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Pressed", "Printed stamps");
                TimestampLog.getInstance().printStamps();
            }
        });

        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimestampLog.getInstance().clearLog(showHistoryActivity.this);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        TimestampLog.getInstance().readFromfile(this);
    }

    @Override
    protected void onPause() {
        //TimestampLog.getInstance().savetoFile(this);
        super.onPause();

    }

    @Override
    protected void onStop() {
        TimestampLog.getInstance().savetoFile(this);
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        //TimestampLog.getInstance().savetoFile(this);
        super.onDestroy();
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
