package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class home_page_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);

    }

    public void petient(View view){
        Intent myIntent = new Intent(this, patient_login.class);
        startActivity(myIntent);
    }
    public void pharm(View view){
        Intent myIntent = new Intent(this, pharmacy_Login.class);
        startActivity(myIntent);
    }
    public void doctor(View view){
        Intent myIntent = new Intent(this, DoctorLogin.class);
        startActivity(myIntent);
    }
}
