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
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.ad.ad.patient_login.id_id;

//this class is for Db of med
public class med_med_activity extends AppCompatActivity{

    public static ArrayList<String> med_names;
    public static ArrayList<String> med_durationmeds;
    public static ArrayList<String> med_timetakens;
    public static ArrayList<String> med_rangehs;
    public static ArrayList<String> med_notess;
    public static ArrayList<String> ffl;
    public static ArrayList<String> med_active1;


    public static Context context;
    BoomMenuButton bmb;
    String id;
    String prescriptionid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_med);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        prescriptionid = intent.getExtras().getString("prescriptionid");
        Log.d("prescriptionid", ""+prescriptionid);
        Log.d("id", ""+id);

        toolBar(getIntent().getExtras().getString("id"));

        getJSON();

    }

    private void toolBar(String name) {
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
            json_url="https://balsam.000webhostapp.com/p_med_view.php";
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


            Log.d("DOCTkkkkkk",result);

            if(!result.equals(null)) {

                String sd = result.replace("\"\"", "=");//تبديل كل " بفراغ
                String s = sd.replace("\"", " ");//تبديل كل " بفراغ

            s = s.trim();
                String[] ss = s.split("=");//تحويل الكلام الى ارراي

                ArrayList<String> med_name = new ArrayList<String>();
                ArrayList<String> med_duration = new ArrayList<String>();
                ArrayList<String> med_timetaken = new ArrayList<String>();
                ArrayList<String> med_rangeh = new ArrayList<String>();
                ArrayList<String> med_notes = new ArrayList<String>();
                ArrayList<String> ff = new ArrayList<String>();
                ArrayList<String> med_active = new ArrayList<String>();
                int f1=0,a1 = 1,b1 = 2, c1 = 3,d1 = 4, e1 = 5,g1=6;
                for (int i = 0; i < ss.length / 7; i++) {
                    ff.add(ss[f1]);
                    med_name.add(ss[a1]);
                    med_duration.add(ss[b1]);
                    med_timetaken.add(ss[c1]);
                    med_rangeh.add(ss[d1]);
                    med_notes.add(ss[e1]);
                    med_active.add(ss[g1]);
                    f1=f1+7;
                    a1 = a1 +7 ;//زائد على حسب الكولم
                    b1 = b1 +7;//زائد على حسب الكولم
                    c1 = c1+7;//زائد على حسب الكولم
                    d1 =d1 +7;//زائد على حسب الكولم
                    e1 = e1+7;//زائد على حسب الكولم
                    g1=g1+7;
                }
                med_names = med_name;
                med_durationmeds=med_duration;
                med_timetakens=med_timetaken;
                med_rangehs=med_rangeh;
                med_notess=med_notes;
                ffl=ff;
                med_active1=med_active;

                for(int i=0;i<med_name.size();i++) {
                    Log.d("DOCT", med_name.get(i)+" .. "+med_duration.get(i)+" .. "+med_timetaken.get(i)+" .. "+
                            med_rangeh.get(i)+" .. "+med_notes.get(i));
                }


                RecyclerView myrv = (RecyclerView) findViewById(R.id.med_recyclerview_id);
                med_med_recycleview myAdapter = new med_med_recycleview(prescriptionid,context, med_names, med_durationmeds, med_timetakens,
                        med_rangehs, med_notess,ffl,med_active1);


                myrv.setLayoutManager(new GridLayoutManager(context, 1));
                myrv.setAdapter(myAdapter);


            }

        }
    }

    public static String actived_med(int patientidd, int doctoridd,int prescid, String medname, int duration, int timesPerDay, int after,String notes, String active){
        if(active=="active")
            return "active";
        else
            return "not active";
    }
}



