package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HodView extends AppCompatActivity {
    ArrayList<String> pc_no_array = new ArrayList<String>();
    ArrayList<String> lab_no_array = new ArrayList<String>();
    ArrayList<String> status_array = new ArrayList<String>();
    ArrayList<String> date_array = new ArrayList<String>();
    ArrayList<String> desc_array = new ArrayList<String>();
    String json_string;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView list;
    RowAdapter rowAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        json_string = intent.getStringExtra("json_data");
        //Toast toast = Toast.makeText(HodView.this,json_string,Toast.LENGTH_LONG);
        //toast.show();
        setContentView(R.layout.activity_hod_view);
        list = findViewById(R.id.listview);

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("problems_data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                pc_no_array.add("pc_no:" +  jo.getString("pc_no"));
                lab_no_array.add("lab_no:" + jo.getString("lab_no"));
                status_array.add("status:" + jo.getString("status"));
                date_array.add("date:" + jo.getString("date"));
                desc_array.add("desc:" + jo.getString("description"));
                //Singlerow singlerow = new Singlerow(pc_no, lab_no, status, desc, date);
                //rowAdapter.add(singlerow);
            }
            rowAdapter = new RowAdapter(HodView.this, pc_no_array, lab_no_array, status_array, date_array, desc_array);
            list.setAdapter(rowAdapter);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }



    }

}

