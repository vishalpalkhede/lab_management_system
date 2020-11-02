package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.time.Duration;


public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    String password;
    String user_name;
    String type;


    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        type = params[0];


        if(type.equals("login")) {
            String login_url = "http://192.168.43.215/dashboard/login.php";
            try {
                user_name = params[1];
                password = params[2];
                URL url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line="";
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
        if(type=="backgroundAdmin"){
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
        if(type=="assign_post") {
            String post_will    = params[1];
            String fullname     = params[2];
            String dept         = params[3];
            String lab_no       = params[4];

            String fullname_a[] =fullname.split(":",2);
            String dept_a[] =dept.split(":",2);
            String lab_no_a[] =lab_no.split(":",2);


            String raw_url = "http://192.168.43.215/dashboard/give_post.php";

            try {
                URL url = new URL(raw_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data =  URLEncoder.encode("fullname","UTF-8")+"="+URLEncoder.encode(fullname_a[1],"UTF-8")+"&"+
                        URLEncoder.encode("department","UTF-8")+"="+URLEncoder.encode(dept_a[1],"UTF-8")+"&"+
                        URLEncoder.encode("lab_no","UTF-8")+"="+URLEncoder.encode(lab_no_a[1],"UTF-8")+"&"+
                        URLEncoder.encode("post","UTF-8")+"="+URLEncoder.encode(post_will,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line;
                while ((line=bufferedReader.readLine())!= null)
                {
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
        if(type=="remove"){
            String problem_delete_url = "http://192.168.43.215/dashboard/delete_problem.php";
            try {
                String pc_no = params[1];
                String lab_no = params[2];
                String desc = params[3];
                String[] pc_no_a = pc_no.split(":",2);
                String[] lab_no_a = lab_no.split(":",2);
                String[] desc_a = desc.split(":",2);
                URL url = new URL(problem_delete_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("pc_no","UTF-8")+"="+URLEncoder.encode(pc_no_a[1],"UTF-8")+"&"
                        +URLEncoder.encode("lab_no","UTF-8")+"="+URLEncoder.encode(lab_no_a[1],"UTF-8")+"&"
                        +URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(desc_a[1],"UTF-8");
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
        }
        if(type=="register"){
            String login_url = "http://192.168.43.215/dashboard/register.php";
            try {
                String fullname = params[1];
                String username = params[2];
                String password = params[3];
                String lab_no = params[4];
                String dept = params[5];
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
        }
        if(type=="updatePrblm"){
            String status = params[1];
            String pc_no = params[2];
            String lab_no = params[3];
            String desc = params[4];

            String[] pc_no_a = pc_no.split(":",2);
            String[] lab_no_a = lab_no.split(":",2);
            String[] desc_a = desc.split(":",2);



            String raw_url = "http://192.168.43.215/dashboard/admin_update.php";

            try {
                URL url = new URL(raw_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("lab_no","UTF-8")+"="+URLEncoder.encode(lab_no_a[1],"UTF-8")+"&"+
                        URLEncoder.encode("pc_no","UTF-8")+"="+URLEncoder.encode(pc_no_a[1],"UTF-8")+"&"+
                        URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(desc_a[1],"UTF-8")+"&"+
                        URLEncoder.encode("status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

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
        if(type=="addPrblm"){
            String desc = params[1];
            String pc_no = params[2];
            String lab_no = params[3];

            String raw_url = "http://192.168.43.215/dashboard/add_problem.php";
            try{
                URL url = new URL(raw_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(desc,"UTF-8")+"&"+
                        URLEncoder.encode("pc_no","UTF-8")+"="+URLEncoder.encode(pc_no,"UTF-8")+"&"+
                        URLEncoder.encode("lab_no","UTF-8")+"="+URLEncoder.encode(lab_no,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

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
        if(type=="incharge_view"){
            String lab_no = params[1];

            String raw_url = "http://192.168.43.215/dashboard/incharge_view.php";
            try {
                URL url = new URL(raw_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("lab_no","UTF-8")+"="+URLEncoder.encode(lab_no,"UTF-8");
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
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
       //Toast to = Toast.makeText(context,result,Toast.LENGTH_SHORT);
       //to.show();
        if(type=="login"){
            Toast toast = Toast.makeText(context, "Log in Successful", Toast.LENGTH_SHORT);
            if (result.equals("HOD")) {
                toast.show();
                context.startActivity(new Intent(context, HodActivity.class));
            }
            if (result.equals("Admin")){
                toast.show();
                String type1;
                type1 = "backgroundAdmin";
                BackgroundWorker backgroundWorker = new BackgroundWorker(context);
                backgroundWorker.execute(type1);
            }
            if (result.equals("Lab Incharge")){
                toast.show();
                Intent intent = new Intent(context, LabInchargeActivity.class);
                intent.putExtra("username",user_name);
                intent.putExtra("password",password);
                context.startActivity(intent);
            }
        }
        if(type=="assign_post"){
           Toast toast = Toast.makeText(context,result,Toast.LENGTH_SHORT);
           toast.show();
        }
        if(type=="backgroundAdmin"){
            Intent intent = new Intent(context,AdminActivity.class);
            intent.putExtra("json_data",result);
            context.startActivity(intent);
        }
        if(type=="remove"){
            Toast toast = Toast.makeText(context,result,Toast.LENGTH_SHORT);
            toast.show();
        }
        if(type=="register"){
            Toast toast = Toast.makeText(context,result,Toast.LENGTH_SHORT);
            toast.show();
        }
        if(type=="updatePrblm"){
            Toast toast = Toast.makeText(context,result,Toast.LENGTH_LONG);
            toast.show();
        }
        if(type=="addPrblm"){
            Toast toast = Toast.makeText(context,result,Toast.LENGTH_LONG);
            toast.show();
        }
        if(type=="incharge_view"){
            Intent intent = new Intent(context,LabInchargeView.class);
            intent.putExtra("json_data",result);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}