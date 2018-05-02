package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class patient_login extends AppCompatActivity {
    EditText UsernameEt,passowrdEt;
    public static int id_id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login_activity);
        UsernameEt=(EditText)findViewById(R.id.p_id);
        passowrdEt=(EditText)findViewById(R.id.p_password);

        TextView pass = (TextView) findViewById(R.id.forget_password);

        // Capture button clicks
        pass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {


                // Start NewActivity.class
                Intent myIntent = new Intent(patient_login.this, password_check.class);
                startActivity(myIntent);
            }
        });

        TextView n = (TextView) findViewById(R.id.new_account);

        // Capture button clicks
        n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                id_id=0;
                // Start NewActivity.class
                Intent myIntent = new Intent(patient_login.this,
                        patient_signUp.class);
                myIntent.putExtra("res_id",""+0);
                startActivity(myIntent);
            }
        });
    }
    public void onLogin(View view){

        EditText id    =(EditText)findViewById(R.id.p_id);
        id_id=Integer.parseInt(id.getText().toString());
        String username=UsernameEt.getText().toString();
        String password=passowrdEt.getText().toString();
        String type="login";

        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password);
    }

    public static int testPatientLogin_username(int user_name){
            return user_name;
    }

    public static boolean testPatientLogin_passwoed(int password){
        if(password ==1234)
            return true;
        else
            return false;
    }

}
