package com.ad.ad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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
import java.util.ArrayList;

import static com.ad.ad.patient_login.id_id;

/**
 * Created by malak on 05/04/18.
 */

public class p_med_med_activity extends AppCompatActivity {

    public static ArrayList<String> med_names;
    public static ArrayList<String> med_durationmeds;
    public static ArrayList<String> med_timetakens;
    public static ArrayList<String> med_rangehs;
    public static ArrayList<String> med_notess;
    public static ArrayList<String> med_phartNames;



    public static Context context;
    //BoomMenuButton bmb;
    String id;
    String prescriptionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_med_med);
        //get data from previous page
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        prescriptionid = intent.getExtras().getString("prescriptionid");
        Log.d("prescriptionid", ""+prescriptionid);
        Log.d("id", ""+id);

        toolBar();
        getJSON();
    }

    private void toolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Medicines list");
    }

    public void getJSON(){
        new BackgroundTaskGet().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    class BackgroundTaskGet extends AsyncTask<Void, Void, String> {
        String jason_string;
        String json_url;
        ArrayList<String> ids=new ArrayList<String>();
        ArrayList<String> name=new ArrayList<String>();
        @Override
        protected void onPreExecute() {
            json_url="https://balsam.000webhostapp.com/p_ph_med_view.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_dta= URLEncoder.encode("ID" ,"UTF-8")+"="+URLEncoder.encode(""+prescriptionid,"UTF-8");
                bufferedWriter.write(post_dta);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jason_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jason_string );
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){e.printStackTrace();}
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            ArrayList<String> med_name = new ArrayList<String>();
            ArrayList<String> med_duration = new ArrayList<String>();
            ArrayList<String> med_timetaken = new ArrayList<String>();
            ArrayList<String> med_rangeh = new ArrayList<String>();
            ArrayList<String> med_notes = new ArrayList<String>();
            ArrayList<String> med_phartName = new ArrayList<String>();

            Log.d("DOCTkkkkkk",result);

            if(!result.equals(null)) {
                String sd = result.replace("\"\"", "=");// replace all "" -> the blank
                String s = sd.replace("\"", " ");// replace all " -> the blank

                s = s.trim();
                String[] ss = s.split("=");// Convert sentences (words) to Array

                //Extract the data from the server and place it in Array
                int a1 = 0,b1 = 1, c1 = 2,d1 = 3, e1 = 4,f1 = 5;
                for (int i = 0; i < ss.length / 6; i++) {//number of columns
                    //stor in array
                    med_name.add(ss[a1]);
                    med_duration.add(ss[b1]);
                    med_timetaken.add(ss[c1]);
                    med_rangeh.add(ss[d1]);
                    med_notes.add(ss[e1]);
                    med_phartName.add(ss[f1]);
                    //plus by number of columns
                    a1 = a1 + 6;
                    b1 = b1 + 6;
                    c1 = c1 + 6;
                    d1 = d1 + 6;
                    e1 = e1 + 6;
                    f1 = f1 + 6;
                }
                //Store all the data in the Array
                med_names = med_name;
                med_durationmeds=med_duration;
                med_timetakens=med_timetaken;
                med_rangehs=med_rangeh;
                med_notess=med_notes;
                med_phartNames=med_phartName;

                //Print all the data in the console
                for(int i=0;i<med_name.size();i++) {
                    Log.d("DOCT", med_name.get(i)+" .. "+med_duration.get(i)+" .. "+med_timetaken.get(i)+" .. "+
                            med_rangeh.get(i)+" .. "+med_notes.get(i));
                }
                RecyclerView myrv = (RecyclerView) findViewById(R.id.med_recyclerview_id);
                //go to p_med_med_recycleview class
                p_med_med_recycleview myAdapter = new p_med_med_recycleview(prescriptionid,context, med_names, med_durationmeds, med_timetakens,
                        med_rangehs, med_notess, med_phartNames);
                myrv.setLayoutManager(new GridLayoutManager(context, 1));//number of columns
                myrv.setAdapter(myAdapter);
            }
        }
    }
}



