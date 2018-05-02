package com.ad.ad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import android.content.Intent;
import android.widget.Spinner;
import android.widget.Toast;

import static com.ad.ad.patient_login.id_id;
/*
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;*/

public class patient_signUp extends AppCompatActivity {
    EditText sid,passsword,name ,phhone,p_add_up ,p_tall_up ,p_weight_up,year ;
    RadioGroup p_status_up,p_gander_up ,p_blood_up  ;
    Button bRegister ;
    Spinner day,month;
    public String res_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_sign_up_activity);
        sid=(EditText)findViewById(R.id.sid);
        passsword=(EditText)findViewById(R.id.passwordd);
        name=(EditText)findViewById(R.id.name);
        phhone=(EditText)findViewById(R.id.phonee);
        year=(EditText)findViewById(R.id.year_check);
        day=(Spinner) findViewById(R.id.day_check);
        month=(Spinner) findViewById(R.id.month);
        p_add_up=(EditText)findViewById(R.id.p_add_up);
        p_tall_up=(EditText)findViewById(R.id.p_tall_up);
        p_weight_up=(EditText)findViewById(R.id.p_weight_up);
        p_status_up=(RadioGroup)findViewById(R.id.p_status_up);
        p_gander_up=(RadioGroup)findViewById(R.id.p_gander_up);
        p_blood_up=(RadioGroup)findViewById(R.id.p_blood_up);
       //bRegister =(Button)findViewById(R.id.bRegister);
    Intent intent = getIntent();
    res_id = intent.getExtras().getString("res_id");
    }


    public void onLogin(View view){

        try {
            String pid = sid.getText().toString();
            String ppassword = passsword.getText().toString();
            String pname = name.getText().toString();
            String pphone = phhone.getText().toString();
            String add = p_add_up.getText().toString();
            String tall = p_tall_up.getText().toString();
            String weight = p_weight_up.getText().toString();

            int selectedId = p_status_up.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            String status = radioButton.getText().toString();

            selectedId = p_gander_up.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            String gander = radioButton.getText().toString();

            selectedId = p_blood_up.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            String blood = radioButton.getText().toString();
            String type = "login";

            String dday = day.getSelectedItem().toString();
            String mmonth = month.getSelectedItem().toString();
            String yyear = year.getText().toString();

            String date = yyear + "-" + mmonth + "-" + dday;
            BackgroundWorkerSin backgroundWorkerd = new BackgroundWorkerSin(this);
            backgroundWorkerd.execute(type, pid, ppassword, pname, date, gander, pphone, status, add, blood, tall, weight, id_id + "");
        }catch (Exception ex){
            Toast.makeText(this,"Please fill in the empty field",Toast.LENGTH_SHORT).show();
        }
    }


    public static String patient_sinup(int idd, String password, String name, int phone,String gender,String brithDay,
                                      String status, String address,int tall, int weight,String blood){
        return "Done Successfully";
    }

    public static String dep_patient_sinup(int paientidd, int depidd, String password, String name, int phone,String gender,String brithDay,
                                       String status, String address,int tall, int weight,String blood){
        return "Done Successfully";
    }
}
