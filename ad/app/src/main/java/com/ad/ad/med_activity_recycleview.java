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
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.GridLayoutManager;

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
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.ad.ad.patient_login.id_id;

///this class is for DB of Prescription view
public class med_activity_recycleview extends AppCompatActivity {



    private ExpandableListView listView;//==simpleExpandableListView
    // private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;


    public static ArrayList<String> presc_idss;
    public static ArrayList<String> pat_idss;
    public static ArrayList<String> doct_idss;
    public static ArrayList<String> datepres;
    public static ArrayList<String> durationpres;


    public static Context context;
    static Intent f;
    BoomMenuButton bmb;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        Log.d("id", ""+id);

        toolBar(getIntent().getExtras().getString("id"));


        getJSON();



    }

    private void toolBar(String name) {
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
        ArrayList<String> ids=new ArrayList<String>();
        ArrayList<String> name=new ArrayList<String>();
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


            Log.d("DOCT",result);

            if(!result.equals(null)) {

                String sd = result.replace("\"\"", "=");//تبديل كل " بفراغ
                String s = sd.replace("\"", " ");//تبديل كل " بفراغ

                s = s.trim();
                String[] ss = s.split("=");//تحويل الكلام الى ارراي

                ArrayList<String> id_pres = new ArrayList<String>();
                ArrayList<String> id_pat = new ArrayList<String>();
                ArrayList<String> id_doc = new ArrayList<String>();
                ArrayList<String> dates = new ArrayList<String>();
                ArrayList<String> durations = new ArrayList<String>();

                int a = 0;
                for (int i = 0; i < ss.length / 5; i++) {
                    id_pres.add(ss[a]);
                    a = a + 5;//زائد على حسب الكولم
                }
                Log.d("MMMMMMMMM", " MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
                int b = 1;
                for (int i = 0; i < ss.length / 5; i++) {
                    id_pat.add(ss[b]);
                    b =b +5;//زائد على حسب الكولم
                }
                int c = 2;
                for (int i = 0; i < ss.length / 5; i++) {
                    id_doc.add(ss[c]);
                    c = c+5;//زائد على حسب الكولم
                }
                int d = 3;
                for (int i = 0; i < ss.length / 5; i++) {
                    dates.add(ss[d]);
                    d =d +5;//زائد على حسب الكولم
                }
                int e = 4;
                for (int i = 0; i < ss.length / 5; i++) {
                    durations.add(ss[e]);
                    e = e+5;//زائد على حسب الكولم
                }                Log.d("MMMMMMMMM", " MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

                presc_idss = id_pres;
                pat_idss=id_pat;
                doct_idss=id_doc;
                datepres=dates;
                durationpres=durations;
for(int i=0;i<id_pres.size();i++) {
    Log.d("DOCT", id_pres.get(i)+" .. "+id_pat.get(i)+" .. "+id_doc.get(i)+" .. "+dates.get(i)+" .. "+durations.get(i));
}


                RecyclerView myrv = (RecyclerView) findViewById(R.id.presc_recyclerview_id);
                med_presc_recycleview myAdapter = new med_presc_recycleview(id,context, presc_idss, pat_idss, doct_idss, datepres, durationpres);

            myrv.setLayoutManager(new GridLayoutManager(context, 1));
                myrv.setAdapter(myAdapter);


            }
            if(result.equals("")){

                TextView tt=(TextView) findViewById(R.id.no_pres);
                tt.setText("no prescription");
            }
        }
    }
}