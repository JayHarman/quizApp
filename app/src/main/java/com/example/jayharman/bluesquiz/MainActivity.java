package com.example.jayharman.bluesquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Views to store for evaluation
    RadioButton questionOneAnswer;
    CheckBox questionTwoFirstAnswer;
    CheckBox questionTwoSecondAnswer;
    CheckBox questionTwoFirstWrong;
    CheckBox questionTwoSecondWrong;
    EditText questionThreeAnswer;
    RadioButton questionFourAnswer;
    Button evalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //store views
        questionOneAnswer = (RadioButton) findViewById(R.id.questionOne1890);
        questionTwoFirstAnswer = (CheckBox) findViewById(R.id.questionTwoAfrican);
        questionTwoSecondAnswer = (CheckBox) findViewById(R.id.questionTwoEuFolk);
        questionTwoFirstWrong = (CheckBox) findViewById(R.id.questionTwoClassical);
        questionTwoSecondWrong = (CheckBox) findViewById(R.id.questionTwoRock);
        questionThreeAnswer = (EditText) findViewById(R.id.questionThreeAnswer);
        questionFourAnswer = (RadioButton) findViewById(R.id.questionFourAASouth);
        evalButton = (Button) findViewById(R.id.evalButton);
    }

    public void evaluate(View view) {
        // Set up results StringBuilder
        StringBuilder resultsText = new StringBuilder(getString(R.string.your_results));

        // Check whether answer one is correct
        if (questionOneAnswer.isChecked()) {
            resultsText.append(getString(R.string.question_1_right));
        } else {
            resultsText.append(getString(R.string.question_1_wrong));
        }

        // Check whether answer two is correct
        Boolean rightAnswersChecked = questionTwoFirstAnswer.isChecked() && questionTwoSecondAnswer.isChecked();
        Boolean anyRightAnswers = questionTwoFirstAnswer.isChecked() || questionTwoSecondAnswer.isChecked();
        Boolean wrongAnswerChecked = questionTwoFirstWrong.isChecked() || questionTwoSecondWrong.isChecked();

        if (rightAnswersChecked && (!wrongAnswerChecked)) {
            resultsText.append(getString(R.string.question_2_right));
        } else if (rightAnswersChecked) {
            resultsText.append(getString(R.string.question_2_some_wrong));
        } else if (anyRightAnswers) {
            resultsText.append(getString(R.string.question_2_some_right));
        } else {
            resultsText.append(getString(R.string.question_2_wrong));
        }

        // Check whether answer three is correct
        // First we get the array of Blues themes
        String[] themes = getResources().getStringArray(R.array.blues_themes);


        String questionThreeAnswerText = questionThreeAnswer.getText().toString();

        // Check if the answer was any of the themes
        if (questionThreeAnswerText.equalsIgnoreCase(themes[0]) || questionThreeAnswerText.equalsIgnoreCase(themes[1])) {
            resultsText.append(getString(R.string.question_3_right));
        } else {
            resultsText.append(getString(R.string.question_3_wrong));
        }

        // Check whether answer four is correct
        if (questionFourAnswer.isChecked()) {
            resultsText.append(getString(R.string.question_4_right));
        } else {
            resultsText.append(getString(R.string.question_4_wrong));
        }

        //Finally, make and show the toast using all of the results.
        Toast toast = Toast.makeText(this, resultsText, Toast.LENGTH_SHORT);
        toast.show();
    }
}
