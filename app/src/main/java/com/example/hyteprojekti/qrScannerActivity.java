package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;


import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    private ZXingScannerView mscannerView;


    //TODO investigate startPreview() failing sometimes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Request permissions
        checkPermissions();
        //Init
        mscannerView = new ZXingScannerView(this);
        setContentView(mscannerView);



        //scanQR();




    }


    //Activate scanner
    public void scanQR() {
        mscannerView.setResultHandler(qrScannerActivity.this);
        try {
            mscannerView.startCamera();
        } catch (Exception e) {
            Log.d("Error starting camera: ", e.getMessage());
        }
    }

    //Check if the program has needed permissions for the camera, if not then ask permissions
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    public Boolean wrongDate(String checkabble) {
        DateChecker dateChecker = new DateChecker();
        if (!(dateChecker.checkdate(checkabble))) {
            Toast error = Toast.makeText(this, "Väärä päivä!", Toast.LENGTH_SHORT);
            error.setGravity(Gravity.CENTER, 0, 0);
            error.show();
            return true;
        }
        return false;
    }



    @Override
    protected void onResume() {
        super.onResume();
        mscannerView.setResultHandler(this);
        try {
            mscannerView.startCamera();
        } catch (Exception e) {
            Log.d("Error starting camera: ", e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        if (mscannerView!=null)
            mscannerView.stopCamera();
        super.onPause();
    }


    @Override
    protected void onStop() {
        if (mscannerView!=null)
            mscannerView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mscannerView!=null)
            mscannerView.stopCamera();
        super.onDestroy();
    }



    @Override
    public void handleResult(Result rawResult) {
        //Here we can receive rawResult

        if (wrongDate(rawResult.getText())) {
            scanQR();
            return;
        }



        String result = rawResult.getText();
        Intent i = new Intent(qrScannerActivity.this, ResultScreen.class);
        i.putExtra("Result", result);
        startActivity(i);
    }
}