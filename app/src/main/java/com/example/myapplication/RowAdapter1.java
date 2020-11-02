package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RowAdapter1 extends BaseAdapter {

    private Activity activity1;
    private static ArrayList fullname_array,dept_array,lab_no_array1,post_array;

    private static LayoutInflater inflater = null;
    public RowAdapter1(Activity a, ArrayList fullname_array, ArrayList dept_array, ArrayList lab_no_array1, ArrayList post_array){
        activity1 = a;
        this.fullname_array = fullname_array;
        this.dept_array = dept_array;
        RowAdapter1.lab_no_array1 = lab_no_array1;
        this.post_array = post_array;

        inflater = (LayoutInflater) activity1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return fullname_array.size();
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
        if(row == null)
            row = inflater.inflate(R.layout.post_single_row, viewGroup, false);

        TextView tvFullname = (TextView) row.findViewById(R.id.tvFullname);
        TextView tvDept = (TextView) row.findViewById(R.id.tvDept);
        TextView tvPost = (TextView) row.findViewById(R.id.tvPost);
        TextView tvLabno1 = (TextView) row.findViewById(R.id.tvLabno1);


        String tvfullname = fullname_array.get(i).toString();
        tvFullname.setText(tvfullname);
        String tvdept = dept_array.get(i).toString();
        tvDept.setText(tvdept);
        String tvlab_no = lab_no_array1.get(i).toString();
        tvLabno1.setText(tvlab_no);
        String tvpost = post_array.get(i).toString();
        tvPost.setText(tvpost);



        return row;
    }
}
