package com.jkxy.second;

/**
 * Created by dea on 16-7-6.
 */
public class UsingThread {

    public static void main(String[] args) {

//        Thread myThread = new MyThread1();
//        myThread.start();


        MyThread2 myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2);
        thread.start();
    }
}
