package com.example.recyclerpickerdialoglibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.recyclerpickerdialoglibrary.MyOwnCustomDialog;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    private MaterialButton main_BTN_option1;
    String[] data = {"Option -1-",
            "Option -2-",
            "Option -3-",
            "Option -4-",
            "Option -5-",
            "Option -6-",
            "Option -7-",
            "Option -8-",
            "Option -9-",
            "Option -10-",
            "Option -11-",
            "Option -12-",
            "Option -13-",
            "Option -14-",
            "Option -15-",
            "Option -16-",
            "Option -17-",
            "Option -18-",
            "Option -19-",
            "Option -20-",
    };

    MyOwnCustomDialog myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // Hide the action bar on mainActivity

        init();

        // OnClickListener
        main_BTN_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFragment = new MyOwnCustomDialog("Question number 1", 0, data, Color.argb(255,124,181,189));
                myFragment.show(getSupportFragmentManager(), "Avraham Rada");
            }
        });
    }

    /**
     * init() initialize all variables
     */
    private void init() {
        main_BTN_option1 = (MaterialButton) findViewById(R.id.main_BTN_option1);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        ArrayList<Integer> answers = myFragment.getMyAdapter().getYourAnswers();
        Collections.sort(answers);

        for (Integer answer : answers) {
            Log.d("pttt", "answer #" + (answer + 1));
            Log.d("pttt", "" + myFragment.getMyAdapter().getListOfData().get(answer));
        }
    }
}