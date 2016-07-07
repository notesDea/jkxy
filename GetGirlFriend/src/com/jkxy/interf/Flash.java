package com.jkxy.interf;

/**
 * Created by dea on 16-7-1.
 */
public class Flash implements USB {

    @Override
    public void start() {
        System.out.println("Flash start working");
    }

    @Override
    public void stop() {
        System.out.println("Flash stop working");
    }
}
