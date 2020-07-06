package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    TextView timer ;
    TextView tries ;
    int rightAnswers=0;
    int totalAnswers=0;
    TextView question ;
    TextView score ;
    GridLayout gridLayout;
    Button start;
    Button restart;
    CountDownTimer countDownTimer;
    Button button1 ;
    Button button2 ;
    Button button3 ;
    Button button4 ;
    ArrayList<Button> arrayList= new ArrayList<>();
    ArrayList<Integer> positions=new ArrayList<>();

    Random random = new Random();
    int q1;
    int q2;

    int rightAnswerPosition = random.nextInt(4);

    public void clicked (View view){
        Button x = (Button) view;
        int value= Integer.valueOf((String) x.getText());

        if(value==q1+q2){
            rightAnswers++;
            totalAnswers++;
            tries.setText(String.valueOf(rightAnswers)+"/"+String.valueOf(totalAnswers));
            newNumbers();
        }
        else{
            totalAnswers++;
            tries.setText(String.valueOf(rightAnswers)+"/"+String.valueOf(totalAnswers));
            newNumbers();
        }

    }
    public void newNumbers () {
        rightAnswerPosition=random.nextInt(4);
        for(int i=0;i<4;i++){
            if(i==rightAnswerPosition){
            }else {
                q1 = random.nextInt(40);
                q2 = random.nextInt(40);
                arrayList.get(i).setText(String.valueOf(q1 + q2));
            }
        }
        q1= random.nextInt(40);
        q2=random.nextInt(40);
        question.setText(String.valueOf(q1)+" + "+String.valueOf(q2));
        arrayList.get(rightAnswerPosition).setText(String.valueOf(q1+q2));
    //first we determine and reserve the right position answer then we generate the the wrong choices after that we generate the right answer at
        //last to access it later
    }
    public void startGame (View v){
        gridLayout.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        tries.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);

//        q1= random.nextInt(30);
//        q2=random.nextInt(30);
//        question.setText(String.valueOf(q1)+" + "+String.valueOf(q2));
//        rightAnswerPosition= random.nextInt(4);
//        arrayList.get(rightAnswerPosition).setText(String.valueOf(q1+q2));
        newNumbers();

        countDownTimer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                restart.setVisibility(View.VISIBLE);
                score.setText("Your score is "+String.valueOf(rightAnswers));
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
                button4.setClickable(false);
                button1.setAlpha((float) 0.5);
                button2.setAlpha((float) 0.5);
                button3.setAlpha((float) 0.5);
                button4.setAlpha((float) 0.5);
            }
        }.start();

    }
    public void playAgain (View v){

        restart.setVisibility(View.INVISIBLE);
        timer.setText("30s");
        totalAnswers=0;
        rightAnswers=0;
        tries.setText(String.valueOf(rightAnswers)+"/"+String.valueOf(totalAnswers));
        question.setText("");
        score.setText("");
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        button4.setClickable(true);
        button1.setAlpha((float) 1);
        button2.setAlpha((float) 1);
        button3.setAlpha((float) 1);
        button4.setAlpha((float) 1);
        countDownTimer.start();
        newNumbers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start= findViewById(R.id.buttonStart);
        restart= findViewById(R.id.buttonRestart);
        timer = findViewById(R.id.textTimer);

        tries = findViewById(R.id.textTries);
        tries.setText(String.valueOf(rightAnswers)+"/"+String.valueOf(totalAnswers));
        question = findViewById(R.id.textQuestion);
        score = findViewById(R.id.textScore);
        gridLayout= findViewById(R.id.grid);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);

        arrayList.add(button1);
        arrayList.add(button2);
        arrayList.add(button3);
        arrayList.add(button4);

        positions.add(1);
        positions.add(2);
        positions.add(3);
        positions.add(4);

        start.setVisibility(View.VISIBLE);
        restart.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        tries.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);

    }
}
