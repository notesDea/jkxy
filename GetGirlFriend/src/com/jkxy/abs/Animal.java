package com.jkxy.abs;

/**
 * Created by dea on 16-7-1.
 */
public abstract class Animal {
    private int age = 10;

    public abstract void call();

    public void tell() {
        System.out.println("Tell you");
    }
}
