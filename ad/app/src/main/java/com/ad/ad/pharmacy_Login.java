package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class pharmacy_Login extends AppCompatActivity {
    EditText UsernameEt,passowrdEt;
    Button button;
    public static String pharid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy__login);
        UsernameEt=(EditText)findViewById(R.id.pharmacy_id);
        passowrdEt=(EditText)findViewById(R.id.ph_password);
        button = (Button) findViewById(R.id.ph_login);

        TextView pass = (TextView) findViewById(R.id.forget_password);

        // Capture button clicks
        pass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {


                // Start NewActivity.class
                Intent myIntent = new Intent(pharmacy_Login.this, password_checkPH.class);
                startActivity(myIntent);
            }
        });


    }
    public void onLogin(View view){
        String username=UsernameEt.getText().toString();
        pharid=username;
        String password=passowrdEt.getText().toString();
        String type="login";

        pharmacistLogin backgroundWorker=new pharmacistLogin(this);
        backgroundWorker.execute(type,username,password);

    }


    public static int testPharmacyLogin_username(int user_name){
        return user_name;
    }

    public static boolean testPharmacyLogin_passwoed(int password){
        if(password ==1234)
            return true;
        else
            return false;
    }
}
