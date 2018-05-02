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
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

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

public class p_med_activity_recycleview extends AppCompatActivity {

    public static ArrayList<String> presc_idss;
    public static ArrayList<String> pat_idss;
    public static ArrayList<String> doct_names;
    public static ArrayList<String> datepres;
    public static ArrayList<String> durationpres;



    public static Context context;
    //BoomMenuButton bmb;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_med);
        //get data from previous page
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        Log.d("id", "" + id);

        toolBar();
        getJSON();
    }

    private void toolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Prescription list");
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
        @Override
        protected void onPreExecute() {
            json_url="https://balsam.000webhostapp.com/p_prescription.php";
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
                String post_dta= URLEncoder.encode("ID" ,"UTF-8")+"="+URLEncoder.encode(""+id,"UTF-8");
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
            ArrayList<String> id_pres = new ArrayList<String>();
            ArrayList<String> id_pat = new ArrayList<String>();
            ArrayList<String> doct_name = new ArrayList<String>();
            ArrayList<String> dates = new ArrayList<String>();
            ArrayList<String> durations = new ArrayList<String>();

            //Log.d("DOCT",result);

            if(!result.equals(null)) {
                String sd = result.replace("\"\"", "=");// replace all "" -> the blank
                String s = sd.replace("\"", " ");// replace all " -> the blank

                s = s.trim();
                String[] ss = s.split("=");// Convert sentences (words) to Array

                //Extract the data from the server and place it in Array
                int a = 0, b = 1, c = 2, d = 3, e = 4;
                for (int i = 0; i < ss.length / 5; i++) {//number of columns
                    //stor in array
                    id_pres.add(ss[a]);
                    id_pat.add(ss[b]);
                    doct_name.add(ss[c]);
                    dates.add(ss[d]);
                    durations.add(ss[e]);
                    //plus by number of columns
                    a = a + 5;
                    b = b + 5;
                    c = c + 5;
                    d = d + 5;
                    e = e + 5;
                }
                //Store all the data in the Array
                presc_idss = id_pres;
                pat_idss=id_pat;
                doct_names=doct_name;
                datepres=dates;
                durationpres=durations;
                //Print all the data in the console
                for(int i=0;i<id_pres.size();i++) {
                    Log.d("DOCT", id_pres.get(i)+" .. "+id_pat.get(i)+" .. "+doct_name.get(i)+" .. "+dates.get(i)+" .. "+durations.get(i));
                }
                RecyclerView myrv = (RecyclerView) findViewById(R.id.presc_recyclerview_id);
                //go to p_med_presc_recycleview class
                p_med_presc_recycleview myAdapter = new p_med_presc_recycleview(id,context, presc_idss, pat_idss,
                                                                                doct_names, datepres, durationpres);

                myrv.setLayoutManager(new GridLayoutManager(context, 1));//number of columns
                myrv.setAdapter(myAdapter);
            }
            //Appear if there is no Prescriptions
            if(result.equals("")){
                TextView tt=(TextView) findViewById(R.id.no_pres);
                tt.setText("no Prescriptions");
            }
        }
    }
}