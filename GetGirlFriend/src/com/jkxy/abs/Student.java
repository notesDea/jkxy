package com.jkxy.abs;

/**
 * Created by dea on 16-7-1.
 */
public class Student extends Person {

    private int score;

    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String getContent() {
        return "Student Infro--> name: " + getName() + ", age: " + getAge() + ", score: " + score;
    }
}
