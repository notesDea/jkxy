package com.jkxy.innerclass;

/**
 * Created by dea on 16-7-3.
 */
public class Main {
    public static void main(String[] args) {
        //method 1: static
//        Outer.Inner = new Outer.Inner();

        // method 2:
        Outer.Inner inner3 = new Outer().inner;
        inner3.print();

        // method3: non-static
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.print();
    }
}
