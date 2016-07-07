package com.jkxy.interf;

/**
 * Created by dea on 16-7-1.
 */
public class Computer {
    public void plugin(USB usb) {
        usb.start();
        System.out.println("USB start working");
        usb.stop();
    }
}
