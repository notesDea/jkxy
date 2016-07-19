package com.jkxy.checkanswer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioButton rdoBtnMale, rdoBtnFemale;
    private CheckBox chkA, chkB, chkC, chkD, chkE, chkF;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void showResult() {
        tvResult.setText("您选择的性别：" + showSex() + "，" + showChkResult());
    }

    public String showSex() {
        if (rdoBtnMale.isChecked()) {
            return "男";
        } else {
            return "女";
        }
    }

    public String showChkResult() {
        if (!chkA.isChecked() && !chkB.isChecked() && !chkD.isChecked() && !chkE.isChecked()) {
            if (chkC.isChecked() && chkF.isChecked()) {
                return "多选题选择正确";
            }

            if (!chkC.isChecked() && !chkF.isChecked()) {
                return "多选题还没有进行选择";
            }
        }
        return "多选题选择错误";
    }


    private void initView() {
        rdoBtnMale = (RadioButton) findViewById(R.id.rdoBtn_male);
        rdoBtnFemale = (RadioButton) findViewById(R.id.rdoBtn_female);
        tvResult = (TextView) findViewById(R.id.tv_result);
        chkA = (CheckBox) findViewById(R.id.chk_a);
        chkB = (CheckBox) findViewById(R.id.chk_b);
        chkC = (CheckBox) findViewById(R.id.chk_c);
        chkD = (CheckBox) findViewById(R.id.chk_d);
        chkE = (CheckBox) findViewById(R.id.chk_e);
        chkF = (CheckBox) findViewById(R.id.chk_f);
    }

    private void initListener() {
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult();
            }
        });

    }
}
