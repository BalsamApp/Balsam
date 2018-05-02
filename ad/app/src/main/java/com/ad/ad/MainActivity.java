package com.ad.ad;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
	import java.net.HttpURLConnection;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.net.URLEncoder;
	import java.sql.Time;
	import java.text.ParseException;
	import java.util.ArrayList;
	import java.util.Date;
import java.util.List;
import static com.ad.ad.patient_login.id_id;


public class MainActivity extends AppCompatActivity   {


    public static  List<alarm_object> alarmList = new ArrayList<>();
    int listPosition;
    Button buttmed;
    BoomMenuButton bmb;
    Context context ;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        listPosition = LoadInt("listPosition");
        getJSON();
        setContentView(R.layout.activity_main);
        toolBar();
        boomBotton();

        buttmed = (Button) findViewById(R.id.med);

        buttmed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                // Intent myIntent = new Intent(MainActivity.this, med_activity.class);
                Intent myIntent = new Intent(MainActivity.this, p_med_activity_recycleview.class);
                Log.d("id_____id", ""+id_id);
                myIntent.putExtra("id",""+id_id);
                startActivity(myIntent);
            }
        });

        Button buttonDep = (Button) findViewById(R.id.dep);

        // Capture button clicks
        buttonDep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        FamilyActivity.class);
                startActivity(myIntent);
            }
        });
        Button buttonAla = (Button) findViewById(R.id.alarm);

        // Capture button clicks
        buttonAla.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        alarm_activty.class);
               // Log.d("id___pp",id_id);
                myIntent.putExtra("id",""+id_id);
                startActivity(myIntent);
            }
        });
    }

    private void toolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Home page");


    }

    private void boomBotton() {


        bmb = (BoomMenuButton) findViewById(R.id.boom);

        int info_color = Color.rgb(255, 133, 51);
        TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.home)
                .normalText("Home").pieceColor(info_color).normalColor(info_color).textSize(16).textWidth(260).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
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
                        Intent myIntent = new Intent(MainActivity.this, p_myinfo_activity.class);
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
                        Intent myIntent = new Intent(MainActivity.this,reset_password.class);
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
                        Intent myIntent = new Intent(MainActivity.this,
                                patient_login.class);
                        startActivity(myIntent);


                    }
                });;
        bmb.addBuilder(builder);

    }
    public void getJSON(){
        new BackgroundTaskGet().execute();
    }

    class BackgroundTaskGet extends AsyncTask<Void, Void, String> {
        String jason_string;
        String json_url;
        ArrayList<String> ids=new ArrayList<String>();
        ArrayList<String> name=new ArrayList<String>();
        @Override
        protected void onPreExecute() {
            json_url="https://balsam.000webhostapp.com/get_alarm.php";

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

            alarmList = new ArrayList<>();
            Log.d("all result of db", result);

            if (!result.equals(null)) {
                alarmList= new ArrayList<>();
                String sd = result.replace("\"\"", "=");//تبديل كل " بفراغ
                String s = sd.replace("\"", " ");//تبديل كل " بفراغ

                s = s.trim();
                String[] ss = s.split("=");//تحويل الكلام الى ارراي

                ArrayList<String> med_name = new ArrayList<String>();
                ArrayList<String> med_duration = new ArrayList<>();
                ArrayList<Integer> med_timetaken = new ArrayList<>();
                ArrayList<String> med_rangeh = new ArrayList<String>();
                ArrayList<String> med_notes = new ArrayList<String>();
                ArrayList<String> pre_id = new ArrayList<>();
                ArrayList<String> med_active = new ArrayList<String>();
                int f1 = 0, a1 = 1, b1 = 2, c1 = 3, d1 = 4, e1 = 5, g1 = 6;
                for (int i = 0; i < ss.length / 7; i++) {
                    pre_id.add(ss[f1]);
                    med_name.add(ss[a1]);
                    med_duration.add(ss[b1]);
                    med_timetaken.add(Integer.parseInt(ss[c1].trim()));
                    med_rangeh.add(ss[d1]);
                    med_notes.add(ss[e1]);
                    med_active.add(ss[g1]);
                    f1 = f1 + 7;
                    a1 = a1 + 7;//زائد على حسب الكولم
                    b1 = b1 + 7;//زائد على حسب الكولم
                    c1 = c1 + 7;//زائد على حسب الكولم
                    d1 = d1 + 7;//زائد على حسب الكولم
                    e1 = e1 + 7;//زائد على حسب الكولم
                    g1 = g1 + 7;
                }







                for (int i = 0; i < pre_id.size(); i++) {
                    try {
                        Log.d("listPosition befor add ", listPosition+"");

                        alarm_object c = new alarm_object(listPosition,pre_id.get(i), med_name.get(i), med_timetaken.get(i), Integer.parseInt(med_duration.get(i)), med_notes.get(i));
                        listPosition++;
                        Log.d("listPosition in add ", listPosition+"");


                        alarmList.add(c);
                        patientActiveUpdate backgroundWorker=new patientActiveUpdate();
                        backgroundWorker.execute(c.getPrescID(),c.getName(),"Enabled");


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

                SaveInt("listPosition",listPosition);
                Log.d("listPosition after add", listPosition+"");


            }
        }


    }



    public void SaveInt(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public int LoadInt(String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int savedValue = sharedPreferences.getInt("key", 0);
        return savedValue;
    }

    public static String reset_Passwoerd_Patient(int patientid){
        return "Password Updated Successfully";
    }


}

