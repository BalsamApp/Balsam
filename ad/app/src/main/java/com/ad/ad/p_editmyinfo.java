package com.ad.ad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by malak on 24/03/18.
 */

public class p_editmyinfo extends AppCompatActivity {
    String id,pname,pdate,pgender,pphone,pstatus,padd, ptall,pweight;
    EditText tide, tname, tdateyear, tphone, tadd, ttall,tweight;

    String side, sname, sdate, sgender, sphone, sstatus, sadd, stall,sweight;
    RadioGroup status,gender;
    Spinner dateday,datemonth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_edit_myinfo);

        //get data from previous page
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        pname = intent.getExtras().getString("name");
        pdate = intent.getExtras().getString("date");
        pgender = intent.getExtras().getString("gender");
        pphone = intent.getExtras().getString("phone");
        pstatus = intent.getExtras().getString("status");
        padd = intent.getExtras().getString("add");
        ptall=intent.getExtras().getString("tall");
        pweight=intent.getExtras().getString("weight");

        //assign each EditText
        tide = (EditText) findViewById(R.id.etidd1);
        tname = (EditText) findViewById(R.id.etname1);
        dateday = (Spinner) findViewById(R.id.etdateday1);
        datemonth = (Spinner) findViewById(R.id.etdatemonth1);
        tdateyear = (EditText) findViewById(R.id.etdateyear1);
        gender=(RadioGroup)findViewById(R.id.etgender1);
        RadioButton female =(RadioButton)findViewById(R.id.female);
        RadioButton male =(RadioButton)findViewById(R.id.male);
        tphone = (EditText) findViewById(R.id.etphone1);
        status = (RadioGroup) findViewById(R.id.etstatus1);
        RadioButton single =(RadioButton)findViewById(R.id.single);
        RadioButton married =(RadioButton)findViewById(R.id.married);
        tadd = (EditText) findViewById(R.id.etaddr1);
        ttall=(EditText) findViewById(R.id.ettall1);
        tweight=(EditText) findViewById(R.id.etweight1);

        //Set each value (the value from the previous page) in the EditText
        tide.setText(id);
        tname.setText(pname);
        if(pgender.equalsIgnoreCase("Female")) {
            female.setChecked(true);
        }
        else if(pgender.equalsIgnoreCase("Male")){
            male.setChecked(true);
        }


        String[] datedmy=pdate.split("-");
        tdateyear.setText(datedmy[0]);
        for (int i = 0; i < datemonth.getCount(); i++) {
            if (datemonth.getItemAtPosition(i).equals(datedmy[1])) {
                datemonth.setSelection(i);
                break;
            }
        }
        for (int i = 0; i < dateday.getCount(); i++) {
            if (dateday.getItemAtPosition(i).equals(datedmy[2])) {
                dateday.setSelection(i);
                break;
            }
        }

        if(pstatus.equalsIgnoreCase("Single")) {
            single.setChecked(true);
        }
        else if(pstatus.equalsIgnoreCase("Married")){
            married.setChecked(true);
        }
        tphone.setText(pphone);
        tadd.setText(padd);
        ttall.setText(ptall);
        tweight.setText(pweight);

        //It will be printed in the console
        Log.d("values", "" + tide+", "+tname+","+tphone+",  "+tadd+",  "+pgender+",  "+pstatus+
                ",  "+dateday+",  "+datemonth+",  "+tdateyear+",  "+ttall+",  "+tweight );
        Log.d("id", "" + id);
        //after click on the button, it will save changes and move to in patient information page(p_myinfo_activity.java)
        Button buttoneditmyinfo = (Button) findViewById(R.id.butt_editinfo1);

        buttoneditmyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivitmyinfo();
            }
        });

    }

    private void launchActivitmyinfo() {
        Intent intent = new Intent(this, p_myinfo_activity.class);
        intent.putExtra("id", getIntent().getExtras().getString("id"));

            //Convert the values in the EditText to a string and store them in a string variable and then send them to the server
            side=tide.getText().toString();
            sname=tname.getText().toString();

            String dday=dateday.getSelectedItem().toString();
            String mmonth=datemonth.getSelectedItem().toString();
            String yyear=tdateyear.getText().toString();
            sdate=yyear+"-"+mmonth+"-"+dday;

            int selectedId = gender .getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            sgender=radioButton.getText().toString();

            sphone=tphone.getText().toString();

            selectedId = status .getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            sstatus=radioButton.getText().toString();

            sadd=tadd.getText().toString();
            stall=ttall.getText().toString();
            sweight=tweight.getText().toString();

            Log.d("rrrrrr1111", "" + side+", "+sname+", "+sdate+", "+sgender+", "+
                sphone+", "+sstatus+", "+sadd+", "+stall+", "+sweight);


            getJSONview();
            //  BackgroundWorkerUpdate backgroundWorkerd=new BackgroundWorkerUpdate(this);
            //  backgroundWorkerd.execute(pid,ppassword,ppname,date,gander,ppphone,fstatus,add);
            startActivity(intent);

    }


    /////////////////////////////////////////////


    class BackgroundTaskGet extends AsyncTask<Void, Void, String> {
        String jason_string;
        String json_url = "https://balsam.000webhostapp.com/p_update_info.php";
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(p_editmyinfo.this, "Loading Data", null, true, true);

        }

        @Override
        protected void onPostExecute(String httpResponseMsg) {
            super.onPostExecute(httpResponseMsg);
            progressDialog.dismiss();
            Toast.makeText(p_editmyinfo.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_dta = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode("" + id, "UTF-8");
                bufferedWriter.write(post_dta);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jason_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jason_string);

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void getJSONview(){ new BackgroundTaskGetview().execute();
}

    class BackgroundTaskGetview extends AsyncTask<Void, Void, String> {
        String jason_string;
        String json_url;
        ArrayList<String> name = new ArrayList<String>();

        @Override
        protected void onPreExecute() {
            json_url = "https://balsam.000webhostapp.com/p_update_info.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_dta = URLEncoder.encode("sid", "UTF-8") + "=" + URLEncoder.encode("" + side, "UTF-8")+"&"
                        +URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode("" + sname, "UTF-8")+"&"
                        +URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode("" + sdate, "UTF-8")+"&"
                        +URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode("" + sgender, "UTF-8")+"&"
                        +URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode("" + sphone, "UTF-8")+"&"
                        +URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode("" + sstatus, "UTF-8")+"&"
                        +URLEncoder.encode("add", "UTF-8") + "=" + URLEncoder.encode("" + sadd, "UTF-8")+"&"
                        +URLEncoder.encode("tall", "UTF-8") + "=" + URLEncoder.encode("" + stall, "UTF-8")+"&"
                        +URLEncoder.encode("weight", "UTF-8") + "=" + URLEncoder.encode("" + sweight, "UTF-8");
                bufferedWriter.write(post_dta);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jason_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jason_string);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public static String editinformation_patient(int idd, String name, int phone,String gender,String brithDay,
                                       String status, String address,int tall, int weight){
        return "Updated Successfully";
    }
}
