package com.example.saglikd2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());
        Uri alarmMelodisi = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmMelodisi== null){
            alarmMelodisi= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context,alarmMelodisi);
        ringtone.play();

    }
}
