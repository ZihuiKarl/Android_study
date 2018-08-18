package com.example.zihuiyang.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class SavingData extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saving_data_l);

        SharedPreferences sharedPreferences = getSharedPreferences("COLOR_PREDEFIND", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final ConstraintLayout layout = findViewById(R.id.constraintLayout_savedata);
        RadioGroup radioGroup = findViewById(R.id.radioGroup_colors);

        if(sharedPreferences.contains(("colorIndex"))){
            layout.setBackgroundColor(sharedPreferences.getInt("colorIndex", 0));
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int colorIndex = 0;
                switch(i){
                    case R.id.radioButton_red:
                        colorIndex = Color.RED;
                        break;
                    case R.id.radioButton_yellow:
                        colorIndex = Color.YELLOW;
                        break;
                    case R.id.radioButton_blue:
                        colorIndex = Color.BLUE;
                        break;
                }
                layout.setBackgroundColor(colorIndex);
                editor.putInt("colorIndex", colorIndex);
                editor.commit();
            }
        });
    }
}
