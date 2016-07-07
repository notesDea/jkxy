package com.jkxy.abs;

/**
 * Created by dea on 16-7-1.
 */
public class Worker extends Person {

    private int wage;

    public Worker(String name, int age, int wage) {
        super(name, age);
        this.wage = wage;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    @Override
    public String getContent() {
        return "Worker info-->name: " + getName() + ", age: " + getAge() + ", wage: " + wage;
    }

}
