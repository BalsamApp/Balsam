package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

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

public class AddMed extends AppCompatActivity {
    TextView pid,did,dd,du;
    Button button;
    Bundle savedInstanceState2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);
        pid=(TextView)findViewById(R.id.textView8);
        pid.setText(getIntent().getExtras().getString("pID"));
        did=(TextView)findViewById(R.id.textView10);
        did.setText(getIntent().getExtras().getString("dID"));
        dd=(TextView)findViewById(R.id.textView11);
        dd.setText(getIntent().getExtras().getString("date"));
        du=(TextView)findViewById(R.id.textView12);
        du.setText(getIntent().getExtras().getString("duration"));
    }

    public void onAddMed(View view){

        TextView pid=(TextView)findViewById(R.id.textView8);
        String pID=pid.getText().toString();
        TextView did=(TextView)findViewById(R.id.textView10);
        String dID=did.getText().toString();
        TextView dd=(TextView)findViewById(R.id.textView11);
        String date=dd.getText().toString();
        TextView du=(TextView)findViewById(R.id.textView12);
        String duration=du.getText().toString();

        EditText med=(EditText)findViewById(R.id.editText3);
        String   medName=med.getText().toString();
        EditText meddu=(EditText)findViewById(R.id.editText4);
        String   medDur=meddu.getText().toString();
        EditText time=(EditText)findViewById(R.id.editText5);
        String   medTime=time.getText().toString();
        EditText after=(EditText)findViewById(R.id.editText6);
        String   afterEach=after.getText().toString();
        EditText note=(EditText)findViewById(R.id.editText8);
        String   medNote=note.getText().toString();
        String type="login";

        background backgroundO=new background(this);
        backgroundO.execute(type,pID,dID,date,duration,medName,medDur,medTime,afterEach,medNote);
    }

    public void Add(View view){
        Intent intent =new Intent(this,AddMed.class);
        intent.putExtra("pID",pid.getText().toString());
        intent.putExtra("dID",did.getText().toString());
        intent.putExtra("date",dd.getText().toString());
        intent.putExtra("duration",du.getText().toString());
        startActivity(intent);


    }
    public static String Add_med(int patientidd, int doctoridd,int prescid, String medname, int duration, int timesPerDay, int after,String notes){
        return "Done Successfully";
    }

}
