package com.jkxy.chapter13;

import java.util.Date;
import java.lang.Object;

/**
 * Created by dea on 16-6-30.
 */
public abstract class GeometricObject implements Cloneable{
    private String color = "white";
    private Date createdDate;
    private boolean filled;

    protected GeometricObject() {
        createdDate = new Date();
    }

    protected GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "created on " + createdDate + "\nlocor: " + color + " and filled " + filled;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
