package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncCallBack {

    Button btnGetRequest, btnPostRequest;
    TextView txtResultValue;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetRequest = findViewById(R.id.btnGetRequest);
        btnPostRequest = findViewById(R.id.btnPostRequest);
        txtResultValue = findViewById(R.id.txtResultValue);
        progressBar = findViewById(R.id.progressBar);

        btnGetRequest.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                new GetAsyncTask().setInstance(MainActivity.this, progressBar).execute();
            }
        });

        btnPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String name = "IT wala";
                new PostAsyncTask().setInstance(MainActivity.this, progressBar).execute(name);
            }
        });
    }

    @Override public void setResult(String result) {
        txtResultValue.setText(result);
    }
}