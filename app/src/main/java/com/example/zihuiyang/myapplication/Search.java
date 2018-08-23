package com.example.zihuiyang.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Search extends AppCompatActivity {
    Button search_button;
    EditText search_input;
    WebView webView;

    private static final int MY_PERMISSIONS_REQUEST_MULTI_CONTACTS = 0;
    private ProgressBar mLoadingProgressBar;
    int aReasonableSize = 1024;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_l);

        search_button = findViewById(R.id.search_button);
        search_input = findViewById(R.id.search_input);
        webView = findViewById(R.id.webView_search);
    }

    public void download_image(View view){
        String inputText = search_input.getText().toString();
        if (ContextCompat.checkSelfPermission(Search.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(Search.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(Search.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(Search.this, "Can not download image without permission", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(Search.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_MULTI_CONTACTS);
            }
        } else {
            Toast.makeText(Search.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            Log.i("Info", "Permission Granted");
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.execute(inputText);
        }
    }

    public void search_text(View view){
        String inputText = "http://";
        inputText = inputText.concat(search_input.getText().toString());
        Toast.makeText(getApplicationContext(), inputText, Toast.LENGTH_SHORT).show();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(inputText);
    }

    public void search_location(View view){
        String inputText = search_input.getText().toString();
        Toast.makeText(getApplicationContext(), inputText, Toast.LENGTH_SHORT).show();
        Intent location_ms = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + Uri.encode(inputText)));
        startActivity(location_ms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        String inputText = search_input.getText().toString();
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_MULTI_CONTACTS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("Info", "Permission granted, start downloading");
                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.execute(inputText);
                } else {
                    Toast.makeText(Search.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    Log.i("Info", "Permission denied");
                }
            }
        }
    }

    class DownloadTask extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Search.this);
            progressDialog.setTitle("Download in progress");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String path = params[0];
            int file_length;
            int total = 0;
            int count;

            Log.i("Info: path", path);
            try {
                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                file_length = urlConnection.getContentLength();

                File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "imageFolder");
                if (!new_folder.exists()) {
                    new_folder.mkdir();
                }

                File input_file = new File(new_folder, "downloaded_image.jpg");

                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                OutputStream outputStream = new FileOutputStream(input_file);
                byte [] data = new byte[1024];

                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    outputStream.write(data, 0, count);
                    int progress = 100 * total / file_length;
                    publishProgress(progress);
                }
                inputStream.close();
                outputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download complete.";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.hide();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "imageFolder");
            File output_file = new File(folder, "downloaded_image.jpg");
            String path = output_file.toString();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + path), "image/*");
            startActivity(intent);
            finish();
        }
    }
}
