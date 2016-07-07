package com.jkxy.interf;

/**
 * Created by dea on 16-7-1.
 */
public class TestUSB {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Flash flash = new Flash();
        Computer computer = new Computer();

        computer.plugin(printer);
        System.out.println();
        computer.plugin(flash);
    }
}
