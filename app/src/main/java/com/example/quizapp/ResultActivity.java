package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.quizapp.StartActivity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("FINAL_SCORE", 0);

        TextView tvScore = findViewById(R.id.tvFinalScore);
        TextView tvFeedback = findViewById(R.id.tvFeedback);

        tvScore.setText(score + "/10");


        if (score == 10) {
            tvFeedback.setText("Perfect Score! 🏆");
        } else if (score >= 7) {
            tvFeedback.setText("Outstanding Performance! ⭐");
        } else if (score >= 5) {
            tvFeedback.setText("Good Job! 👍");
        } else {
            tvFeedback.setText("Keep Practicing! 💪");
        }

        findViewById(R.id.btnHome).setOnClickListener(v -> {
            finish();
        });
    }
}