package com.ad.ad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;

import static com.ad.ad.DoctorLogin.docId;

public class CreateNewPresc extends AppCompatActivity {
    EditText Date,Dura;
    TextView pID,dID;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_presc);
        pID=(TextView)findViewById(R.id.textViewPID);
        pID.setText(getIntent().getExtras().getString("PID"));
        dID=(TextView)findViewById(R.id.editText7);
        dID.setText(docId.toString());
        Date=(EditText)findViewById(R.id.editText10);
        Dura=(EditText)findViewById(R.id.editText11);
        button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             onclickCreate(v);
                 gotoclass();

            }
        });
    }

    public void onclickCreate(View view){

        String patientID=pID.getText().toString();
        String docID=dID.getText().toString();
        String date=Date.getText().toString();
        String duration=Dura.getText().toString();
        String type="login";

        CreatPrescBackgraoundWork creatPrescBackgraoundWork=new CreatPrescBackgraoundWork(this);
        creatPrescBackgraoundWork.execute(type,patientID,docID,date,duration);
    }
    public void gotoclass(){
        Intent intent =new Intent(this,AddMed.class);
        intent.putExtra("pID",pID.getText().toString());
        intent.putExtra("dID",dID.getText().toString());
        intent.putExtra("date",Date.getText().toString());
        intent.putExtra("duration",Dura.getText().toString());
        startActivity(intent);
    }
    public static String createPrescription(int patientidd, int doctoridd, String date, int duration){
        return "Prescription Created Successfully";
    }
}
