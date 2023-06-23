package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.myapplication.QandA
import android.os.Bundle
import com.example.myapplication.R
import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class oringnal : AppCompatActivity(), View.OnClickListener {
    var totalQuestionTextView: TextView? = null
    var questionTextView: TextView? = null
    var ansA: TextView? = null
    var ansB: TextView? = null
    var ansC: TextView? = null
    var ansD: TextView? = null
    var submitButton: TextView? = null
    var scoure = 0
    var totalQuestionNum = QandA.Questions.size
    var currentQuestion = 0
    var userSelectedAns = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        totalQuestionTextView = findViewById(R.id.total_quesiton)
        questionTextView = findViewById(R.id.question)
        ansA = findViewById(R.id.ans_a)
        ansB = findViewById(R.id.ans_b)
        ansC = findViewById(R.id.ans_c)
        ansD = findViewById(R.id.ans_d)
        submitButton = findViewById(R.id.summit)

//        ansA.setOnClickListener()
//        ansA.setOnClickListener(this)
//        ansB.setOnClickListener(this)
//        ansC.setOnClickListener(this)
//        ansD.setOnClickListener(this)
//        submitButton.setOnClickListener(this)
//        totalQuestionTextView.setText("Total Questions: $totalQuestionNum")
//        loadNextQuestion()
    }

    override fun onClick(view: View) {
        ansA!!.setBackgroundColor(Color.WHITE)
        ansB!!.setBackgroundColor(Color.WHITE)
        ansC!!.setBackgroundColor(Color.WHITE)
        ansD!!.setBackgroundColor(Color.WHITE)
        val clickedButton = view as Button
        if (clickedButton.id == R.id.summit) {
            if (userSelectedAns == QandA.Answers[currentQuestion]) {
                scoure++
            }
            currentQuestion++
            loadNextQuestion()
        } else {
            userSelectedAns = clickedButton.text.toString()
            clickedButton.setBackgroundColor(Color.GRAY)
        }
    }

    fun loadNextQuestion() {
        if (currentQuestion == totalQuestionNum) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Result")
            builder.setMessage("Your score is $scoure")
            builder.setPositiveButton("Retake") { dialogInterface: DialogInterface?, i: Int -> reTakeQuiz() }
            builder.setCancelable(true)
            builder.show()
            return
        }
        questionTextView!!.text = QandA.Questions[currentQuestion]
        ansA!!.text = QandA.Choices[currentQuestion][0]
        ansB!!.text = QandA.Choices[currentQuestion][1]
        ansC!!.text = QandA.Choices[currentQuestion][2]
        ansD!!.text = QandA.Choices[currentQuestion][3]
    }

    fun reTakeQuiz() {
        scoure = 0
        currentQuestion = 0
        loadNextQuestion()
    }
}