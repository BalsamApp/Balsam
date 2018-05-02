package com.ad.ad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import static com.ad.ad.DoctorLogin.docId;
public class resetPassword extends AppCompatActivity {
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        setContentView(R.layout.activity_reset_password_d);
    }
    public void reset_pass(View view){
        EditText pass=(EditText)findViewById(R.id.new_pass) ;
        String new_pass=pass.getText().toString();
        BackgroundWorkerpass backgroundWorkerd=new BackgroundWorkerpass(this);
        backgroundWorkerd.execute(new_pass,id);

    }

    class BackgroundWorkerpass extends AsyncTask<String, Void, String> {

        Context context;
        AlertDialog alertDialog;

        BackgroundWorkerpass(Context ctx) {
            context = (Context) ctx;
        }


        @Override
        protected String doInBackground(String... params) {

            String login_url = "http://balsam.000webhostapp.com/Dreset_pass.php";
            try {

                String pass = params[0];
                Log.d("pass",pass);
                String id = params[1];
                Log.d("id",id);

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_dta = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&"
                        + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(post_dta);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "this:";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
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
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");

        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("ee", "" + docId);
            alertDialog.setMessage(result);
            alertDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    public static String reset_Passwoerd_D(int doctorid, int phone, String date){
        return "Password Updated Successfully";
    }
}