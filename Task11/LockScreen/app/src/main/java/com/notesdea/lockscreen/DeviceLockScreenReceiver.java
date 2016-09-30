package com.notesdea.lockscreen;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by notes on 2016/9/23.
 */

public class DeviceLockScreenReceiver extends DeviceAdminReceiver {

    //当激活管理者权限时调用
    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        showToast(context, context.getString(R.string.admin_receiver_status_enabled));
    }

    //当取消管理者权限时调用
    @Override
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        showToast(context, context.getString(R.string.admin_receiver_status_disabled));
    }

    private void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
