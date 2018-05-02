package com.ad.ad;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;

import static com.ad.ad.patient_login.id_id;


public class FamilyActivity extends AppCompatActivity   {

    public static ArrayList<String> idds;
    public static ArrayList<String> namess;
    public static Context c;

    BoomMenuButton bmb;
  //  public TextView textView = (TextView) findViewById(R.id.dep_id);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c=this;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_family);
        toolBar();
        boomBotton();
        getJSON();
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatingActionButton3);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(FamilyActivity.this,
                        patient_signUp.class);
                myIntent.putExtra("res_id",id_id);
                startActivity(myIntent);
            }
        });

    }









    private void toolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("My family");


    }

    private void boomBotton() {


        bmb = (BoomMenuButton) findViewById(R.id.boom);

        int info_color = Color.rgb(255, 133, 51);
        TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.home)
                .normalText("Home").pieceColor(info_color).normalColor(info_color).textSize(16).textWidth(260).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent myIntent = new Intent(FamilyActivity.this, MainActivity.class);
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
                        Intent myIntent = new Intent(FamilyActivity.this, p_myinfo_activity.class);
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
                        Intent myIntent = new Intent(FamilyActivity.this,reset_password.class);
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
                        Intent myIntent = new Intent(FamilyActivity.this,
                                patient_login.class);
                        startActivity(myIntent);


                    }
                });;
        bmb.addBuilder(builder);

    }




    public void getJSON(){
        new FamilyActivity.BackgroundTaskGet().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    class BackgroundTaskGet extends AsyncTask<Void, Void, String> {
        String jason_string;
        String json_url;
        ArrayList<String> ids=new ArrayList<String>();
        ArrayList<String> name=new ArrayList<String>();
        @Override
        protected void onPreExecute() {
            json_url="https://balsam.000webhostapp.com/family_view.php";
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
                String post_dta= URLEncoder.encode("ID" ,"UTF-8")+"="+URLEncoder.encode(""+id_id,"UTF-8");
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



if(result!=null) {

    String s = result.replace('\"', ' ');//تبديل كل " بفراغ
    s = s.trim();
    String[] ss = s.split("  ");//تحويل الكلام الى ارراي

    ArrayList<String> idd = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();

    int j = 0;
    for (int i = 0; i < ss.length / 2; i++) {
        idd.add(ss[j]);
        j = +2;//زائد على حسب الكولم
    }

    int d = 1;
    for (int i = 0; i < ss.length / 2; i++) {
        names.add(ss[d]);
        d = d + 2;//زائد على حسب الكولم
    }


    idds = idd;
    namess = names;


    RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
    famiy_recycleview myAdapter = new famiy_recycleview(c, idds, namess);
    myrv.setLayoutManager(new GridLayoutManager(c, 3));
    myrv.setAdapter(myAdapter);
}
if(result.equals("")){
    TextView tt=(TextView) findViewById(R.id.no_dep);
    tt.setText("no family member");
}
        }
    }

}

