package com.ad.ad;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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

import static com.ad.ad.CreatPrescBackgraoundWork.pre_id;

/**
 * Created by lenovo on 05/04/18.
 */

public class background extends AsyncTask<String,Void,String> {
 String pres_id;

    Context context;
    AlertDialog alertDialog;
    background(Context ctx){
        context=ctx;
        pres_id=pre_id;
    }


    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url= "http://balsam.000webhostapp.com/AddMed2.php";
        try {
            String pID=params[1];
            String dID=params[2];
            String date=params[3];
            String duration=params[4];
            String medName=params[5];
            String medDur=params[6];
            String medTime=params[7];
            String afterEach=params[8];
            String medNote=params[9];



            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_dta= URLEncoder.encode("pID" ,"UTF-8")+"="+URLEncoder.encode(pID,"UTF-8")+"&"
                    +URLEncoder.encode("dID" ,"UTF-8")+"="+URLEncoder.encode(dID ,"UTF-8")+"&"
                    +URLEncoder.encode("date" ,"UTF-8")+"="+URLEncoder.encode(date ,"UTF-8")+"&"
                    +URLEncoder.encode("duration" ,"UTF-8")+"="+URLEncoder.encode(duration ,"UTF-8")+"&"
                    +URLEncoder.encode("pres_id" ,"UTF-8")+"="+URLEncoder.encode(pres_id ,"UTF-8")+"&"
                    +URLEncoder.encode("medName" ,"UTF-8")+"="+URLEncoder.encode(medName ,"UTF-8")+"&"
                    +URLEncoder.encode("medDur" ,"UTF-8")+"="+URLEncoder.encode(medDur ,"UTF-8")+"&"
                    +URLEncoder.encode("medTime" ,"UTF-8")+"="+URLEncoder.encode(medTime ,"UTF-8")+"&"
                    +URLEncoder.encode("afterEach" ,"UTF-8")+"="+URLEncoder.encode(afterEach ,"UTF-8")+"&"
                    +URLEncoder.encode("medNote" ,"UTF-8")+"="+URLEncoder.encode(medNote ,"UTF-8");
            bufferedWriter.write(post_dta);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result=" ";
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
        alertDialog.setTitle("Add Status");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
