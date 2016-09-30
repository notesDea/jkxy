package com.notesdea.lockscreen;


import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SettingAdminActivity extends AppCompatActivity implements View.OnClickListener {

    //获取管理者权限按钮
    private Button btnGetDeviceAdmin,
            //取消管理者权限按钮
            btnCancelDeviceAdmin,
            //锁屏按钮
            btnLockScreen;
    //负责管理锁屏权限
    private ManageLockScreenAdmin mManageLockScreenAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_device_admin);
        initData();
        initView();
        initListener();
    }

    private void initData() {
        mManageLockScreenAdmin = new ManageLockScreenAdmin(this);
    }

    private void initView() {
        btnGetDeviceAdmin = (Button) findViewById(R.id.btn_settingdeviceadmin_getadmin);
        btnCancelDeviceAdmin = (Button) findViewById(R.id.btn_settingdeviceadmin_canceladmin);
        btnLockScreen = (Button) findViewById(R.id.btn_lockscreen);

        //如果权限已允许，显示对应的视图
        if (mManageLockScreenAdmin.isAdminEnabled()) {
            showGotView();
        } else {
            showUngetView();
        }
    }

    //显示权限已允许时显示的视图
    private void showGotView() {
        btnGetDeviceAdmin.setVisibility(View.INVISIBLE);
        btnCancelDeviceAdmin.setVisibility(View.VISIBLE);
        btnLockScreen.setVisibility(View.VISIBLE);
    }

    //显示权限未允许时对应的视图
    private void showUngetView() {
        btnGetDeviceAdmin.setVisibility(View.VISIBLE);
        btnCancelDeviceAdmin.setVisibility(View.INVISIBLE);
        btnLockScreen.setVisibility(View.INVISIBLE);
    }

    private void initListener() {
        //获取管理者权限
        btnGetDeviceAdmin.setOnClickListener(this);
        //取消管理者权限
        btnCancelDeviceAdmin.setOnClickListener(this);
        //执行锁屏操作
        btnLockScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_settingdeviceadmin_getadmin:
                mManageLockScreenAdmin.requestDeviceAdmin(this);
                break;
            case R.id.btn_settingdeviceadmin_canceladmin:
                mManageLockScreenAdmin.cancelDeviceAdmin();
                showUngetView();
                break;
            case R.id.btn_lockscreen:
                mManageLockScreenAdmin.lockScreen();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ManageLockScreenAdmin.REQUEST_CODE_SETTING_ADMIN:
                if (resultCode == RESULT_OK) {
                    showGotView();
                } else {
                    showUngetView();
                }
                break;
        }
    }
}
