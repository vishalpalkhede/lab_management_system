package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LabInchargeView extends AppCompatActivity {

    String json_string;
    ArrayList<String> pc_no_array = new ArrayList<String>();
    ArrayList<String> lab_no_array = new ArrayList<String>();
    ArrayList<String> status_array = new ArrayList<String>();
    ArrayList<String> date_array = new ArrayList<String>();
    ArrayList<String> desc_array = new ArrayList<String>();
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView listView;
    RowAdapter rowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_view);
        final Intent intent = getIntent();
        listView = findViewById(R.id.listview);
        json_string = intent.getStringExtra("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("incharge_view");

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
            rowAdapter = new RowAdapter(LabInchargeView.this, pc_no_array, lab_no_array, status_array, date_array, desc_array);
            listView.setAdapter(rowAdapter);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pc_no = pc_no_array.get(i).toString();
                String lab_no = lab_no_array.get(i).toString();
                String status = status_array.get(i).toString();
                String date = date_array.get(i).toString();
                String desc = desc_array.get(i).toString();

                Intent intent1 = new Intent(LabInchargeView.this, RemoveProblem.class);
                intent1.putExtra("pc_no",pc_no);
                intent1.putExtra("lab_no",lab_no);
                intent1.putExtra("status",status);
                intent1.putExtra("date",date);
                intent1.putExtra("desc",desc);
                startActivity(intent1);


            }
        });


    }


}
