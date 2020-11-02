package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class ProblemUpdateActivity extends AppCompatActivity {

    TextView tvpc_no,tvlab_no,tvstatus,tvdate,tvdesc;
    Button submitBtn;
    RadioGroup radioGroup2;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_update);

        Intent intent = getIntent();
        final String pc_no = intent.getStringExtra("pc_no");
        final String lab_no = intent.getStringExtra("lab_no");
        final String date = intent.getStringExtra("date");
        final String status = intent.getStringExtra("status");
        final String desc = intent.getStringExtra("desc");

        tvpc_no = findViewById(R.id.adminPcno);
        tvlab_no = findViewById(R.id.adminLabno);
        tvdate = findViewById(R.id.adminDate);
        tvstatus = findViewById(R.id.adminStatus);
        tvdesc = findViewById(R.id.adminDesc);

        tvpc_no.setText(pc_no);
        tvlab_no.setText(lab_no);
        tvdesc.setText(desc);
        tvdate.setText(date);
        tvstatus.setText(status);

        radioGroup2 = findViewById(R.id.radioGroup2);

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup2.getCheckedRadioButtonId();
                radioButton = findViewById(id);
                String problem_status = radioButton.getText().toString();
                String type="updatePrblm";
                BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
                backgroundWorker.execute(type,problem_status,pc_no,lab_no,desc);


            }
        });
    }

}
