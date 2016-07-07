package com.jkxy.second;

/**
 * Created by dea on 16-7-4.
 */
public class UsingException {
    public static void main(String[] args) {
        int number1 = 5;
        int number2 = 0;
        int result;

        try {
            result = number1 / number2;
        } catch (Exception e) {
            System.out.println("算数错误");
        } finally {
            System.out.println("End");
        }
    }
}
