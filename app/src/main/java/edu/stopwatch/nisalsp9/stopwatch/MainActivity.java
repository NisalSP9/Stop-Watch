package edu.stopwatch.nisalsp9.stopwatch;


import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity  {

    Chronometer ch;
    Chronometer ch2;
    Button start;
    Button stop;
    Button reset;
    Button lap;
    TextView lapInfor;

    DecimalFormat df = new DecimalFormat();

    long timeWhenStopped = 0;
    long time2 = 0;
    int count = 1;
    String endOfLastLap[];





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ch = (Chronometer)findViewById(R.id.chronometer);
        ch2 = (Chronometer)findViewById(R.id.chronometer2);
        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        reset = (Button)findViewById(R.id.reset);
        lap = (Button)findViewById(R.id.lap);
        lapInfor= (TextView)findViewById(R.id.textView);
        stop.setVisibility(View.INVISIBLE);
        lap.setVisibility(View.INVISIBLE);
        ch2.setVisibility(View.INVISIBLE);
        reset.setEnabled(false);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                lap.setVisibility(View.VISIBLE);
                reset.setVisibility(View.INVISIBLE);
                ch.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                ch.start();

                if(!lapInfor.getText().toString().trim().equals("")){
                    ch2.setBase(SystemClock.elapsedRealtime() + time2);
                    ch2.start();

                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                lap.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
                reset.setEnabled(true);
                timeWhenStopped = ch.getBase() - SystemClock.elapsedRealtime();
                time2=ch2.getBase()-SystemClock.elapsedRealtime();
                ch.stop();
                ch2.stop();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reset.setEnabled(false);
                ch2.setVisibility(View.INVISIBLE);
                ch.stop();
                ch2.stop();
                ch2.setBase(SystemClock.elapsedRealtime());
                ch.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
                time2=0;
                count=1;
                lapInfor.setText("");
            }
        });

        lap.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {





                //lapBeepMaker();
                time2 = 0;
                ch2.setVisibility(View.VISIBLE);

                if (count == 1) {
                    lapInfor.setText("LAP " + count++ + " : " + ch.getText() + "\n" + lapInfor.getText());
                } else {

                    lapInfor.setText("LAP " + count++ + " : " + ch2.getText() + "\n" + lapInfor.getText());
                }

                ch2.setBase(SystemClock.elapsedRealtime() + time2);
                ch2.start();

            }
        });


        //if(ch.){


        //}
//
        //TONE_SUP_ERROR


    }


    public void lapBeepMaker(){

        //ToneGenerator toneG =
        new ToneGenerator(AudioManager.STREAM_ALARM, 5000).startTone(ToneGenerator.TONE_PROP_BEEP, 1000);
        //toneG.;

    }


















}
