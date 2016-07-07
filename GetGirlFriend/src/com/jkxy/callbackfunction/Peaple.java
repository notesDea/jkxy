package com.jkxy.callbackfunction;

/**
 * Created by dea on 16-7-7.
 */
public class Peaple {
    private String name;
    private IRunningCallbackListener iRunningCallbackListener;

    public Peaple(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("Someone is running");
        if (iRunningCallbackListener != null) {
            iRunningCallbackListener.running(this.name);
        }

    }

    public void setiRunningCallbackListener(IRunningCallbackListener iRunningCallbackListener) {
        this.iRunningCallbackListener = iRunningCallbackListener;
    }

    public IRunningCallbackListener getiRunningCallbackListener() {
        return iRunningCallbackListener;
    }

}
