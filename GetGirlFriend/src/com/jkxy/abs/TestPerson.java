package com.jkxy.abs;

/**
 * Created by dea on 16-7-1.
 */
public class TestPerson {
    public static void main(String[] args) {
        Student student = new Student("Zjd", 22, 99);
        Worker worker = new Worker("Ly", 27, 3000);

        student.say();
        worker.say();
    }
}
