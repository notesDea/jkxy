package com.jkxy.chapter13;

/**
 * Created by dea on 16-7-2.
 */
public interface Edible {
    String howToEat();

    public static void eat() {
        System.out.println("eating..");
    }
}
