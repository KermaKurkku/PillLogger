package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mscannerView;
    private TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Init
        mscannerView = findViewById(R.id.zxscan);
        txtResult = findViewById(R.id.txt_result);

        //TODO ask for permissions
        


        //Request permission
        /*
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scanQR();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(qrScannerActivity.this, "You must accept this permission", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
         */



    }

    private void scanQR() {
        try {
            mscannerView.setResultHandler(qrScannerActivity.this);
            mscannerView.startCamera();
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
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
        txtResult.setText(rawResult.getText());
    }
}
