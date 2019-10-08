package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import me.dm7.barcodescanner.zxing.ZXingScannerView;



public class MainActivity extends AppCompatActivity {

    private Button historyView;
    private Button scannerView;



    //TODO get camera working
    //TODO get notifications working

    //TODO styling
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimestampLog.getInstance().initializeLog();
        TimestampLog.getInstance().readFromfile(this);

        createButtons();
        historyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHistory(view);
            }
        });
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




    @Override
    protected void onResume() {
        super.onResume();
        //TimestampLog.getInstance().readFromfile(this);
    }

    @Override
    protected void onPause() {
        //TimestampLog.getInstance().savetoFile(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Changes the view of the app to showHistoryActivity when button is pressed
    public void showHistory(View view) {
        //Creates the intent to change the view
        Intent changeView = new Intent(this, showHistoryActivity.class);
        //Changes the view
        try {
            startActivity(changeView);
        } catch(Exception e) {
            System.out.println("Error: "+ e.getMessage());
            Log.d("Error", e.getMessage());
        }
    }

    //Changes view to qrScannerActivity
    public void showScanner(View view) {
        Intent changeView = new Intent(this, qrScannerActivity.class);
        Log.d("Pressed", "Tried to activate scanner");
        try {
            startActivity(changeView);
         } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    private void createButtons() {
        historyView = (Button) findViewById(R.id.historyButton);
        scannerView = (Button) findViewById(R.id.scannerButton);
    }







}
