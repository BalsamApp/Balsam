package com.ad.ad;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ad.ad.R;
import com.ad.ad.alarm_object;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static com.ad.ad.patient_login.id_id;

public class alarmListAdapter extends BaseAdapter {


    private Context Context;
    private List<alarm_object> alarm_objectList;
    AlarmManager alarmManager;

    //Constructor

    public alarmListAdapter(Context Context, List<alarm_object> alarm_objectList) {

        this.Context = Context;
        this.alarm_objectList = alarm_objectList;
    }

    @Override
    public int getCount() {
        return alarm_objectList.size();
    }

    @Override
    public Object getItem(int position) {
        return alarm_objectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        final View v = View.inflate(Context, R.layout.alarm_item, null);

        TextView med_name = (TextView)v.findViewById(R.id.alarm_med_name);
        TextView alarmTime = (TextView)v.findViewById(R.id.time);

        //Set text for TextView
        med_name.setText(alarm_objectList.get(position).getName());
        alarmTime.setText( alarm_objectList.get(position).getTime().getHours()+":"+ alarm_objectList.get(position).getTime().getMinutes());


        final Switch alarm_switch = (Switch) v.findViewById(R.id.alarm_switch);
        alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alarm_on(position);
                } else {
                    alarm_off(position);
                }
            }
        });


        Button edit_time = (Button) v.findViewById(R.id.edit_btn);
        edit_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Time oldTime = (Time) alarm_objectList.get(position).getTime();
                int ohour = oldTime.getHours();
                int ominute = oldTime.getMinutes();
                TimePickerDialog timePickerDialog = new TimePickerDialog(alarm_activty.context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Time newTime = new Time(selectedHour, selectedMinute,0);//seconds by default set to zero
                        alarm_objectList.get(position).setTime(newTime);
                        TextView alarmTime = (TextView)v.findViewById(R.id.time);
                        alarmTime.setText( alarm_objectList.get(position).getTime().getHours()+":"+ alarm_objectList.get(position).getTime().getMinutes());
                        Log.d("object id ", ( alarm_objectList.get(position).getId())+" ");
                        Log.d("onClick id ", position+" ");
                        writeListToFile(alarm_objectList,id_id+".txt");
                        alarm_on(position);
                    }
                }, ohour, ominute, false);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();


            }
        });

        return v;
    }


    public  void alarm_off(int position){

        Date d = alarm_objectList.get(position).getStartDate();
        Intent myIntent = new Intent(alarm_activty.context, Alarm_receiver.class);
        myIntent.putExtra("med_name",alarm_objectList.get(position).getName());
        PendingIntent pending_intent = PendingIntent.getBroadcast(alarm_activty.context.getApplicationContext(), alarm_objectList.get(position).getId(), myIntent, 0);
        alarm_activty.alarmManager.cancel(pending_intent);
        Toast.makeText(Context, "Alarm off", Toast.LENGTH_LONG).show();

    }

    public void alarm_on(int position){
        Date d = alarm_objectList.get(position).getStartDate();
        Intent myIntent = new Intent(alarm_activty.context, Alarm_receiver.class);
        myIntent.putExtra("med_name",alarm_objectList.get(position).getName());
        myIntent.putExtra("noti_id", alarm_objectList.get(position).getId());
        PendingIntent pending_intent = PendingIntent.getBroadcast(alarm_activty.context.getApplicationContext(), alarm_objectList.get(position).getId(), myIntent, 0);
        Long repeatInMill =  new Long((24/alarm_objectList.get(position).getTime_taken())*60*60*1000);
        alarm_activty.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, d.getTime(),repeatInMill,pending_intent);
        Toast.makeText(Context, "Alarm set to  " +alarm_objectList.get(position).getTime().getHours()+":"+ alarm_objectList.get(position).getTime().getMinutes(),Toast.LENGTH_LONG).show();
    }


    private void writeListToFile(List <alarm_object> list, String fileName){

        File myfile = alarm_activty.context.getFileStreamPath(fileName);
        myfile.delete();

        try {
            if(myfile.exists() || myfile.createNewFile()){
                FileOutputStream fos = alarm_activty.context.openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list);
                oos.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}







