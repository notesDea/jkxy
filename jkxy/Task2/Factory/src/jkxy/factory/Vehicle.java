package jkxy.factory;

/**
 * Created by dea on 16-7-8.
 */
public abstract class Vehicle {
    private String color = "Blue";
    private int price;

    public Vehicle() {
    }

    public Vehicle(String color, int price) {
        this.color = color;
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract String run();
}
