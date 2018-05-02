package com.ad.ad;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

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

import static com.ad.ad.patient_login.id_id;

/**
 * Created by malak on 24/03/18.
 */

public class p_myinfo_activity extends AppCompatActivity {
    Button buttonmyinfo;
    BoomMenuButton bmb;
    String id;
    String name;
    String[]ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_myinfo_lay);

        //get data from previous page
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        Log.d("id", ""+id);

        toolBar();
        boomBotton();
        getJSON();
        //after click on the button, it will move to edit patient information page(p_editmyinfo.java)
        buttonmyinfo = (Button) findViewById(R.id.butt_editinfo);
        buttonmyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivitmyinfo();
            }
        });
    }

    private void launchActivitmyinfo() {

        Intent intent = new Intent(this, p_editmyinfo.class);
        intent.putExtra("id",getIntent().getExtras().getString("id"));
        //put or stor data to move to the next page
        intent.putExtra("name",ss[2]);
        intent.putExtra("date",ss[3]);
        intent.putExtra("gender",ss[4]);
        intent.putExtra("phone",ss[5]);
        intent.putExtra("status",ss[6]);
        intent.putExtra("add",ss[7]);
        intent.putExtra("tall",ss[9]);
        intent.putExtra("weight",ss[10]);
        startActivity(intent);
    }

    public void getJSON(){ new BackgroundTaskGet().execute();
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
                String post_dta = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode("" + id, "UTF-8");
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
            Log.d("id", "" + result);
            String s = result.replace('\"', ' '); // replace all " -> the blank
            s = s.trim();
            ss = s.split("  ");// Convert sentences (words) to Array

            //set each text in TextView
            TextView tt = (TextView) findViewById(R.id.etidd);
            tt.setText(ss[0]);
            tt = (TextView) findViewById(R.id.etname);
            tt.setText(ss[2]);
            tt = (TextView) findViewById(R.id.etdate);
            tt.setText(ss[3]);
            tt = (TextView) findViewById(R.id.etgender);
            tt.setText(ss[4]);
            tt = (TextView) findViewById(R.id.etphone);
            tt.setText(ss[5]);
            tt = (TextView) findViewById(R.id.etstatus);
            tt.setText(ss[6]);
            tt = (TextView) findViewById(R.id.etaddr);
            tt.setText(ss[7]);
            tt = (TextView) findViewById(R.id.etboold);
            tt.setText(ss[8]);
            tt = (TextView) findViewById(R.id.ettall);
            tt.setText(ss[9]);
            tt = (TextView) findViewById(R.id.etwei);
            tt.setText(ss[10]);
            tt = (TextView) findViewById(R.id.etsens);
            tt.setText(ss[12]);
            tt = (TextView) findViewById(R.id.etdis);
            tt.setText(ss[13]);
        }
    }

    private void toolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Patient information");
    }

    private void boomBotton() {


        bmb = (BoomMenuButton) findViewById(R.id.boom);

        int info_color = Color.rgb(255, 133, 51);
        TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.home)
                .normalText("Home").pieceColor(info_color).normalColor(info_color).textSize(16).textWidth(260).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent myIntent = new Intent(p_myinfo_activity.this, MainActivity.class);
                        myIntent.putExtra("id",id_id+"");
                        startActivity(myIntent);


                    }
                });
        bmb.addBuilder(builder);

        info_color = Color.rgb(102, 255, 51);
        builder = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.man)
                .normalText("My information").pieceColor(info_color).normalColor(info_color).textSize(16).textWidth(260).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent myIntent = new Intent(p_myinfo_activity.this, p_myinfo_activity.class);
                        myIntent.putExtra("id",id_id+"");
                        startActivity(myIntent);


                    }
                });
        bmb.addBuilder(builder);

        int call_color = Color.rgb(102, 0, 102);
        builder = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.call)
                .normalText("Call an ambulance").pieceColor(call_color).normalColor(call_color).textSize(16).textHeight(50).textWidth(265).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent myIntent = new Intent(Intent.ACTION_DIAL);
                        myIntent.setData(Uri.parse("tel:997"));
                        startActivity(myIntent);


                    }
                });

        bmb.addBuilder(builder);

        int pass_color = Color.rgb(51, 204, 255);
        builder = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.key2)
                .normalText("Reset password").pieceColor(pass_color).normalColor(pass_color).textSize(16).textWidth(260)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent myIntent = new Intent(p_myinfo_activity.this,reset_password.class);
                        myIntent.putExtra("id",""+id_id);
                        startActivity(myIntent);


                    }
                });

        bmb.addBuilder(builder);



        int out_color = Color.rgb(255, 77, 77);
        builder = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.sign_out2)
                .normalText("Sign out").pieceColor(out_color).normalColor(out_color).textSize(16).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent myIntent = new Intent(p_myinfo_activity.this,
                                patient_login.class);
                        startActivity(myIntent);


                    }
                });;
        bmb.addBuilder(builder);

    }





}
