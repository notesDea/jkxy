package com.notesdea.speedup;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "CleanUp";

    private ActivityManager mActivityManager;

    //1MB = 1024 * 1024 B
    public final static long ONE_MB = 1024 * 1024;

    //需要请求的权限
    private String[] permissions = {Manifest.permission.KILL_BACKGROUND_PROCESSES};
    //杀死进程的权限请求码
    private static final int REQUEST_CODE_KILL_PROCESS_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        if (isNeedDynamicPermissions()) {
            int killProcessPermission = checkSelfPermission(permissions[0]);
            if (!isPermissionGranted(killProcessPermission)) {
                requestPermissions( permissions, REQUEST_CODE_KILL_PROCESS_PERMISSION);
            } else {
                cleanUpMemory();
            }
        } else {
            cleanUpMemory();
        }
    }

    //判断是否需要动态权限
    private boolean isNeedDynamicPermissions() {
        return Build.VERSION.SDK_INT >= 23;
    }

    //判断请求的权限是否已通过
    private boolean isPermissionGranted(int requestPermission) {
        return requestPermission == PackageManager.PERMISSION_GRANTED;
    }

    //清理内存
    private void cleanUpMemory() {
        long beginCleanUpMem = getAvailMemory();
        List<ActivityManager.RunningAppProcessInfo> appProcesses
                =  mActivityManager.getRunningAppProcesses();

        killProcesses(appProcesses);

        long afterCleanUpMem = getAvailMemory();
        long cleanedUpMem = afterCleanUpMem - beginCleanUpMem;
        Log.d(TAG, "清理了" + cleanedUpMem + "M内存");
        showToast("清理了" + cleanedUpMem + "M内存");
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_KILL_PROCESS_PERMISSION:
                if (isPermissionGranted(grantResults[0]))
                    cleanUpMemory();
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    /**
     * 返回可用内存
     * @return 返回以 MB 为单位的内存
     */
    private long getAvailMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        mActivityManager.getMemoryInfo(memoryInfo);
        long availMem = memoryInfo.availMem / ONE_MB;
        Log.d(TAG, "可用内存为：" + availMem);
        return availMem;
    }

    //杀死 List 列表中所有进程
    private void killProcesses(List<ActivityManager.RunningAppProcessInfo> appProcesses) {
        if (appProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : appProcesses) {
                killProcess(processInfo);
            }
        }
    }

    //杀死指定进程
    private void killProcess(ActivityManager.RunningAppProcessInfo processInfo) {
        if (isExpendableProcess(processInfo)) {
            String[] pkgList = processInfo.pkgList;
            for (String packageName : pkgList) {
                mActivityManager.killBackgroundProcesses(packageName);
                Log.d(TAG, "包名： " + packageName + ", 重要程度：" + processInfo.importance);
            }
        }
    }

    //判断线程是否可牺牲
    private boolean isExpendableProcess(ActivityManager.RunningAppProcessInfo processInfo) {
        return processInfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE;
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
