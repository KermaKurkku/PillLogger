package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class showHistoryActivity extends AppCompatActivity {

    //TODO  all functionality
    //TODO collect data
    //TODO show collected data as list or graph

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
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
