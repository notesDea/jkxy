package com.jkxy.innerclass;

/**
 * Created by dea on 16-7-4.
 */
public class BClass {

    public void tell() {
        call(new Inside() {
            @Override
            public void print() {
                System.out.println("don't using");
            }
        });
    }

    public void call(Inside i) {
        i.print();
    }
}
