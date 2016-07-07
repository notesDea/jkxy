package com.jkxy.chapter13;

import java.util.Date;

/**
 * Created by dea on 16-7-2.
 */
/*public class House implements Cloneable, Comparable<House> {
    private int id;
    private double area;
    private Date whenBuilt;

    public House(int id, double area) {
        this.id = id;
        this.area = area;
        whenBuilt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Date getBuiltDate() {
        return whenBuilt;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
//        return super.clone();         //shallow clone

        //deep clone
        try {
// Perform a shallow copy
            House houseClone = (House)super.clone();
// Deep copy on whenBuilt
            houseClone.whenBuilt = (java.util.Date)(whenBuilt.clone());
            return houseClone;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
//        House cloneHouse = (House) super.clone();
//        cloneHouse.whenBuilt = (Date) whenBuilt.clone();
//        return cloneHouse;

    }

    @Override
//    compare
    public int compareTo(House o) {
        if (area > o.area) {
            return 1;
        } else if (area == o.area) {
            return 0;
        } else {
            return -1;
        }
    }


}*/


public class House implements Cloneable, Comparable<House> {
    private int id;
    private double area;
    private java.util.Date whenBuilt;

    public House(int id, double area) {
        this.id = id;
        this.area = area;
        whenBuilt = new java.util.Date();
    }

    public int getId() {
         return id;
    }
    public double getArea() {
        return area;
    }

    public java.util.Date getWhenBuilt() {
        return whenBuilt;
    }

     @Override /** Override the protected clone method defined in
     25 the Object class, and strengthen its accessibility */
     public Object clone() throws CloneNotSupportedException {
         return super.clone();
     }

     @Override // Implement the compareTo method defined in Comparable
     public int compareTo(House o) {
         if (area > o.area)
             return 1;
         else if (area < o.area)
             return -1;
         else
         return 0;
     }
}