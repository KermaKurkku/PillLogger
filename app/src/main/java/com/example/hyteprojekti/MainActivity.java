package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    //TODO get camera working
    //TODO get notifications working

    //TODO styling
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Changes the view of the app to showHistoryActivity when button is pressed
    public void showHistory(View view) {
        //Creates the intent to change the view
        Intent changeView = new Intent(this, showHistoryActivity.class);
        //Changes the view
        startActivity(changeView);
    }

}
