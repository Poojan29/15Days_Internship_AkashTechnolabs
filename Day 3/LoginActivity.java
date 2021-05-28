package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.red));
        getSupportActionBar().setTitle("Login");
    }
}