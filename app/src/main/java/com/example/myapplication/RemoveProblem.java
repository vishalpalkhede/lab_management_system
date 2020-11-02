package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RemoveProblem extends AppCompatActivity {

    TextView tvLabno,tvPcno,tvStatus,tvDate,tvDesc;
    Button removeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_problem);
        Intent intent = getIntent();
        final String pc_no = intent.getStringExtra("pc_no");
        final String lab_no = intent.getStringExtra("lab_no");
        final String date = intent.getStringExtra("date");
        final String status = intent.getStringExtra("status");
        final String desc = intent.getStringExtra("desc");

        tvLabno = findViewById(R.id.inchargeLabno);
        tvPcno = findViewById(R.id.inchargePcno);
        tvStatus = findViewById(R.id.inchargeStatus);
        tvDate= findViewById(R.id.inchargeDate);
        tvDesc = findViewById(R.id.inchargeDesc);

        removeBtn = findViewById(R.id.btnRemove);

        tvPcno.setText(pc_no);
        tvLabno.setText(lab_no);
        tvStatus.setText(status);
        tvDate.setText(date);
        tvDesc.setText(desc);

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "remove";
                BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
                backgroundWorker.execute(type,pc_no,lab_no,desc);

            }
        });
    }


}
