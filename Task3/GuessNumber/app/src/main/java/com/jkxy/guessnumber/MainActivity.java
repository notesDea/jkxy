package com.jkxy.guessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    int guess, result;
    private EditText etGuess;
    private TextView tvPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializationView();

        findViewById(R.id.btnCommit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etGuess.getText().toString().matches("\\d{1,}") &&
                        isInRange(Integer.valueOf(etGuess.getText().toString()), 0, 100)) {
                    guess = Integer.valueOf(etGuess.getText().toString());
                    if (guess < result) {
                        tvPrompt.setText("输入的数字太小了");
                    } else if (guess > result) {
                        tvPrompt.setText("输入的数字太大了");
                    } else {
                        tvPrompt.setText("正确");
                    }
                } else {
                    tvPrompt.setText("请输入0-100的数字");
                }

            }
        });

        findViewById(R.id.btnAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = random.nextInt(101);
            }
        });
    }

    private void initializationView() {
        etGuess = (EditText) findViewById(R.id.etGuess);
        tvPrompt = (TextView) findViewById(R.id.tvPrompt);
        result = random.nextInt(101);
    }

    public boolean isInRange(int num, int beginIndex, int endIndex) {
        return  num >= beginIndex && num <= endIndex;
    }
}
