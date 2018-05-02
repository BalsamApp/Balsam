package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pharmacistHome extends AppCompatActivity {
    Button precBtn;
    Button viewInfo;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_home);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            id = extras.getString("id");
        }

        precBtn= (Button) findViewById(R.id.presBtn);
        precBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                        // Start NewActivity.class
                        // Intent myIntent = new Intent(MainActivity.this, med_activity.class);
                        Intent myIntent = new Intent(pharmacistHome.this, med_activity_recycleview.class);
                     myIntent.putExtra("id", id);;
                        startActivity(myIntent);

            }
        });

        //////////////////
        viewInfo= (Button) findViewById(R.id.viewP);
        viewInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {


                //Start NewActivity.class
                Intent myIntent = new Intent(pharmacistHome.this, paitentPersonal_PH.class);
                myIntent.putExtra("id", id);
                startActivity(myIntent);
            }
        });





    }
}
