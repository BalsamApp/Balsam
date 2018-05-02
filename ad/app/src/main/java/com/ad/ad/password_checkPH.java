package com.ad.ad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

public class password_checkPH extends AppCompatActivity {
    EditText id,phone,year;
    Spinner day,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_check_ph);
        id=(EditText) findViewById(R.id.check_pass_id);
        phone=(EditText) findViewById(R.id.phone_check);
        year=(EditText) findViewById(R.id.year_check);
        day=(Spinner) findViewById(R.id.day_check);
        month=(Spinner) findViewById(R.id.month_check);

        Button check = (Button) findViewById(R.id.check_pass);

        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                // Intent myIntent = new Intent(MainActivity.this, med_activity.class);
                pass_check();
            }
        });
    }
    public void pass_check(){

        String idd=id.getText().toString();
        String phonee=phone.getText().toString();
        String dday=day.getSelectedItem().toString();
        String mmonth=month.getSelectedItem().toString();
        String yyear=year.getText().toString();
        BackgroundWorker1 backgroundWorkerd=new BackgroundWorker1(this);
        backgroundWorkerd.execute(idd,phonee,dday,mmonth,yyear);

    }

    class BackgroundWorker1 extends AsyncTask<String,Void,String> {

        String day,month,year,id,phone;
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker1(password_checkPH ctx){
            context=ctx;
        }

        String idd;
        @Override
        protected String doInBackground(String... params) {

            String login_url= "http://balsam.000webhostapp.com/pass_checkPH.php";
            try {
                idd=params[0];
                String phonee=params[1];
                String dday=params[2];
                String mmonth=params[3];
                String yyear=params[4] ;
                String date=dday+"-"+mmonth+"-"+yyear;
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_dta= URLEncoder.encode("id" ,"UTF-8")+"="+URLEncoder.encode(idd ,"UTF-8")+"&"+
                        URLEncoder.encode("phone" ,"UTF-8")+"="+URLEncoder.encode(phonee ,"UTF-8")+"&"+
                        URLEncoder.encode("date" ,"UTF-8")+"="+URLEncoder.encode(date ,"UTF-8");
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

            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog =new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");

        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("res",result);
            if(result.equals("\"The data is not correct\"")){
                alertDialog.setMessage(result);
                alertDialog.show();

            }else {
                alertDialog.setMessage(result);
                alertDialog.show();
                Intent myIntent = new Intent(password_checkPH.this, reset_passwordPH.class);
                myIntent.putExtra("pharid", "" + idd);
                startActivity(myIntent);
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
