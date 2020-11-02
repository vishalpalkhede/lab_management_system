package com.example.myapplication;

public class Singlerow{

    private String pc_no;
    private String lab_no;
    private String status;
    private String desc;
    private String date;


    public Singlerow (String pc_no, String lab_no, String status, String desc, String date){
        this.setPc_no(pc_no);
        this.setLab_no(lab_no);
        this.setStatus(status);
        this.setDesc(desc);
        this.setDate(date);

    }

    public void setPc_no(String pc_no) {
        this.pc_no = pc_no;
    }

    public void setLab_no(String lab_no) {
        this.lab_no = lab_no;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPc_no() {
        return pc_no;
    }

    public String getLab_no() {
        return lab_no;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }
}





