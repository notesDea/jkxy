package com.jkxy.note.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jkxy.note.NoteInfo;
import com.jkxy.note.NotesDatabase;

import java.util.List;

public class RemindTimeReceiver extends BroadcastReceiver {
    public static final String ALARM_RECEIVER_ACTION= "alarm";


    public RemindTimeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            //全部遍历一遍
            NotesDatabase.initDb(context);
            List<NoteInfo> notesList = NotesDatabase.getAll();
            for (NoteInfo note : notesList) {
                note.scheduleAlarm(context);
            }

        } else if (intent.getAction().equals(ALARM_RECEIVER_ACTION)){
            Log.d("Receiver", "接收到提醒时：");
            //提醒响起时，开启通知
            Bundle bundle = intent.getExtras();
            NoteInfo note = (NoteInfo) bundle.getSerializable(NotesDatabase.NOTES_TABLE);

            Notification notification = note.notesNotification(context);
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);
        }
    }
}
