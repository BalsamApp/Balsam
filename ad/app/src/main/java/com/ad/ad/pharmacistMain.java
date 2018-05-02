package com.ad.ad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class pharmacistMain extends AppCompatActivity {
    EditText UsernameEt;
    Button button;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_main);
        UsernameEt = (EditText) findViewById(R.id.P_ID);
        button = (Button) findViewById(R.id.searchBtn);

    }

    public void onLogin2(View view){
        String username=UsernameEt.getText().toString();

        String type="login";

        paitentLogin backgroundWorker=new paitentLogin(this);
        backgroundWorker.execute(type,username);

    }
}

