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

/**
 * QrScannerActivity uses ZXingScanner libnrary (https://github.com/zxing/zxing)
 * for creating a qr scanner.
 * @author Jere Salmensaari
 */

public class qrScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    private ZXingScannerView mscannerView;


    //TODO investigate startPreview() failing sometimes

    /**
     * Creates the qr scanner on first start
     * @param savedInstanceState
     */
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

    /**
     * Activates the scanner independent of onCreate()
     */
    public void scanQR() {
        mscannerView.setResultHandler(qrScannerActivity.this);
        try {
            mscannerView.startCamera();
        } catch (Exception e) {
            Log.d("Error starting camera: ", e.getMessage());
        }
    }

    /**
     * Checks if the program has needed permissions to use the scanner.
     * If not then asks for permission. Perissions needed: CAMERA
     */
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    /**
     * Checks if qr scanned is the right one for this date
     * @param checkabble    QR result to be checked
     * @return true         if checkabble is wrong for this date, false if date is good
     */
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


    /**
     * Starts the camera on resume to the program
     */
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

    /**
     * Stops the camera on pausing the program
     */
    @Override
    protected void onPause() {
        if (mscannerView!=null)
            mscannerView.stopCamera();
        super.onPause();
    }

    /**
     * Stops the camera on stopping the program
     */
    @Override
    protected void onStop() {
        if (mscannerView!=null)
            mscannerView.stopCamera();
        super.onStop();
    }

    /**
     * Stops the camera on program being destroyed
     */
    @Override
    protected void onDestroy() {
        if (mscannerView!=null)
            mscannerView.stopCamera();
        super.onDestroy();
    }


    /**
     * Handles the result of the QR scan. Checks if the date is wrong before starting ResultActivity.
     * If date is wrong, starts the scanner again. If not then starts the ResultActivity and sends the
     * result to it.
     * @param rawResult the result of the QR scan
     */
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