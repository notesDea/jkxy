package jkxy.factory;

/**
 * Created by dea on 16-7-7.
 */
public class Factory {

    public static Vehicle getVehicle(String type) {
        if (type.equals("Car")) {
            return new Car();
        } else if (type.equals("SportsCar")) {
            return new SportsCar();
        } else if (type.equals("Bus")) {
            return new Bus();
        } else {
            System.out.println("No such vehicle");
            return null;
        }
    }
}
