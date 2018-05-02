package com.ad.ad;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
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

/**
 * Created by lenovo on 04/04/18.
 */

public class CreatPrescBackgraoundWork extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    CreatPrescBackgraoundWork(Context ctx){
        context=ctx;
    }

public static String pre_id;
    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url= "http://balsam.000webhostapp.com/CreateNwePresc.php";
        try {
           /* String id=params[1];*/
            String patientID=params[1];
            String docID=params[2];
            String date=params[3];
            String duration=params[4];

            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_dta=URLEncoder.encode("patientID" ,"UTF-8")+"="+URLEncoder.encode(patientID ,"UTF-8")+"&"
                    +URLEncoder.encode("docID" ,"UTF-8")+"="+URLEncoder.encode(docID ,"UTF-8")+"&"
                    +URLEncoder.encode("date" ,"UTF-8")+"="+URLEncoder.encode(date ,"UTF-8")+"&"
                    +URLEncoder.encode("duration" ,"UTF-8")+"="+URLEncoder.encode(duration ,"UTF-8");
            bufferedWriter.write(post_dta);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="this:";
            String line;
            while ((line = bufferedReader.readLine())!=null){
                result +=line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog =new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Presc Status");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        String s = result.replace('\"', ' ');
        s = s.trim();
        String[] ss = s.split("=");
        String name=ss[0];
        if(name.equals("Error") ){
            alertDialog.setMessage(result);
            alertDialog.show();

        }else{
            String id=ss[1];
            pre_id=id;

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
