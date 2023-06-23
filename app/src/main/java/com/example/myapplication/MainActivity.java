package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionTextView;
    TextView questionTextView;
    TextView ansA,ansB,ansC,ansD;
    TextView submitButton,previousButton;

    int scoure = 0;
    int totalQuestionNum = QandA.Questions.length;
    int currentQuestion = 0;
    String userSelectedAns = "";
    Stack passedAns = new Stack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView = findViewById(R.id.total_quesiton);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        submitButton = findViewById(R.id.summit);
        previousButton = findViewById(R.id.prev);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);

        totalQuestionTextView.setText("Total Questions: " + totalQuestionNum);

        loadNextQuestion();
    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId() == R.id.summit){

            if (userSelectedAns.equals(QandA.Answers[currentQuestion])){
                scoure ++;
//                passedAns.push(true);
//
//            }else{
//                passedAns.push(false);
            }
            currentQuestion++;
            loadNextQuestion();

        }else if (clickedButton.getId() == R.id.prev){
            if (currentQuestion == 0) {
                return;
            }
            passedAns.pop();
            currentQuestion--;
            loadNextQuestion();
//            if (userSelectedAns.equals(QandA.Answers[currentQuestion])){
////                scoure ++;
//                passedAns.push(true);
//
//            }else {
//                passedAns.push(false);
//            }
        }
        else {

            userSelectedAns = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GRAY);
        }


//        for (int i = 0; i < passedAns.size(); i++){
//            if (passedAns.pop().equals(true)){
//                scoure++;
//            }
//        }
    }


    void loadNextQuestion(){
        if (currentQuestion == totalQuestionNum){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Result");
            builder.setMessage("Your score is "+ scoure);
            builder.setPositiveButton("Retake",((dialogInterface, i) -> reTakeQuiz()));
            builder.setCancelable(true);
            builder.show();
            return;
        }

        questionTextView.setText(QandA.Questions[currentQuestion]);
        ansA.setText(QandA.Choices[currentQuestion][0]);
        ansB.setText(QandA.Choices[currentQuestion][1]);
        ansC.setText(QandA.Choices[currentQuestion][2]);
        ansD.setText(QandA.Choices[currentQuestion][3]);

    }

    void reTakeQuiz(){
        scoure = 0;
        currentQuestion = 0;
        loadNextQuestion();
    }
}