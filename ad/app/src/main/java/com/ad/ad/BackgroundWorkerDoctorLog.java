package com.ad.ad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by lenovo on 25/02/18.
 */
public class BackgroundWorkerDoctorLog extends AsyncTask<String,Void,String> {
    String user_name,password;
    //obj for coming context
    Context context;
    //obj for alert Dialog
    AlertDialog alertDialog;
    //construcrure with context
    BackgroundWorkerDoctorLog(Context ctx){
        context=ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        //url for php code file in webhost
        String login_url= "https://balsam.000webhostapp.com/DoctorLogin.php";
        try {
            //params to be send to php code
            String user_name=params[1];
            String password=params[2];
            //new obj from ura take url addess
            URL url=new URL(login_url);
            //url open conection by  httpurlconnection
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            //type of connect requestMethod post
            httpURLConnection.setRequestMethod("POST");
            //make output and input connection true
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            //defind  outputstream for writting data out to php file
            OutputStream outputStream=httpURLConnection.getOutputStream();
            //bufferwriter with encode
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_dta= URLEncoder.encode("user_name" ,"UTF-8")+"="+URLEncoder.encode(user_name ,"UTF-8")+"&"
                    +URLEncoder.encode("password" ,"UTF-8")+"="+URLEncoder.encode(password ,"UTF-8");
            bufferedWriter.write(post_dta);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            // establish inputstream and bufferReader
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line;
            //while there is data line to  read save it in result
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
        if(result.equals("\"p\"")){
       Intent intent =new Intent(context,patientID.class);
         context.startActivity(intent);


        }
        else{

            alertDialog.setMessage(result);
            alertDialog.show();

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}