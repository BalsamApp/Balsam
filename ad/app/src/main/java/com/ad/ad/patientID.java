package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class patientID extends AppCompatActivity {
    EditText patiantid_cheek;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_id);
        patiantid_cheek= (EditText)findViewById(R.id.patiant_id_cheek);
        button=(Button)findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idLogin(v);
            }
        });
    }


    public void idLogin(View view){

        String username=patiantid_cheek.getText().toString();
        String type="login";

        patientIDBackgroundWorker patientIDBackgroundWorker=new  patientIDBackgroundWorker(this);
        patientIDBackgroundWorker.execute(type,username);
    }

}
