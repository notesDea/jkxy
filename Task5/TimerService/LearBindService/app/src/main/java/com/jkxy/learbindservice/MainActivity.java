package com.jkxy.learbindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jkxy.serviceserviceservice.IMyService;
import com.jkxy.serviceserviceservice.TimerServiceCallback;


//客户端
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "aidl";
    private Button btnBindService, btnUnbindService;
    private TextView tvCount;
    private IMyService binder = null;
    private Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new Intent();
        service.setComponent(new ComponentName("com.jkxy.serviceserviceservice",
                "com.jkxy.serviceserviceservice.MyService"));
        initView();
        initListener();

    }

    private void initView() {
        tvCount = (TextView) findViewById(R.id.tvCount);
        btnBindService = (Button) findViewById(R.id.btnBindService);
        btnUnbindService = (Button) findViewById(R.id.btnUnbindService);
    }

    private void initListener() {
        //绑定服务
        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(service, conn, Context.BIND_AUTO_CREATE);
            }
        });

        //解除绑定服务
        btnUnbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder != null) {
                  	callUnregist;
                    unbindService(conn);
                    binder = null;
                }
            }
        });
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IMyService.Stub.asInterface(service);
            try {
                binder.registCallback(onTimeService);
            } catch (RemoteException e) {
                 e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            callUnregist();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        callUnregist();
    }

    private void callUnregist() {
        try {
            binder.unregistCallback(onTimeService);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private TimerServiceCallback.Stub onTimeService = new TimerServiceCallback.Stub() {
        @Override
        public void onTimer(int num) throws RemoteException {
            //发送消息
            Message msg = new Message();
            msg.arg1 = num;
            msg.obj = MainActivity.this;
            handler.sendMessage(msg);
        }
    };

    private final MyHandler handler = new MyHandler();


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int num = msg.arg1;
            MainActivity aty = (MainActivity) msg.obj;
            aty.tvCount.setText(getString(R.string.value_text, num));
        }
    }
}
