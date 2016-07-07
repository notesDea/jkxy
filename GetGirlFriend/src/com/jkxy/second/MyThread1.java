package com.jkxy.second;

/**
 * Created by dea on 16-7-6.
 */
public class MyThread1 extends Thread {

    public MyThread1() {
        super();
    }

    public MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 150; i++) {
            System.out.println("This is MyThread1");
        }
    }
}
