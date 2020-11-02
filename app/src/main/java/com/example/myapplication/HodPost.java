package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HodPost extends AppCompatActivity {
    ArrayList<String> fullname_array = new ArrayList<String>();
    ArrayList<String> dept_array = new ArrayList<String>();
    ArrayList<String> post_array = new ArrayList<String>();
    ArrayList<String> lab_no_array= new ArrayList<String>();
    String json_string;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView listView;
    RowAdapter1 rowAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        json_string = intent.getStringExtra("json_data");
        Toast toast = Toast.makeText(HodPost.this,json_string,Toast.LENGTH_LONG);
        toast.show();
        setContentView(R.layout.activity_hod_view);
        listView = findViewById(R.id.listview);


        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("users");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                fullname_array.add("Fullname:" +  jo.getString("fullname"));
                lab_no_array.add("Lab_no:" + jo.getString("lab_no"));
                dept_array.add("Department:" + jo.getString("department"));
                post_array.add("Post:" + jo.getString("post"));
            }
            rowAdapter1 = new RowAdapter1(HodPost.this,fullname_array,dept_array,lab_no_array,post_array);
            listView.setAdapter(rowAdapter1);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String fullname = fullname_array.get(i).toString();
                    String lab_no = lab_no_array.get(i).toString();
                    String dept = dept_array.get(i).toString();
                    String post = post_array.get(i).toString();
                    Intent intent1 = new Intent(HodPost.this, AssignPost.class);
                    intent1.putExtra("fullname",fullname);
                    intent1.putExtra("lab_no",lab_no);
                    intent1.putExtra("dept",dept);
                    intent1.putExtra("post",post);
                    startActivity(intent1);


                }
            });
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }



    }

}