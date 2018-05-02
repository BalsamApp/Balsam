package com.ad.ad;

/**
 * Created by My Pc on 10/04/18.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
public class Alarm_receiver extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        String med_name = intent.getExtras().getString("med_name");
        int noti_id = intent.getExtras().getInt("noti_id");
        Toast.makeText(context, "Alarm worked."+med_name, Toast.LENGTH_LONG).show();
        Intent intent1 = new Intent(context, myService.class);
        intent1.putExtra("med_name", med_name);
        intent1.putExtra("noti_id", noti_id);
        //Toast.makeText(context, "Alarm worked."+med_name, Toast.LENGTH_LONG).show();
        context.startService(intent1);


    }
}
