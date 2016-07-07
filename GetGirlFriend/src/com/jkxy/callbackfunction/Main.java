package com.jkxy.callbackfunction;

/**
 * Created by dea on 16-7-7.
 */
public class Main {

    public static void main(String[] args) {
        Peaple peaple = new Peaple("邹俊达");
        peaple.setiRunningCallbackListener(listener);
        peaple.run();
    }

    private static IRunningCallbackListener listener = new IRunningCallbackListener() {
        @Override
        public void running(String name) {
            System.out.println(name + " is running");
        }
    };
}
