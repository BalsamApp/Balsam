/**
 * Created by My Pc on 20/04/18.
 */




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
 * Created by arar_ on 17/04/18.
 */

public class patientActiveUpdate extends AsyncTask<String,Void,String> {
    String prescriptionid;
    AlertDialog alertDialog;
    String jason_string;
    String json_url;
    String med_name;
    String status;
    patientActiveUpdate( ){
    }
    @Override
    protected void onPreExecute() {
        json_url= "http://balsam.000webhostapp.com/pharActiveUpdate.php";
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            prescriptionid =params[0];
            med_name=params[1];
            status=params[2];
            Log.d("pre",prescriptionid);
            URL url = new URL(json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_dta= URLEncoder.encode("ID" ,"UTF-8")+"="+URLEncoder.encode(""+prescriptionid,"UTF-8")+'&'+
                    URLEncoder.encode("med_st" ,"UTF-8")+"="+URLEncoder.encode(""+status,"UTF-8")+'&'+
                    URLEncoder.encode("med_name" ,"UTF-8")+"="+URLEncoder.encode(""+med_name,"UTF-8");
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
    protected void onPostExecute(String result) {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


