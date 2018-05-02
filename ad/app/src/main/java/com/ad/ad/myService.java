package com.ad.ad;


import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;


public class myService extends IntentService {
    MediaPlayer mMediaPlayer;



    public myService () {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder builder = new Notification.Builder(this);
        String med_name = intent.getExtras().getString("med_name");
        //String med_Time = intent.getExtras().getString("med_Time");
        int noti_id = intent.getExtras().getInt("noti_id");


        builder.setContentTitle("Medicine Time ");
        builder.setContentText("Time to take your medicine: "+med_name);
        builder.setSmallIcon(R.drawable.balsam_logo);
        //Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        builder.setSound(sound);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(noti_id, notificationCompat);
    }
}





