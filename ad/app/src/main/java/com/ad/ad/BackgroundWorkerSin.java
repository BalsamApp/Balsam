package com.ad.ad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import static android.content.Intent.getIntent;
import static com.ad.ad.patient_login.id_id;

/**
 * Created by lenovo on 25/02/18.
 */

public class BackgroundWorkerSin extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    BackgroundWorkerSin(Context ctx){
        context=ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url= "http://balsam.000webhostapp.com/sin.php";
        try
        {

            String sid=params[1];
            String password=params[2];
            String name=params[3];
            String date=params[4];
            String gander=params[5];
            String phone=params[6];
            String status=params[7];
            String add=params[8];
            String blood=params[9];
            String tall=params[10];
            String weight=params[11];
            String res_id=params[12];

            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_dta= URLEncoder.encode("sid" ,"UTF-8")+"="+URLEncoder.encode(sid ,"UTF-8")+"&"
                    +URLEncoder.encode("password" ,"UTF-8")+"="+URLEncoder.encode(password ,"UTF-8")+"&"
                    +URLEncoder.encode("name" ,"UTF-8")+"="+URLEncoder.encode(name ,"UTF-8")+"&"
                    +URLEncoder.encode("date" ,"UTF-8")+"="+URLEncoder.encode(date ,"UTF-8")+"&"
                    +Uri.encode("gander" )+"="+Uri.encode(gander )+"&"
                    +URLEncoder.encode("phone" ,"UTF-8")+"="+URLEncoder.encode(phone ,"UTF-8")+"&"
                    +Uri.encode("status" ,"UTF-8")+"="+Uri.encode(status ,"UTF-8")+"&"
                    +URLEncoder.encode("add" ,"UTF-8")+"="+URLEncoder.encode(add ,"UTF-8")+"&"
                    +URLEncoder.encode("blood" ,"UTF-8")+"="+URLEncoder.encode(blood ,"UTF-8")+"&"
                    +URLEncoder.encode("tall" ,"UTF-8")+"="+URLEncoder.encode(tall ,"UTF-8")+"&"
                    +URLEncoder.encode("weight" ,"UTF-8")+"="+URLEncoder.encode(weight ,"UTF-8")+"&"
                    +URLEncoder.encode("res_ID" ,"UTF-8")+"="+URLEncoder.encode(res_id,"UTF-8");
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
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("ee",""+id_id);
        alertDialog.setMessage(result);
        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
