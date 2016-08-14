package com.jkxy.note;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jkxy.note.service.RemindTimeReceiver;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    public static final int RETURN_RESULT_REQUEST = 1;      //添加笔记时的requestCode
    private ListView listView;
    private ArrayAdapter<NoteInfo> adapter;
    private List<NoteInfo> notesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        initListener();

        NotesDatabase.initDb(MainActivity.this);
        notesList = NotesDatabase.getAll();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listView.setAdapter(adapter);



    }

    private void initListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog deleteDialog = deleteDialog(position);
                deleteDialog.show();
                return true;
            }
        });
    }

    private Dialog deleteDialog(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.system_tip))
                .setMessage(getString(R.string.delete_data_tip))
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //取消提醒
                        NoteInfo note = notesList.get(position);
                        Log.d("note", note.getTime() + "=============" + note.getContent());
                        Intent receiver = new Intent(MainActivity.this, RemindTimeReceiver.class);
                        receiver.setAction(RemindTimeReceiver.ALARM_RECEIVER_ACTION);
                        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this,
                                (int) note.getId(), receiver, PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        alarmManager.cancel(pi);

                        //根据Id，删除数据库和列表的笔记
                        NotesDatabase.initDb(MainActivity.this);
                        NotesDatabase.deleteNotes((int) notesList.get(position).getId());
                        notesList.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.create().dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    //菜单栏

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //启动添加笔记Activity
            case R.id.itemAdd:
                intent = new Intent(MainActivity.this, AddNotesActivity.class);
                startActivityForResult(intent, RETURN_RESULT_REQUEST);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //接收添加事件传回来的笔记数据
            case RETURN_RESULT_REQUEST:
                if (resultCode == RESULT_OK) {
                    String remindTime = data.getStringExtra("remindTime");
                    String content = data.getStringExtra("content");
                    NoteInfo note = new NoteInfo(Integer.valueOf(remindTime), content);
                    NotesDatabase.initDb(MainActivity.this);
                    //添加笔记到数据库并设置ID
                    note.setId(NotesDatabase.addNotes(note));
                    notesList.add(note);
                    adapter.notifyDataSetChanged();

                    //开启笔记提醒
                    note.scheduleAlarm(MainActivity.this);
                    Log.d("Alarm", "开启提醒");

                } else {
                    Toast.makeText(MainActivity.this, "取消添加", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        NotesDatabase.closeDb();
    }


}
