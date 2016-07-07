package com.jkxy.innerclass;

/**
 * Created by dea on 16-7-3.
 */
public class Outer {
    private String str = "hello world!";
    public Inner inner = new Inner();

    class Inner {
        public void print() {
            System.out.println(str);
        }
    }

    public void tell() {
        new Inner().print();
    }
}
