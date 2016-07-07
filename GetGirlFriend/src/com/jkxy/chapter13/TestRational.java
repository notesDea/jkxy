package com.jkxy.chapter13;

/**
 * Created by dea on 16-7-3.
 */
public class TestRational {
    public static void main(String[] args) {
        Rational rational = new Rational(-3, 5);
        System.out.println(rational);
        Rational rational1 = rational.add(new Rational(10, 5));
        System.out.println(rational1);
        System.out.println(rational.doubleValue());
        System.out.println(rational.floatValue());
    }
}
