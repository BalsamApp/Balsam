package com.ad.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Docotr extends AppCompatActivity {
  Button button,buttonPresc,pbutton;
  TextView patiantId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docotr);
        patiantId= (TextView)findViewById(R.id.bypatiantId);
        //get pID from p Id cheek page
        patiantId.setText(getIntent().getExtras().getString("userID"));
        //3 button on click listener clling methods
        button=(Button)findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoclassHealthstatus();
            }
        });
        buttonPresc=(Button)findViewById(R.id.button4);
        buttonPresc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoclassPresc();
            }
        });
        pbutton=(Button)findViewById(R.id.button3);
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoclassPInfo();
            }
        });
    }
    public void gotoclassHealthstatus(){
        Intent intent =new Intent(this,get_data.class);
        intent.putExtra("userID",patiantId.getText().toString());
        startActivity(intent);
    }
    public void gotoclassPresc(){
        Intent intent =new Intent(this,CreateNewPresc.class);
        intent.putExtra("PID",patiantId.getText().toString());
        startActivity(intent);
    }
    public void gotoclassPInfo(){
        Intent intent =new Intent(this,paitentPersonal.class);
        intent.putExtra("id",patiantId.getText().toString());
        startActivity(intent);
    }
}
