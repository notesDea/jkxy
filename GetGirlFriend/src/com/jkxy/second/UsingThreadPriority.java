package com.jkxy.second;

/**
 * Created by dea on 16-7-6.
 */
public class UsingThreadPriority {

    public static void main(String[] args) {
        Runnable1 runnable1 = new Runnable1();
        Runnable2 runnable2 = new Runnable2();

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.setPriority(5);
        thread2.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
    }
}

class Runnable1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Runnable1");
        }
   }
}

class Runnable2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Runnable2");
        }
    }
}
