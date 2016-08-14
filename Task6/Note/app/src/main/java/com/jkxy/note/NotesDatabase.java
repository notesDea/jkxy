package com.jkxy.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by notes on 2016/8/4.
 */
public class NotesDatabase extends SQLiteOpenHelper {
    static NotesDatabase instance = null;
    static SQLiteDatabase database = null;

    public static final String NOTES_TABLE = "notes";
    public static final String NOTES_ID = "_id";
    public static final String NOTES_TIME = "time";
    public static final String NOTES_CONTENT = "content";
    public static final String NOTES_DB= "Notes.db";
    public static final int NOTES_VERSION= 1;

    public static final String CREATE_NOTES = "create table " + NOTES_TABLE + " (" +
            NOTES_ID + " integer primary key autoincrement, " +
            NOTES_TIME + " integer, " +
            NOTES_CONTENT + " text)";

    public NotesDatabase(Context context) {
        super(context, NOTES_DB, null, NOTES_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTES);
        Log.d("database", "Create database succeed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void initDb(Context context) {
        if (null == instance) {
            instance = new NotesDatabase(context);
        }
    }

    public static SQLiteDatabase getDatabase() {
        if (database== null) {
            database = instance.getWritableDatabase();
        }
        return database;
    }

    //关闭数据库
    public static void closeDb() {
        if (null != database && database.isOpen()) {
            database.close();

        }
        database = null;
        instance = null;
    }

    public static long addNotes(NoteInfo note) {
        int remindTime = note.getTime();
        String content = note.getContent();
        ContentValues values = new ContentValues();
        values.put(NOTES_TIME, remindTime);
        values.put(NOTES_CONTENT, content);
        return getDatabase().insert(NOTES_TABLE, null, values);
    }

    public static List<NoteInfo> getAll() {
        List<NoteInfo> notesList = new ArrayList<>();
        Cursor cursor = getDatabase().query("notes", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(NOTES_ID));
                String remindTime = cursor.getString(cursor.getColumnIndex(NOTES_TIME));
                String content = cursor.getString(cursor.getColumnIndex(NOTES_CONTENT));
                NoteInfo note = new NoteInfo(Integer.valueOf(remindTime), content);
                note.setId(id);
                notesList.add(note);
            }
            cursor.close();
        }
        return notesList;
    }

    public static int deleteNotes(int id) {
        return getDatabase().delete(NOTES_TABLE, NOTES_ID + "=?", new String[]{id + ""});
    }

    public static int deleteNotes(NoteInfo note) {
        return getDatabase().delete(NOTES_TABLE, NOTES_ID + "=?", new String[]{note.getId() + ""});
    }


}
