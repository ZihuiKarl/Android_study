package com.example.zihuiyang.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    Button search_button;
    EditText search_input;
    WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_l);

        search_button = findViewById(R.id.search_button);
        search_input = findViewById(R.id.search_input);
        webView = findViewById(R.id.webView_search);
    }

    public void search_text(View view){
        String inputText = "http://";
        inputText = inputText.concat(search_input.getText().toString());
        Toast.makeText(getApplicationContext(), inputText, Toast.LENGTH_SHORT).show();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(inputText);
    }
}
