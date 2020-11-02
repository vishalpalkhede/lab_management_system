package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HodActivity extends AppCompatActivity {
    Button post,view;
    TextView tvReport;
    String type ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod);
        post = (Button)findViewById(R.id.postBtn);
        view = (Button)findViewById(R.id.viewBtn);
        tvReport = (TextView)findViewById(R.id.tvReport);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "add post";
                BackgroundHodView backgroundHodview = new BackgroundHodView();
                backgroundHodview.execute(type);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "view problem";
                BackgroundHodView backgroundHodView = new BackgroundHodView();
                backgroundHodView.execute(type);
                //BackgroundHodView backgroundHodView
                //new BackgroundHodView().execute(type);
                //Intent intent = new Intent(HodActivity.this,HodView.class);
               // intent.putExtra("json_data",json_string);
                //startActivity(intent);
            }
        });
        tvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type = "report";
                BackgroundHodView  backgroundHodView = new BackgroundHodView();
                backgroundHodView.execute(type);

            }
        });

    }
    public class BackgroundHodView extends AsyncTask<String,Void,String> {
        //Context context;
        String data = "";
        String pc_no;
        String lab_no;
        String status = "";
        String desc = "";
        String date = "";



        @Override
        protected String doInBackground(String... strings) {
            String type = strings[0];
            if (type.equals("view problem")){
            String hod_view_url = "http://192.168.43.215/dashboard/hod_view.php";
            try {
                URL url = new URL(hod_view_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
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

            }
            if (type.equals("add post"))
            {
                String add_post_url = "http://192.168.43.215/dashboard/hod_user_view.php";
                try {
                    URL url = new URL(add_post_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result = "";
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        result += line;
                    }
                    inputStream.close();
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (type.equals("report"))
            {
                String add_post_url = "http://192.168.43.215/dashboard/report.php";
                try {
                    URL url = new URL(add_post_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result = "";
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        result += line;
                    }
                    inputStream.close();
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            //json_string = result;
            //Toast toast = Toast.makeText(HodActivity.this,json_string, Toast.LENGTH_SHORT);
            //toast.show();
            if(type.equals("view problem")){
            Intent intent = new Intent(HodActivity.this,HodView.class);
            intent.putExtra("json_data",result);
            startActivity(intent);
            }
            if (type.equals("add post")){
                Intent intent = new Intent(HodActivity.this,HodPost.class);
                intent.putExtra("json_data",result);
                startActivity(intent);
            }
            if (type.equals("report")){

                String file_name = "record.txt";
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = openFileOutput(file_name,MODE_APPEND);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("report");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jo = jsonArray.getJSONObject(i);

                        String pc = "pc_no:" +  jo.getString("pc_no")+"\t\t";
                        fileOutputStream.write(pc.getBytes());
                        String lab = "lab_no:" + jo.getString("lab_no")+"\t\t";
                        fileOutputStream.write(lab.getBytes());
                        String status = "status:" + jo.getString("status")+"\t\t";
                        fileOutputStream.write(status.getBytes());
                        String date = "date:" + jo.getString("date")+"\t\t";
                        fileOutputStream.write(date.getBytes());
                        String desc = "desc:" + jo.getString("description")+"\t\t\n\n";
                        fileOutputStream.write(desc.getBytes());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast toast = Toast.makeText(getApplicationContext(),"Saved to:"+getFilesDir()+"/record.txt",Toast.LENGTH_LONG);
                toast.show();
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }



}
