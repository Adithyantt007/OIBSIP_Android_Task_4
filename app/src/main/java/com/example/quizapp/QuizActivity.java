package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestion, tvScore, tvQuestionCount;
    private ProgressBar progressBar;
    private Button[] btnOptions = new Button[4];

    private List<Question> matchQuestions;
    private int currentIdx = 0;
    private int matchScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        progressBar = findViewById(R.id.quizProgress);
        btnOptions[0] = findViewById(R.id.btnOpt1);
        btnOptions[1] = findViewById(R.id.btnOpt2);
        btnOptions[2] = findViewById(R.id.btnOpt3);
        btnOptions[3] = findViewById(R.id.btnOpt4);

        setupQuiz();
    }

    private void setupQuiz() {
        List<Question> pool = new ArrayList<>();
        pool.add(new Question("Capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        pool.add(new Question("Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1));
        pool.add(new Question("Largest Mammal?", new String[]{"Elephant", "Blue Whale", "Giraffe", "Shark"}, 1));
        pool.add(new Question("Java Developer?", new String[]{"James Gosling", "Steve Jobs", "Elon Musk", "Bill Gates"}, 0));
        pool.add(new Question("Fastest Animal?", new String[]{"Lion", "Cheetah", "Horse", "Eagle"}, 1));
        pool.add(new Question("Chemical symbol for Gold?", new String[]{"Ag", "Gd", "Au", "Pb"}, 2));
        pool.add(new Question("Smallest Continent?", new String[]{"Asia", "Europe", "Australia", "Africa"}, 2));
        pool.add(new Question("First Man on Moon?", new String[]{"Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Michael Collins"}, 0));
        pool.add(new Question("Human Bone Count?", new String[]{"206", "205", "210", "201"}, 0));
        pool.add(new Question("Currency of Japan?", new String[]{"Yuan", "Won", "Yen", "Ringgit"}, 2));

        Collections.shuffle(pool);
        matchQuestions = pool.subList(0, 10);
        loadQuestion();
    }

    private void loadQuestion() {
        resetButtons();
        Question q = matchQuestions.get(currentIdx);
        tvQuestionCount.setText("Question " + (currentIdx + 1) + "/10");
        tvScore.setText("Score: " + matchScore);
        tvQuestion.setText(q.getQuestionText());
        progressBar.setProgress((currentIdx + 1) * 10);

        String[] options = q.getOptions();
        for (int i = 0; i < 4; i++) {
            btnOptions[i].setText(options[i]);
            int choiceIndex = i;
            btnOptions[i].setOnClickListener(v -> handleAnswer(choiceIndex, q.getCorrectAnswerIndex()));
        }
    }

    private void handleAnswer(int selected, int correct) {
        for (Button b : btnOptions) b.setEnabled(false);
        int softGreen = Color.parseColor("#81C784");
        int softRed = Color.parseColor("#E57373");

        if (selected == correct) {
            matchScore++;
            btnOptions[selected].setBackgroundTintList(android.content.res.ColorStateList.valueOf(softGreen));
            btnOptions[selected].setTextColor(Color.WHITE);
        } else {
            btnOptions[selected].setBackgroundTintList(android.content.res.ColorStateList.valueOf(softRed));
            btnOptions[selected].setTextColor(Color.WHITE);
            btnOptions[correct].setBackgroundTintList(android.content.res.ColorStateList.valueOf(softGreen));
            btnOptions[correct].setTextColor(Color.WHITE);
        }

        new Handler().postDelayed(() -> {
            currentIdx++;
            if (currentIdx < 10) {
                loadQuestion();
            } else {
                gameOver();
            }
        }, 1500);
    }

    private void resetButtons() {
        for (Button b : btnOptions) {
            b.setEnabled(true);
            b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.WHITE));
            b.setTextColor(Color.parseColor("#2D3436")); // Dark gray text
        }
    }
    private void gameOver() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("FINAL_SCORE", matchScore);
        startActivity(intent);
        finish();
    }
}