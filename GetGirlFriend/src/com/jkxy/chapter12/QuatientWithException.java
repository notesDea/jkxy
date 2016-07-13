package com.jkxy.chapter12;

import java.util.Scanner;

/**
 * Created by dea on 16-7-4.
 */
public class QuatientWithException {

    public static int quotient(int number1, int number2) {
        if (number2 == 0) {
            throw new ArithmeticException("Divisor cannot be zero");
        }

        return number1 / number2;
    }

    public static void main(String[] args) {
        try {
            int result1 = quotient(3, 2);

            System.out.println(result1);
        } catch (Exception e) {
            System.out.println("Exception: an integer cannot be divided by zero");
        } finally {
            System.out.println("Execution continue");
        }

        try {
            int result2 = quotient(3, 0);

            System.out.println(result2);
        } catch (Exception e) {
            System.out.println("Exception: an integer cannot be divided by zero");
        } finally {
            System.out.println("Execution continue");
        }

    }
}
