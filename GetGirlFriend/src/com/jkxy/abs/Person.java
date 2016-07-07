package com.jkxy.abs;

/**
 * Created by dea on 16-7-1.
 */
public abstract class Person {
    private int age;
    private String name;

    public Person(){

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println(getContent());
    }

    public abstract String getContent();
}
