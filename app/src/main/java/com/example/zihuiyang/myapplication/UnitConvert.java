package com.example.zihuiyang.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class UnitConvert extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_convert_l);

        Toast.makeText(getApplicationContext(),"The Unit conversion app is opened", Toast.LENGTH_LONG).show();

        Button buttonConvertKmToMile = findViewById(R.id.button_kmtomile);
        buttonConvertKmToMile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextKm = findViewById(R.id.editText_km);
                EditText editTextMile = findViewById(R.id.editText_mile);
                double inputOfKm = Double.valueOf(editTextKm.getText().toString());
                double valueOfMile = inputOfKm * 0.62137;
                DecimalFormat formatReturnVal = new DecimalFormat("##.##");
                editTextMile.setText(formatReturnVal.format(valueOfMile));
            }
        });

        Button buttonConvertMileToKm = findViewById(R.id.button_miletokm);
        buttonConvertMileToKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextKm = findViewById(R.id.editText_km);
                EditText editTextMile = findViewById(R.id.editText_mile);
                double inputOfMile = Double.valueOf(editTextMile.getText().toString());
                double valueOfKm = inputOfMile / 0.62137;
                DecimalFormat formatReturnVal = new DecimalFormat("##.##");
                editTextKm.setText(formatReturnVal.format(valueOfKm));
            }
        });
    }
}
