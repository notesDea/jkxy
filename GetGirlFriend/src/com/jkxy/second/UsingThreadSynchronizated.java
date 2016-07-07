package com.jkxy.second;

/**
 * Created by dea on 16-7-6.
 */
public class UsingThreadSynchronizated extends Thread {

    private String name;
    private Object obj;

    public UsingThreadSynchronizated(Object obj, String name) {
        this.name = name;
        this.obj = obj;
    }

    @Override
    public void run() {

        synchronized (obj) {
            for (int i = 1; i < 1001; i++) {
                System.out.println("线程" + name + "执行了第 " + i + " 次");
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        for (int i = 1; i < 6; i++) {
            new  UsingThreadSynchronizated(obj, i + "").start();
            Thread.sleep(1);
        }
    }
}
