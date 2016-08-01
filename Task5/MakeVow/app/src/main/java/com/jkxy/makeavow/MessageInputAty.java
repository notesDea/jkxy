package com.jkxy.makeavow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MessageInputAty extends AppCompatActivity {

    private EditText etWriteVow;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_input_aty);
        etWriteVow = (EditText) findViewById(R.id.et_writevow);

        //提交操作
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etWriteVow.getText().toString().equals("")) {
                    Toast.makeText(MessageInputAty.this, "愿望不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("return_vow", etWriteVow.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        //取消操作
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
