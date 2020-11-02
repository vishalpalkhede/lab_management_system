package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RowAdapter extends BaseAdapter {

    //ArrayList<singlerow> list;
    private Activity activity;

    private static ArrayList pc_no_array,lab_no_array,status_array,date_array,desc_array;

    private static LayoutInflater inflater = null;
   // List list = new ArrayList();

    public RowAdapter(Activity a, ArrayList pc_no_array, ArrayList lab_no_array,ArrayList status_array,ArrayList date_array,ArrayList desc_array) {
        activity = a;
        this.pc_no_array = pc_no_array;
        this.lab_no_array = lab_no_array;
        RowAdapter.status_array = status_array;
        this.date_array = date_array;
        this.desc_array = desc_array;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return pc_no_array.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {



        View row;
        row = view;
        //ContactHolder contactHolder;
        if(row == null)

            row = inflater.inflate(R.layout.single_row, viewGroup, false);

            TextView tvPc_no = (TextView) row.findViewById(R.id.tvPcno);
            TextView tvLab_no = (TextView) row.findViewById(R.id.tvLabno1);
            TextView tvStatus = (TextView) row.findViewById(R.id.tvStatus);
            TextView tvDate = (TextView) row.findViewById(R.id.tvDate);
            TextView tvDesc = (TextView) row.findViewById(R.id.tvDesc);

        String pc_no = pc_no_array.get(i).toString();
        tvPc_no.setText(pc_no);
        String lab_no = lab_no_array.get(i).toString();
        tvLab_no.setText(lab_no);
        String status = status_array.get(i).toString();
        tvStatus.setText(status);
        String date = date_array.get(i).toString();
        tvDate.setText(date);
        String desc = desc_array.get(i).toString();
        tvDesc.setText(desc);


        return row;
    }

    //static class ContactHolder{
      //  TextView tvPc_no;
        //TextView tvLab_no;
       // TextView tvStatus;
       // TextView tvDate ;
        //TextView tvDesc;

    //}
}
