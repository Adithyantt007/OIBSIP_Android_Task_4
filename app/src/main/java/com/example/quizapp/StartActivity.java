package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        findViewById(R.id.btnPlay).setOnClickListener(v -> {

            startActivity(new Intent(this, QuizActivity.class));
        });

        findViewById(R.id.btnQuit).setOnClickListener(v -> finish());
    }
}
