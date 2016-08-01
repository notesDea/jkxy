package com.jkxy.simplecaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected EditText etDisplay;
    protected Button[] numbers = new Button[16];
    private String expression = "";
    private boolean isCalResults = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;

        switch (btn.getId()) {
            case R.id.btn_zero:
            case R.id.btn_one:
            case R.id.btn_two:
            case R.id.btn_three:
            case R.id.btn_four:
            case R.id.btn_five:
            case R.id.btn_six:
            case R.id.btn_seven:
            case R.id.btn_eight:
            case R.id.btn_nine:
                //判断当前是否是计算结果
                if (isCalResults) {
                    expression = "";
                }
                expression += btn.getText().toString();
                etDisplay.setText(expression);
                isCalResults = false;
                break;

            case R.id.btn_plus:
            case R.id.btn_subtract:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                expression += btn.getText().toString();
                etDisplay.setText(expression);
                isCalResults = false;
                break;

            case R.id.btn_clear:
                expression = "";
                etDisplay.setText("0");
                isCalResults = false;
                break;

            case R.id.btn_equal:
                try {
                    expression = Calculate.calculator(expression);
                    etDisplay.setText(expression);
                    isCalResults = true;
                } catch (Exception e) {
                    etDisplay.setText("表达式错误！");
                    expression = "";
                }
                break;

            default:
                break;
        }
    }

    private void initView() {
        etDisplay = (EditText) findViewById(R.id.et_display);
        numbers[0] = (Button) findViewById(R.id.btn_zero);
        numbers[1] = (Button) findViewById(R.id.btn_one);
        numbers[2] = (Button) findViewById(R.id.btn_two);
        numbers[3] = (Button) findViewById(R.id.btn_three);
        numbers[4] = (Button) findViewById(R.id.btn_four);
        numbers[5] = (Button) findViewById(R.id.btn_five);
        numbers[6] = (Button) findViewById(R.id.btn_six);
        numbers[7] = (Button) findViewById(R.id.btn_seven);
        numbers[8] = (Button) findViewById(R.id.btn_eight);
        numbers[9] = (Button) findViewById(R.id.btn_nine);
        numbers[10] = (Button) findViewById(R.id.btn_plus);
        numbers[11] = (Button) findViewById(R.id.btn_subtract);
        numbers[12] = (Button) findViewById(R.id.btn_multiply);
        numbers[13] = (Button) findViewById(R.id.btn_divide);
        numbers[14] = (Button) findViewById(R.id.btn_equal);
        numbers[15] = (Button) findViewById(R.id.btn_clear);
    }

    private void initListener() {
        numbers[0].setOnClickListener(this);
        numbers[1].setOnClickListener(this);
        numbers[2].setOnClickListener(this);
        numbers[3].setOnClickListener(this);
        numbers[4].setOnClickListener(this);
        numbers[5].setOnClickListener(this);
        numbers[6].setOnClickListener(this);
        numbers[7].setOnClickListener(this);
        numbers[8].setOnClickListener(this);
        numbers[9].setOnClickListener(this);
        numbers[10].setOnClickListener(this);
        numbers[11].setOnClickListener(this);
        numbers[12].setOnClickListener(this);
        numbers[13].setOnClickListener(this);
        numbers[14].setOnClickListener(this);
        numbers[15].setOnClickListener(this);

    }
}
