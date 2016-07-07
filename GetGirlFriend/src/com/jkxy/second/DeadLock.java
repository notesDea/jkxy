package com.jkxy.second;

/**
 * Created by dea on 16-7-6.
 */
public class DeadLock {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        MyRunnable myRunnable1 = new MyRunnable(o1, o2, "A", 3000);
        MyRunnable myRunnable2 = new MyRunnable(o2, o1, "B", 300);

        new Thread(myRunnable1).start();
        new Thread(myRunnable2).start();
    }
}

class MyRunnable implements Runnable {

    private Object o1, o2;
    private int dur;
    private String name;

    public MyRunnable(Object o1, Object o2, String name, int dur) {
        this.o1 = o1;
        this.o2 = o2;
        this.name = name;
        this.dur = dur;
    }

    @Override
    public void run() {

        synchronized (o1) {
            try {
                Thread.sleep(dur);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + name);

            synchronized (o2) {
                System.out.println("线程——————" + name);
            }
        }
    }
}

