package com.example.zihuiyang.myapplication;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class TestingLayout extends AppCompatActivity{
    MediaPlayer monsterSound;
    MediaPlayer robotSound;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_layout_l);

        monsterSound = MediaPlayer.create(this, R.raw.evil_monster);
        robotSound = MediaPlayer.create(this, R.raw.robot_arm_moving);

        final LinearLayout linearLayout = findViewById(R.id.testlayout_LL);
        SeekBar seekBar = findViewById(R.id.seekBar_turtle);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lastProgress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                linearLayout.setBackgroundColor(Color.argb(255, 0, lastProgress, 255-lastProgress));
            }
        });
    }

    public void playSound_1(View view){
        Toast.makeText(getApplicationContext(), "sound 1 is playing", Toast.LENGTH_SHORT).show();
        monsterSound.start();
    }

    public void playSound_2(View view){
        Toast.makeText(getApplicationContext(), "sound 2 is playing", Toast.LENGTH_SHORT).show();
        robotSound.start();
    }
}
