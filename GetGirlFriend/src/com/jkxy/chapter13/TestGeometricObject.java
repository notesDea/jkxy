package com.jkxy.chapter13;

/**
 * Created by dea on 16-6-30.
 */
public class TestGeometricObject {

    public static void main(String[] args) {


        GeometricObject circle = new Circle(5);
        GeometricObject rectangle = new Rectangle(5, 3);

        System.out.println("The two objects have the same area? " + equalArea(circle, rectangle));

        System.out.println("Circle: ");
        displayGemetricObject(circle);

        System.out.println("Rectangle");
        displayGemetricObject(rectangle);
    }

    public static boolean equalArea(GeometricObject object1,
                                    GeometricObject object2) {
        return object1.getArea() == object2.getArea();
    }

    public static void displayGemetricObject(GeometricObject object) {
        System.out.println();
        System.out.println("The area is " + object.getArea());
        System.out.println("The perimeter is " + object.getPerimeter());
    }
}
