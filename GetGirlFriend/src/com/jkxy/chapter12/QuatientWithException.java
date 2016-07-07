package com.jkxy.chapter12;

import java.util.Scanner;

/**
 * Created by dea on 16-7-4.
 */
public class QuatientWithException {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter two numbers: ");
        int number1 = scanner.nextInt();
        int number2 = scanner.nextInt();

        quatient(number1, number2);
    }

    public static void quatient(int number1, int number2) {
        if (number2 == 0) {
            throw new ArithmeticException("The denominator is not zero");
        }
        System.out.println(number1 / number2);
    }
}
