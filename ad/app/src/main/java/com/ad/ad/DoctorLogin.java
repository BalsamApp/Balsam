package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DoctorLogin extends AppCompatActivity {
    public static String docId;

    EditText id ,password;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        //refrenc to used edit text and button
        id=(EditText)findViewById(R.id.D_id);
        password=(EditText)findViewById(R.id.p_password);
        button=(Button)findViewById(R.id.p_login);
        // button onclick calling method
        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idLogin(v);
        }
    });


}

 //method for calling loging backgroundworker
    public void idLogin(View view){

        String username=id.getText().toString();
        //to send it to createPresc
        docId=username;
        String Dpassword=password.getText().toString();
        String type="login";

        BackgroundWorkerDoctorLog backgroundWorkerDoctorLog=new  BackgroundWorkerDoctorLog(this);
        backgroundWorkerDoctorLog.execute(type,username,Dpassword);
    }

    public void forget(View view) {
        Intent intent =new Intent(this,passwordcheek.class);
        startActivity(intent);
    }

    public static int testDoctorLogin_username(int user_name){
        return user_name;
    }

    public static boolean testDoctorLogin_passwoed(int password){
        if(password ==1234)
            return true;
        else
            return false;
    }
}
