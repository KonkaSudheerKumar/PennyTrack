package com.example.pennytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {

    EditText name,email,username,password,income;
    Button signin,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.editTextText6);
        email=findViewById(R.id.editTextTextEmailAddress);
        username=findViewById(R.id.editTextText7);
        password=findViewById(R.id.editTextTextPassword);
        income=findViewById(R.id.editTextText);
        signup=findViewById(R.id.button3);
        signin=findViewById(R.id.button4);

        signprocess sp=new signprocess(getApplicationContext(), "Penny Tracker", null, 1);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namee=name.getText().toString();
                String emaill=email.getText().toString();
                String usernamee=username.getText().toString();
                String passwordd=password.getText().toString();
                String incomee=income.getText().toString();

                sp.insertRecord(namee,emaill,usernamee,passwordd,incomee);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), sign_in.class);
                startActivity(i);
            }
        });
    }
}