package com.example.zihuiyang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listViewApp = findViewById(R.id.listviewapp);
        String[] values = new String[]{"Unit conversion", "Testing Layout", "Saving data"};
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        listViewApp.setAdapter(adapter);

        listViewApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent("com.example.action.CONVERT_UNIT");
                    startActivity(intent);
                }
                else if(i == 1){
                    Intent intent = new Intent("com.example.action.LAYOUT_TEST");
                    startActivity(intent);
                }
                else if(i == 2){
                    Intent intent = new Intent("com.example.action.SAVING_DATA");
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid app", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
