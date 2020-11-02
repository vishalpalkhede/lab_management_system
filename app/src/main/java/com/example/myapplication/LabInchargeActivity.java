package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class LabInchargeActivity extends AppCompatActivity {
    Button addProblem, removeProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_incharge);
        final Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String password = intent.getStringExtra("password");


        addProblem = findViewById(R.id.btnAddProblem);
        removeProblem = findViewById(R.id.btnRemoveProblem);

        addProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LabInchargeAddProblem.class);
                startActivity(intent);
                //BackgroundLabIncharge backgroundLabIncharge = new BackgroundLabIncharge();
                //backgroundLabIncharge.execute(type);

            }
        });

        removeProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BackgroundLabNo backgroundLabNo = new BackgroundLabNo();
                backgroundLabNo.execute(username, password);

            }
        });


    }

    public class BackgroundLabNo extends AsyncTask<String, Void, String> {


        protected String doInBackground(String... strings) {


            String lab_no_url = "http://192.168.43.215/dashboard/lab_no.php";
            try {
                String user_name = strings[0];
                String password = strings[1];
                URL url = new URL(lab_no_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            String type = "incharge_view";
            BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
            backgroundWorker.execute(type, s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
