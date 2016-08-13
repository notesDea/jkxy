package com.jkxy.startexamplerapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String PERMISSION_EXAMPLE_APP = "com.jkxy.examplerapp.permission.START_ATY";
    private Button btnStartAty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartAty = (Button) findViewById(R.id.btnStartAty);
        btnStartAty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.jkxy.examplerapp.intent.action.MainActivity");
                if (checkCallingOrSelfPermission(PERMISSION_EXAMPLE_APP) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.start_faild, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
