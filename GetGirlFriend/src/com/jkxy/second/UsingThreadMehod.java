package com.jkxy.second;

/**
 * Created by dea on 16-7-6.
 */
public class UsingThreadMehod {

    public static void main(String[] args) throws InterruptedException {

        boolean isExecutable = false;
        for (int i = 1; i <= 3000; i++) {

            if (i > 1000) {
                if (!isExecutable) {

                    System.out.println("start sleeping");
                    Thread.sleep(5000);
                    System.out.println("end sleeping");

                    Thread thread2 = new MyThread1();
                    thread2.start();
                    thread2.join();
                    isExecutable = true;
                }
            }
            System.out.println("thread1 执行第 " + i + "次");
        }
    }
}
