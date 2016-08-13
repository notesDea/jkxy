package com.jkxy.launchlocalappfrombrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLaunchLocalAty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLaunchLocalAty = (Button) findViewById(R.id.btnLaunchLocalAty);
        btnLaunchLocalAty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.jkxy.localappactivity.intent.action.MainActivity");
                startActivity(intent);

            }
        });
    }
}
