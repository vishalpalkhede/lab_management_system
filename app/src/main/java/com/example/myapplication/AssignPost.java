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

public class AssignPost extends AppCompatActivity {
    TextView tvfullname,tvdept,tvlab_no,tvpost;
    Button assign_post;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_post);
        Intent intent = getIntent();
        final String fullname = intent.getStringExtra("fullname");
        final String lab_no = intent.getStringExtra("lab_no");
        final String dept = intent.getStringExtra("dept");
        final String post = intent.getStringExtra("post");

        tvfullname =findViewById(R.id.postFullname);
        tvlab_no = findViewById(R.id.postLabno);
        tvdept= findViewById(R.id.postDept);
        tvpost = findViewById(R.id.postPost);

        assign_post = findViewById(R.id.btnAssignPost);


        tvfullname.setText(fullname);
        tvlab_no.setText(lab_no);
        tvdept.setText(dept);
        tvpost.setText(post);

        radioGroup = findViewById(R.id.radioGroup);

        assign_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int btn_id = radioGroup.getCheckedRadioButtonId();

                String post_will;

                radioButton = findViewById(btn_id);

                post_will = radioButton.getText().toString();

                //Toast toast = Toast.makeText(getApplicationContext(),post_will,Toast.LENGTH_LONG);
                //toast.show();
                String type;
                type = "assign_post";
                BackgroundWorker backgroundWorker = new BackgroundWorker(AssignPost.this);
                backgroundWorker.execute(type, post_will, fullname, dept, lab_no);
            }
        });

    }

}
