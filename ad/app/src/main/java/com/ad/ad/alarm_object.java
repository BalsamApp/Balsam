package com.ad.ad;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.sql.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class alarm_object implements Serializable {







    private int id;
    private  String prescID;
    private String name;
    private Date startDate;
    private Date endtDate;
    private Time time;
    private int time_taken;
    private int duration;
    private String note;
    private boolean state;

    //Constructor

    public alarm_object(int id,String prescID, String name,int time_taken,int duration , String note) throws ParseException {
        this.id = id;
        this.prescID = prescID;
        this.name = name;
        this.startDate = Calendar.getInstance().getTime();
        this.time = new Time (startDate.getTime());
        startDate.setHours(time.getHours());
        startDate.setMinutes(time.getMinutes());
        startDate.setSeconds(time.getSeconds());
        this.time_taken = time_taken;
        this.note = note;
        this.state= true;
        this.duration = duration;
        setEndDate();


    }

    //Setter, getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrescID() {
        return prescID;
    }

    public void setPrescID(String prescID) {
        this.prescID = prescID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
        startDate.setHours(time.getHours());
        startDate.setMinutes(time.getMinutes());
        startDate.setSeconds(time.getSeconds());
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    public int getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(int time_taken) {
        this.time_taken = time_taken;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }



    public Date getEndDate() {
        return this.endtDate;
    }

    public void setEndDate()
    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, duration); //minus number would decrement the days
        endtDate= cal.getTime();

    }








}
