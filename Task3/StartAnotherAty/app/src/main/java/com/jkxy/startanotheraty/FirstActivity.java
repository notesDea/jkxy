package com.jkxy.startanotheraty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        editText = (EditText) findViewById(R.id.editText);

        findViewById(R.id.btnStartAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                if (editText.getText().toString().equals("")) {
                    intent.putExtra("data", "null");
                } else {
                    intent.putExtra("data", editText.getText().toString());
                }
                startActivity(intent);
            }
        });
    }
}
