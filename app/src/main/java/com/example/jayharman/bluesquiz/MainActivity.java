package com.example.jayharman.bluesquiz;

import android.content.Context;
import android.content.res.Resources;
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
        questionOneAnswer = (RadioButton)findViewById(R.id.questionOne1890);
        questionTwoFirstAnswer = (CheckBox)findViewById(R.id.questionTwoAfrican);
        questionTwoSecondAnswer = (CheckBox)findViewById(R.id.questionTwoEuFolk);
        questionTwoFirstWrong=(CheckBox)findViewById(R.id.questionTwoClassical);
        questionTwoSecondWrong=(CheckBox)findViewById(R.id.questionTwoRock);
        questionThreeAnswer = (EditText)findViewById(R.id.questionThreeAnswer);
        questionFourAnswer=(RadioButton)findViewById(R.id.questionFourAASouth);
        evalButton = (Button)findViewById(R.id.evalButton);
    }

    public void evaluate(View view){
        //initial setup for results string
        String resultsText = "your results:";
        //define the context and the duration of the toast
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        //check whether answer 1 is correct:
        if(questionOneAnswer.isChecked()){
            //if it is, let them know.
            resultsText =resultsText + "\nQuestion 1 was right!";
        }else{
            //if it is not, let them know.
            resultsText = resultsText+ "\nQuestion 1 was wrong...";
        }


        //Check whether answer 2 is correct:
        //make a boolean to represent if they checked both the correct answers
        Boolean rightAnswersChecked=questionTwoFirstAnswer.isChecked()&&questionTwoSecondAnswer.isChecked();
        //make a boolean to check if they got any right answers
        Boolean anyRightAnswers=questionTwoFirstAnswer.isChecked()||questionTwoSecondAnswer.isChecked();
        //make a boolean to represent if they checked any wrong answers
        Boolean wrongAnswerChecked=questionTwoFirstWrong.isChecked()||questionTwoSecondWrong.isChecked();


        //check the booleans:
        if(rightAnswersChecked&&(!wrongAnswerChecked)){
            //if only the right answers are checked, let them know
            resultsText=resultsText+"\nQuestion 2 was completely right!";
        }else if(rightAnswersChecked){
            //if they got the right answers checked but also checked some wrong ones:
            resultsText=resultsText+"\nQuestion 2 had some right and some wrong";
        }else if(anyRightAnswers){
            //if they got any right answers:
            resultsText=resultsText+"\nQuestion 2 had one part right";

        }else{
            //if they got none right:
            resultsText=resultsText+"\nQuestion 2 was wrong...";
        }

        //Check whether answer 3 is correct:
        //first we get the array of themes
        Resources res = getResources();
        String[] themes =res.getStringArray(R.array.blues_themes);

        //if they correctly got any of the themes, let them know.
        if(questionThreeAnswer.getText().toString().equals(themes[0])){
            resultsText=resultsText+"\nQuestion 3 was right!";
        }else if(questionThreeAnswer.getText().toString().equals(themes[1])){
            resultsText=resultsText+"\nQuestion 3 was right!";
        }else if(questionThreeAnswer.getText().toString().equals(themes[2])){
            resultsText=resultsText+"\nQuestion 3 was right!";
        }else if(questionThreeAnswer.getText().toString().equals(themes[3])){
            resultsText=resultsText+"\nQuestion 3 was right!";
        }else{
            // if they did not, also let them know
            resultsText=resultsText+"\nQuestion 3 was wrong...";
        }

        //Check whether answer 4 is correct:
        if(questionFourAnswer.isChecked()){
            resultsText =resultsText + "\nQuestion 4 was right!";
        }else{
            resultsText = resultsText+ "\nQuestion 4 was wrong...";
        }


        //Finally, make and show the toast using all of the results.
        Toast toast = Toast.makeText(context, resultsText, duration);
        toast.show();

    }
}
