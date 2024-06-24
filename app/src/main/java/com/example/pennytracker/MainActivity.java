package com.example.pennytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText itemname,itemprice;
    Button add,view;

    String date,time,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemname=findViewById(R.id.editTextText2);
        itemprice=findViewById(R.id.editTextText4);
        add=findViewById(R.id.button);
        view=findViewById(R.id.button2);

        Intent i=getIntent();
        String username=i.getStringExtra("USERNAME");

        DBHelper db=new DBHelper(getApplicationContext(),"PennyTracker",null,1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int pYear = c.get(Calendar.YEAR);
                int pMonth = c.get(Calendar.MONTH);
                int pDate = c.get(Calendar.DATE);
                int pHour=c.get(Calendar.HOUR);
                int pMin=c.get(Calendar.MINUTE);

                date=pYear+"/"+pMonth+"/"+pDate;
                time=pHour+":"+pMin;


                String eitemname=itemname.getText().toString();
                String eitemprice=itemprice.getText().toString();

                db.insertRecord(eitemname,eitemprice,date,time);

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=db.display();
                Intent in=new Intent(getApplicationContext(), MainActivity2.class);
                in.putExtra("USERNAME",username);
                in.putExtra("RESULT",result);

                startActivity(in);
            }
        });


            }
        }