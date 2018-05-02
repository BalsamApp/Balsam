package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.net.URLEncoder;


public class get_data extends AppCompatActivity {
    TextView v;
    EditText Dis ,allerg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        v=(TextView)findViewById(R.id.idresult);
        //get patiant ID
        v.setText(getIntent().getExtras().getString("userID"));
        Dis=(EditText)findViewById(R.id.textView2);
        allerg=(EditText)findViewById(R.id.editTextAllergy);
        String username=v.getText().toString();
        String type="login";
        patientIDBackgroundWorker2 patientIDBackgroundWorker2=new  patientIDBackgroundWorker2(this);
        patientIDBackgroundWorker2.execute(type,username);
    }

    public void getJSON(View view){
        new BackgroundTaskGet().execute();
        new BackgroundTaskGet2().execute();
    }

    class BackgroundTaskGet extends AsyncTask<Void, Void, String> {
        TextView b=(TextView)findViewById(R.id.idresult);
        String username=b.getText().toString();
        //jason_string for result
        String jason_string;
        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="https://balsam.000webhostapp.com/get_dataOfdis.php";
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
                String post_dta= URLEncoder.encode("user_name" ,"UTF-8")+"="+URLEncoder.encode(""+username,"UTF-8");
                bufferedWriter.write(post_dta);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                //multiline of data
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
            // clean up reasult from \ ,"
            String s = result.replace('\"', ' ');
            String s2 = s.replace('\\', ' ');
            TextView textView=(TextView)findViewById(R.id.textView2);
            textView.setText(s2);
        }
    }
    //-----------------------------------------------------------------------------
    class BackgroundTaskGet2 extends AsyncTask<Void, Void, String> {
        TextView b=(TextView)findViewById(R.id.idresult);
        String username=b.getText().toString();
        String jason_string;
        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="https://balsam.000webhostapp.com/get_dataOfallerg.php";
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
                String post_dta= URLEncoder.encode("user_name" ,"UTF-8")+"="+URLEncoder.encode(""+username,"UTF-8");
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
            String s = result.replace('\"', ' ');
            String s2 = s.replace('\\', ' ');
            TextView textView=(TextView)findViewById(R.id.editTextAllergy);
            textView.setText(s2);
        }
    }
    //---------------------------------------------------------------------------
    public void onUpdate(View view){

        String disSt=Dis.getText().toString();
        String id=v.getText().toString();
        String allergy=allerg.getText().toString();
        String type="login";

        UpdateDisSta updateDisSta=new UpdateDisSta(this);
        updateDisSta.execute(type,id,disSt);

        UpdateAllery updateAllery=new UpdateAllery(this);
        updateAllery.execute(type,id,allergy);
    }


    public static String updateDiseases(int iddpatient, String Diseases){
        return "Diseases Updated Successfully";

    }
    public static String updateAllergy(int iddpatient, String Allergy){
        return "Allergy Updated Successfully";
    }


}
