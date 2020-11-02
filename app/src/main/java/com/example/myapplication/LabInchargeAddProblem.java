package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LabInchargeAddProblem extends AppCompatActivity {
    EditText etDesc,etPc,etLab;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_incharge_add_problem);

        etDesc = findViewById(R.id.inchargeDesc);
        etPc = findViewById(R.id.inchargePC);
        etLab = findViewById(R.id.inchargeLab);

        btnSubmit = findViewById(R.id.inchargeSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String desc = etDesc.getText().toString();
                String pc_no = etPc.getText().toString();
                String lab_no = etLab.getText().toString();
                String type = "addPrblm";
                BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
                backgroundWorker.execute(type,desc,pc_no,lab_no);

            }
        });
    }
}
