package com.example.hyteprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.hyteprojekti.loggable.Timestamp;

import java.util.ArrayList;

/**
 * Tässä luodaan listanäkymä logitiedoista
 * @author Vilho
 *
 */

public class showHistoryActivity extends AppCompatActivity {



    private Button testButton1, testButton2, clearButton;
    private ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        //TimestampLog.getInstance().readFromfile(this);
        //TimestampLog.getInstance().initializeLog();

        lv= findViewById(R.id.histolog);

        if (TimestampLog.getInstance().isEmpty()) {
            ArrayList<String> errorList = new ArrayList<>();
            errorList.add("Tyhjää täynnä");
            lv.setAdapter(new ArrayAdapter<String>(
                    this,
                    R.layout.listitemview,
                    errorList

            ));
        } else {
            lv.setAdapter(new ArrayAdapter<Timestamp>(
                    this,
                    R.layout.listitemview,
                    TimestampLog.getInstance().getReverseLog()

            ));
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "resumed");
        //TimestampLog.getInstance().readFromfile(this);
    }

    @Override
    protected void onPause() {
        Log.d("onPause", "Paused");
        //TimestampLog.getInstance().savetoFile(this);
        super.onPause();

    }

    @Override
    protected void onStop() {
        Log.d("onStop", "Stopped");
        //TimestampLog.getInstance().savetoFile(this);
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("onDestroy", "Destroyd");
        //TimestampLog.getInstance().savetoFile(this);
        super.onDestroy();
    }


}
