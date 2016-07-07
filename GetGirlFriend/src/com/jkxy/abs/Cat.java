package com.jkxy.abs;

/**
 * Created by dea on 16-7-1.
 */
public class Cat extends Animal {

    public static void main(String[] args) {
        Animal animal = new Animal() {
            @Override
            public void call() {
                System.out.println("call");
            }
        };
        animal.call();

        Cat cat = new Cat();
        cat.call();
    }

    @Override
    public void call() {
        System.out.println("Cat call");
    }
}
