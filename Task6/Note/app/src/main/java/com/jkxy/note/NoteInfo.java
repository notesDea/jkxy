package com.jkxy.note;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.jkxy.note.service.RemindTimeReceiver;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by notes on 2016/8/6.
 * 记录笔记的数据
 */
public class NoteInfo implements Serializable{
    private long id;
    private int time;
    private String content;
    private Calendar noteTime = Calendar.getInstance();

    public NoteInfo() {
    }

    public NoteInfo(int time, String content) {
        this.time = time;
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Calendar getCalendarTime() {
        noteTime.set(Calendar.HOUR_OF_DAY, time);
        noteTime.set(Calendar.MINUTE, 0);
        noteTime.set(Calendar.SECOND, 0);
        noteTime.set(Calendar.MILLISECOND, 0);
        if (noteTime.before(Calendar.getInstance())) {
            noteTime.add(Calendar.DATE, 1);
        }
        return noteTime;
    }

    public void scheduleAlarm(Context context) {

        Intent intent = new Intent(context, RemindTimeReceiver.class);
        intent.setAction(RemindTimeReceiver.ALARM_RECEIVER_ACTION);
        intent.putExtra(NotesDatabase.NOTES_TABLE, this);

        PendingIntent pi = PendingIntent.getBroadcast(context, (int) getId(), intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        long triggerAtMillis = getCalendarTime().getTimeInMillis();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pi);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pi);
        }
        Log.d("alarm", "定时的时间是：" + new Date(getCalendarTime().getTimeInMillis()).toString());

    }

    public Notification notesNotification(Context context) {
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("笔记提醒")
                .setContentText(time + "：" + content)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        return notification;
    }

    @Override
    public String toString() {
        return time + "\n" + content;
    }
}
