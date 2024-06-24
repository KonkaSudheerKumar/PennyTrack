package com.example.pennytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_in extends AppCompatActivity {

    EditText nam, passwor;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        nam = findViewById(R.id.editTextText8);
        passwor = findViewById(R.id.editTextText9);
        signin = findViewById(R.id.button5);


        signprocess sp = new signprocess(getApplicationContext(), "Penny Tracker", null, 1);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = nam.getText().toString();
                String password = passwor.getText().toString();
                Log.d("Sign In", "Username: " + username + ", Password: " + password);
                if (sp.checkUser(username, password)) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("USERNAME", username);
                    startActivity(i);
                } else {
                    Toast.makeText(sign_in.this, "You Entered Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
