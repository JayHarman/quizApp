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

    // Views to hold on to for evaluation
    RadioButton mQuestionOneAnswer;
    CheckBox mQuestionTwoFirstAnswer;
    CheckBox mQuestionTwoSecondAnswer;
    CheckBox mQuestionTwoFirstWrong;
    CheckBox mQuestionTwoSecondWrong;
    EditText mQuestionThreeAnswer;
    RadioButton mQuestionFourAnswer;
    Button mEvalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionOneAnswer = (RadioButton) findViewById(R.id.question_1_1890);
        mQuestionTwoFirstAnswer = (CheckBox) findViewById(R.id.question_2_african);
        mQuestionTwoSecondAnswer = (CheckBox) findViewById(R.id.question_2_eu_folk);
        mQuestionTwoFirstWrong = (CheckBox) findViewById(R.id.question_2_classical);
        mQuestionTwoSecondWrong = (CheckBox) findViewById(R.id.question_2_rock);
        mQuestionThreeAnswer = (EditText) findViewById(R.id.question_3_answer);
        mQuestionFourAnswer = (RadioButton) findViewById(R.id.question_4_african_american_southerners);
        mEvalButton = (Button) findViewById(R.id.eval_answers_button);
    }

    public void evaluate(View view) {

        // Set up StringBuilder to hold the results
        StringBuilder resultsText = new StringBuilder(getString(R.string.your_results));

        // Check whether answer one is correct
        if (mQuestionOneAnswer.isChecked()) {
            resultsText.append(getString(R.string.question_1_right));
        } else {
            resultsText.append(getString(R.string.question_1_wrong));
        }

        // Check whether answer two is correct
        Boolean rightAnswersChecked = mQuestionTwoFirstAnswer.isChecked() && mQuestionTwoSecondAnswer.isChecked();
        Boolean anyRightAnswers = mQuestionTwoFirstAnswer.isChecked() || mQuestionTwoSecondAnswer.isChecked();
        Boolean wrongAnswerChecked = mQuestionTwoFirstWrong.isChecked() || mQuestionTwoSecondWrong.isChecked();

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
        
        String questionThreeAnswerText = mQuestionThreeAnswer.getText().toString();

        // Check if the answer was any of the themes
        if (questionThreeAnswerText.equalsIgnoreCase(themes[0]) || questionThreeAnswerText.equalsIgnoreCase(themes[1])) {
            resultsText.append(getString(R.string.question_3_right));
        } else {
            resultsText.append(getString(R.string.question_3_wrong));
        }

        // Check whether answer four is correct
        if (mQuestionFourAnswer.isChecked()) {
            resultsText.append(getString(R.string.question_4_right));
        } else {
            resultsText.append(getString(R.string.question_4_wrong));
        }

        //Finally, make and show the toast using all of the results.
        Toast toast = Toast.makeText(this, resultsText, Toast.LENGTH_SHORT);
        toast.show();
    }
}
