package com.jkxy.second;

/**
 * Created by dea on 16-7-4.
 */
public class MyCustomException extends Exception {

    public static void main(String[] args) {
        try {
            throw new MyCustomException("自定义异常");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public MyCustomException(String msg) {
        super(msg);
    }
}
