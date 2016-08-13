package com.jkxy.makeavow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //接收愿望的请求码
    static final int VOW_REQUEST = 1;
    private TextView tvVow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvVow = (TextView) findViewById(R.id.tv_vow);

        findViewById(R.id.btn_startaty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessageInputAty.class);
                startActivityForResult(intent, VOW_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOW_REQUEST) {
            if (resultCode == RESULT_OK) {
                String vow = "您今天的愿望是：\n" + data.getStringExtra("return_vow");
                tvVow.setText(vow);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "您取消了许愿操作", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
