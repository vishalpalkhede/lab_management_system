package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class RegistrationActivity extends AppCompatActivity {

    EditText etFullname,etUsername,etPass,etConfirmPass,etLabno,etDept;
    Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFullname = findViewById(R.id.regFullname);
        etUsername = findViewById(R.id.regUsername);
        etPass = findViewById(R.id.regPass);
        etConfirmPass = findViewById(R.id.regConfirmPass);
        etLabno = findViewById(R.id.regLabno);
        etDept = findViewById(R.id.regDep);

        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = etFullname.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPass.getText().toString();
                String confPassword = etConfirmPass.getText().toString();
                String labno = etLabno.getText().toString();
                String dept = etDept.getText().toString();

                if(password.equals(confPassword)){

                    if(password.length()>=5) {
                        String type="register";
                        BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
                        backgroundWorker.execute(type, fullname, username, password, labno, dept);
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Legnth of Password should be greater than 4",Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Password Not Matched with Confirm Password.",Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });




    }

    public class BackgroundRegister extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... params) {



            String login_url = "http://192.168.43.215/dashboard/register.php";
                try {
                    String fullname = params[0];
                    String username = params[1];
                    String password = params[2];
                    String lab_no = params[3];
                    String dept = params[4];
                    URL url = new URL(login_url);

                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                    httpURLConnection.setRequestMethod("POST");

                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                            +URLEncoder.encode("fullname","UTF-8")+"="+URLEncoder.encode(fullname,"UTF-8")+"&"
                            +URLEncoder.encode("department","UTF-8")+"="+URLEncoder.encode(dept,"UTF-8")+"&"
                            +URLEncoder.encode("lab_no","UTF-8")+"="+URLEncoder.encode(lab_no,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result = "";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
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
        protected void onPreExecute() {
            //alertDialog = new AlertDialog.Builder(context).create();
            //alertDialog.setTitle("Status");
        }

        @Override
        protected void onPostExecute(String result) {

                Toast toast = Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT);
                toast.show();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
