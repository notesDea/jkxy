package com.notesdea.lockscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //负责管理锁屏权限
    private ManageLockScreenAdmin mManageLockScreenAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        if (mManageLockScreenAdmin.isAdminEnabled()) {
            mManageLockScreenAdmin.lockScreen();
            finish();
        } else {
            startAnotherAty(SettingAdminActivity.class);
            finish();
        }
    }

    private void initData() {
        mManageLockScreenAdmin = new ManageLockScreenAdmin(this);
    }

    //打开另一个活动
    private void startAnotherAty(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
