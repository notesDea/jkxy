package com.jkxy.chapter13;

import java.lang.Object;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by dea on 16-7-1.
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        GeometricObject x = new GeometricObject() {
            @Override
            public double getArea() {
                return 0;
            }

            @Override
            public double getPerimeter() {
                return 0;
            }
        };
//        GeometricObject y = (GeometricObject) x.clone();
//        System.out.println(x == y);

        Rectangle rectangle = new Rectangle(5, 5);

        Rectangle rectangle1 = (Rectangle) rectangle.clone();
        rectangle1.getArea();




        House house = new House(1, 5000);
        try {
            House house1 = (House) house.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
