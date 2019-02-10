package com.example.kunwarviraj.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView quesText;
    Random r = new Random();
    TextView result;
    ArrayList<Integer> answers;
    int correctLocation;
    Button op1;
    Button op2;
    Button op3;
    Button op4;
    int score=0;
    int numberofques = 0;
    TextView scoreText;
    TextView timersec;
    GridLayout gridlay;
    Button playz;

    public void correctAns(View view)
    {
        if(view.getTag().equals(Integer.toString(correctLocation)))
        {
                score++;
                result.setText("Correct !!");
        }
        else
        {
            result.setText("Wrong Answer :(");
        }
        numberofques++;
        scoreText.setText(Integer.toString(score)+"/"+Integer.toString(numberofques));
        frameQues();
    }
    public void playAgain(View view){
        score=0;
        numberofques=0;
        timersec.setText("30s");
        scoreText.setText("0/0");
        result.setText("");
        playz.setVisibility(View.INVISIBLE);
        frameQues();
        new CountDownTimer(3100,1000)
        {

            @Override
            public void onTick(long l) {
                timersec.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                playz.setVisibility(View.VISIBLE);
                timersec.setText("0s");
                result.setText("Your Score Is :"+ score +"/"+numberofques);
            }
        }.start();
    }
    public void frameQues()
    {
        int a = r.nextInt(21);
        int b =  r.nextInt(21);

        quesText.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answers.clear();
        correctLocation = r.nextInt(4);
        for (int i=0;i<4;i++)
        {
            if(i==correctLocation)
            {
                answers.add(a+b);
            }
            else
            {
                int rand = r.nextInt(41);
                while (rand == a+b)
                {
                    rand = r.nextInt(41);
                }
                answers.add(rand);
            }
        }
        op1.setText(Integer.toString(answers.get(0)));
        op2.setText(Integer.toString(answers.get(1)));
        op3.setText(Integer.toString(answers.get(2)));
        op4.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quesText = (TextView) findViewById(R.id.quesTextview);
        answers= new ArrayList<Integer>();
        op1 = (Button) findViewById(R.id.bop1);
        op2 = (Button) findViewById(R.id.bop2);
        op3 = (Button) findViewById(R.id.bop3);
        op4 = (Button) findViewById(R.id.bop4);
        result = (TextView) findViewById(R.id.resultTextview);
        scoreText= (TextView) findViewById(R.id.scoreTextView);
        timersec = (TextView) findViewById(R.id.timersec);
        playz = (Button) findViewById(R.id.playz);
        gridlay = (GridLayout) findViewById(R.id.gridLay);
        playAgain( findViewById(R.id.playz));
    }
}
