package com.example.system_project;

public class NotifucationClass {
    private  String  userid;

    private  String  text;

    public NotifucationClass(String userid, String text) {
        this.userid = userid;
        this.text = text;

    }
private  NotifucationClass(){}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
