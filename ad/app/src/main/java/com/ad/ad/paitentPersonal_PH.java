package com.ad.ad;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
public class paitentPersonal_PH extends AppCompatActivity {
String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitent_personal__ph);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            id = extras.getString("id");
        }

        getJSON();

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
            json_url = "https://balsam.000webhostapp.com/dep_info.php";
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
                String post_dta = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode("" +id, "UTF-8");
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

        @Override
        protected void onPostExecute(String result) {
            String s = result.replace('\"', ' ');//تبديل كل " بفراغ

            s = s.trim();
            String[] ss = s.split("  ");//تحويل الكلام الى ارراي

            TextView tt=(TextView) findViewById(R.id.patientName);
            tt.setText(ss[2]);
            tt=(TextView) findViewById(R.id.birthday);
            tt.setText(ss[3]);
            tt=(TextView) findViewById(R.id.gender);
            tt.setText(ss[4]);
            tt=(TextView) findViewById(R.id.mobile);
            tt.setText(ss[5]);
            tt=(TextView) findViewById(R.id.status);
            tt.setText(ss[6]);
            tt=(TextView) findViewById(R.id.address);
            tt.setText(ss[7]);
            tt=(TextView) findViewById(R.id.bloodType);
            tt.setText(ss[8]);
            tt=(TextView) findViewById(R.id.height);
            tt.setText(ss[9]);
            tt=(TextView) findViewById(R.id.weight);
            tt.setText(ss[10]);
            tt=(TextView) findViewById(R.id.allergies);
            tt.setText(ss[12]);
            tt=(TextView) findViewById(R.id.diseases);
            tt.setText(ss[13]);


        }
    }
}

