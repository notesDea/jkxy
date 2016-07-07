package com.jkxy.chapter13;

/**
 * Created by dea on 16-7-2.
 */
public class TestComparableRectangle {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5, 5);
        Rectangle rectangle1 = new Rectangle(5, 5);
        System.out.println(rectangle.compareTo(rectangle1));
    }
}
