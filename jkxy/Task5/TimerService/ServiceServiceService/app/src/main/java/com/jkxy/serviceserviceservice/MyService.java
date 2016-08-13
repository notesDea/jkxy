package com.jkxy.serviceserviceservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    public static final String TAG = "aidl";
    private boolean canRun;
    private RemoteCallbackList<TimerServiceCallback> callbackList = new RemoteCallbackList<>();

    public class MyServiceImpl extends IMyService.Stub {

        @Override
        public void registCallback(TimerServiceCallback callback) throws RemoteException {
            callbackList.register(callback);
        }

        @Override
        public void unregistCallback(TimerServiceCallback callback) throws RemoteException {
            callbackList.unregister(callback);
        }

        @Override
        public void isRun(boolean canRun) throws RemoteException {
            MyService.this.canRun = canRun;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceImpl();
    }

    @Override
    public void onCreate() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                canRun = true;
                for (int i = 0; canRun; i++) {
                    int count = callbackList.beginBroadcast();

                    while (count-- > 0) {
                        try {
                            callbackList.getBroadcastItem(count).onTimer(i);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    callbackList.finishBroadcast();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        canRun = false;
        Log.d(TAG, "service Destroy");
    }
}
