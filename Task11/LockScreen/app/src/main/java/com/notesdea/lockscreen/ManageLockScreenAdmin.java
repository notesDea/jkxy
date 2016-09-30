package com.notesdea.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by notes on 2016/9/24.
 */

public class ManageLockScreenAdmin {
    //请求 Admin 权限的请求码
    public static final int REQUEST_CODE_SETTING_ADMIN = 100;
    //DeviceLockScreenReceiver 的组件名
    private ComponentName mComponentName;
    private DevicePolicyManager mDevicePolicyManager;

    public ManageLockScreenAdmin(Context context) {
        mComponentName = new ComponentName(context, DeviceLockScreenReceiver.class);
        mDevicePolicyManager = (DevicePolicyManager) context. getSystemService(Context.DEVICE_POLICY_SERVICE);
    }

    //获取 DeviceLockScreenReceiver 的组件名
    public ComponentName getComponentName() {
        return mComponentName;
    }

    //获取 DevicePolicyManager 对象
    public DevicePolicyManager getDevicePolicyManager() {
        return mDevicePolicyManager;
    }

    //判断管理是否已允许
    public boolean isAdminEnabled() {
        return mDevicePolicyManager.isAdminActive(mComponentName);
    }

    //锁屏操作
    public void lockScreen() {
        mDevicePolicyManager.lockNow();
    }

    //请求管理者权限
    public void requestDeviceAdmin(SettingAdminActivity activity) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
        activity.startActivityForResult(intent, REQUEST_CODE_SETTING_ADMIN);
    }

    //取消管理者权限
    public void cancelDeviceAdmin() {
        mDevicePolicyManager.removeActiveAdmin(mComponentName);
    }
}
