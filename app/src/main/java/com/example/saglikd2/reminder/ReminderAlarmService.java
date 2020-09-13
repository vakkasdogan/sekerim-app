package com.example.saglikd2.reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;


import com.example.saglikd2.R;

import com.example.saglikd2.AddReminderActivity;
import com.example.saglikd2.data.AlarmReminderContract;

import static com.example.saglikd2.App.CHANNEL_1_ID;

/**
 * Created by delaroy on 9/22/17.
 */

public class ReminderAlarmService extends IntentService {
    private static final String TAG = ReminderAlarmService.class.getSimpleName();

    private static final int NOTIFICATION_ID = 42;
    //This is a deep link intent, and needs the task stack
    public static PendingIntent getReminderPendingIntent(Context context, Uri uri) {
        Intent action = new Intent(context, ReminderAlarmService.class);
        action.setData(uri);
        return PendingIntent.getService(context, 0, action, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public ReminderAlarmService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Uri uri = intent.getData();

        //Display a notification to view the task details
        Intent action = new Intent(this, AddReminderActivity.class);
        action.setData(uri);
        PendingIntent operation = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(action)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        //Grab the task description
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        String description = "";
        try {
            if (cursor != null && cursor.moveToFirst()) {
                description = AlarmReminderContract.getColumnString(cursor, AlarmReminderContract.AlarmReminderEntry.KEY_TITLE);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        Notification note = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.reminder_title))
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                .setContentIntent(operation)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setAutoCancel(true)
                .build();
        manager.notify(NOTIFICATION_ID, note);

        Intent activityIntent = new Intent(getApplicationContext(), AddReminderActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                .setContentTitle("message")
                .setContentText("be careful")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("bilgi")
                        .setBigContentTitle("bigbalik")
                        .setSummaryText("özet"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .build();


        notificationManager.notify(1, notification);



        Uri alarmMelodisi = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmMelodisi== null){
            alarmMelodisi= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(this,alarmMelodisi);
        ringtone.play();




    }
}