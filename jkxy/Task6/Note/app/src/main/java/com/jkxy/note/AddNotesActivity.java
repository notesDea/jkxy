package com.jkxy.note;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * 添加笔记的Activity
 */

public class AddNotesActivity extends AppCompatActivity {
    private EditText etRemindTIme, etContent;
    private Button btnSave;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        initView();
        initListener();
    }

    private void initView() {
        etRemindTIme = (EditText) findViewById(R.id.etRemindTime);
        etContent = (EditText) findViewById(R.id.etContent);
        btnSave = (Button) findViewById(R.id.btnSave);

    }

    private void initListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remindTime = etRemindTIme.getText().toString();
                String content = etContent.getText().toString();
                if ("".equals(remindTime) || "".equals(content)) {
                    Toast.makeText(AddNotesActivity.this,
                            "时间或笔记内容不能为空", Toast.LENGTH_SHORT).show();
                } else if (!remindTime.matches("([01]\\d)|(2[0-4])|\\d")) {
                    Toast.makeText(AddNotesActivity.this,
                            "时间格式不正确", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent();
                    intent.putExtra("remindTime", remindTime);
                    intent.putExtra("content", content);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(AddNotesActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
