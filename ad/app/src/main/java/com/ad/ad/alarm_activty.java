package com.ad.ad;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.Time;

import static com.ad.ad.patient_login.id_id;


/**
 * A simple {@link Fragment} subclass.
 */
public class alarm_activty extends Activity {

    public static Context context;
    private List<alarm_object> newMedList ;
    private List<alarm_object> allMedList ;
    public static AlarmManager alarmManager;
    private alarmListAdapter adapter;
    private ListView alarmListView;



    public alarm_activty() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.alarm);

        newMedList = MainActivity.alarmList;
        MainActivity.alarmList= new ArrayList<alarm_object>();

        if(loadListFromFile(id_id+".txt")!=null)
        allMedList = loadListFromFile(id_id+".txt");
        if(allMedList.size()>0){
           // Log.e("list readed from file","done ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        }




        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if(newMedList.size()>0){
            for(int i = 0; i<newMedList.size(); i++){
                alarm_object t = newMedList.get(i);
                create_alarm(t);
                allMedList.add(t);
                Log.e("there is o from DB","iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            }



                writeListToFile(allMedList, id_id+".txt");

        }



        if (allMedList.size()>0) {

            alarmListView = (ListView) findViewById(R.id.alarmListView);
            adapter = new alarmListAdapter(getApplicationContext(), allMedList);
            alarmListView.setAdapter(adapter);


        } else{
            TextView no_alarm =  (TextView) findViewById(R.id.no_list);
            no_alarm.setVisibility(View.VISIBLE);
            no_alarm.setText("There is no Alarm today");
        }


    }


    public void create_alarm(alarm_object o){

        Date d = o.getStartDate();


        Intent myIntent = new Intent(this, Alarm_receiver.class);
        myIntent.putExtra("med_name",o.getName());
        myIntent.putExtra("noti_id",o.getId());

        //myIntent.putExtra("med_Time",alarmList.get(position).getTime().getHours()+":"+alarmList.get(position).getTime().getMinutes());

        PendingIntent pending_intent = PendingIntent.getBroadcast(this.getApplicationContext(), o.getId(), myIntent, 0);

        Long repeatInMill =  new Long((24/o.getTime_taken())*60*60*1000);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, d.getTime(),repeatInMill, pending_intent);

    }



    private List<alarm_object> loadListFromFile( String fileName ) {

        List<alarm_object>  list = new ArrayList<alarm_object>();
        try {
            //File file = getFileStreamPath(fileName);
           // file.delete();
            FileInputStream fis = openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.e("inside file load","llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
            list = (List<alarm_object>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            //Log.e("inside file load catch","llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");

        }
        return list;
    }

    private void writeListToFile(List <alarm_object> list, String fileName){

        File myfile = getFileStreamPath(fileName);


        try {
            if(myfile.exists() || myfile.createNewFile()){
               // Log.e("inside file write","wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");

                FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list);
                oos.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Log.e("inside file write catch","wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");

        }

    }


    public static String modifyAlerts(int idd,int prescid, String medname, int duration, int timesPerDay, int after, String oclock){
        return "Alerts modified successfully";
    }
    public static String dep_modifyAlerts(int patientidd, int depidd,int prescid, String medname, int duration, int timesPerDay, int after, String oclock){
        return "Alerts modified successfully";
    }


}





