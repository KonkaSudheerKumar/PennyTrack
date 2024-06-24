package com.example.pennytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tresult, tprice;
    ImageButton search;
    EditText paritem;
    Button delete, date, fromto;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tresult = findViewById(R.id.textView6);
        tprice = findViewById(R.id.textView8);
        search = findViewById(R.id.imageButton);
        delete = findViewById(R.id.button11);
        date = findViewById(R.id.button12);
        fromto = findViewById(R.id.button13);
        paritem = findViewById(R.id.editTextText5);
        progressBar = findViewById(R.id.progressBar);

        Intent i = getIntent();
        String results = i.getStringExtra("RESULT");
        tresult.setText(results);

        String username = i.getStringExtra("USERNAME");
        DBHelper dbHelper = new DBHelper(getApplicationContext(), "PennyTracker", null, 1);
        signprocess signProcessHelper = new signprocess(getApplicationContext(), "Penny Tracker", null, 1);

        // Call getTotalIncome on the instance of signprocess
        int totalIncome = signProcessHelper.getTotalIncome(username);
        int totalSpent = dbHelper.getTotalSpent();
        int remainingIncome = totalIncome - totalSpent;
        tprice.setText(String.valueOf(remainingIncome));

        updateProgressBar(remainingIncome, totalIncome);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String paritemm = paritem.getText().toString();
                String s = dbHelper.particularItem(paritemm);
                tresult.setText(s);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String par = paritem.getText().toString();
                dbHelper.deleteItem(par);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pardate = paritem.getText().toString();
               dbHelper.parDate(pardate);

            }
        });

        fromto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromdate = paritem.getText().toString();
                String s = dbHelper.fromDate(fromdate);
                tresult.setText(s);
            }
        });
    }

    private void updateProgressBar(int remainingIncome, int totalIncome) {
        int progress = (int) ((remainingIncome / (float) totalIncome) * 100);
        progressBar.setProgress(progress);

        if (progress < 25) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progress < 75) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(255, 165, 0))); // Orange
        } else {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        tprice.setText(String.valueOf(remainingIncome));
    }
}
