package com.ad.ad;

/**
 * Created by arar_ on 15/04/18.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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

public class pharmacistLogin extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    pharmacistLogin(pharmacy_Login ctx){
        context=ctx;
    }
    String pharid;

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url= "http://balsam.000webhostapp.com/pharmacistLogin.php";
        try {
            pharid=params[1];
            String password=params[2];
            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_dta= URLEncoder.encode("user_name" ,"UTF-8")+"="+URLEncoder.encode(pharid,"UTF-8")+"&"
                    +URLEncoder.encode("password" ,"UTF-8")+"="+URLEncoder.encode(password ,"UTF-8");
            bufferedWriter.write(post_dta);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line;
            while ((line = bufferedReader.readLine())!=null){
                result +=line;
            }
            //
            // toast.show();
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(type.equals("login")){


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
        if(result.equals("\"login not success\"")){
            alertDialog.setMessage(result);
            alertDialog.show();

        }else {
            alertDialog.setMessage(result);
            alertDialog.show();
            Intent myIntent = new Intent(context, pharmacistMain.class);
            context.startActivity(myIntent);
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
