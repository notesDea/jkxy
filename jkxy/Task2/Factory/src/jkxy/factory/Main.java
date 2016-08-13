package jkxy.factory;


/**
 * Created by dea on 16-7-8.
 */
public class Main {
    public static void main (String[] args) {
        Vehicle car = Factory.getVehicle("Car");
        Vehicle bus = Factory.getVehicle("Bus");
        Vehicle sportsCar = Factory.getVehicle("SportsCar");

        try {
            car.setColor("White");
            car.setPrice(100000);
            System.out.println("Car price is " + car.getColor());
            System.out.println(car.getPrice());
            System.out.println(car.run());
        } catch (Exception e) {
            System.out.println("No such vehicle");
        }

        System.out.println();

        try {
            bus.setPrice(200000);
            System.out.println("Bus price is " + bus.getPrice());
            System.out.println(bus.getColor());
            bus.run();
        } catch (Exception e) {
            System.out.println("No such vehicle");
        }

        try {
            sportsCar.setPrice(3000000);
            sportsCar.setColor("Yellow");
            System.out.println("SportCar price is " + sportsCar.getPrice());
            System.out.println(sportsCar.getColor());
            System.out.println(sportsCar.run());
        } catch (Exception e) {
            System.out.println("No such vehicle");
        }


    }
}
