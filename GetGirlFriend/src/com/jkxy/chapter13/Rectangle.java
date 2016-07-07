package com.jkxy.chapter13;

/**
 * Created by dea on 16-6-30.
 */
public class Rectangle extends GeometricObject implements Comparable<Rectangle> {
    private double width, height;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height, String color, boolean filled) {
        this.width = width;
        this.height = height;
        setColor(color);
        setFilled(filled);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public int compareTo(Rectangle o) {
        if (getArea() > o.getArea()) {
            return 1;
        } else if (getArea() == o.getArea()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Width: " + width + " Height: " + height +" Area: " + getArea();
    }
}
