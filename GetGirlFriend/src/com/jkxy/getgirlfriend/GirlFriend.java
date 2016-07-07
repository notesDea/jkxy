package com.jkxy.getgirlfriend;

/**
 * Created by dea on 16-6-30.
 */
public class GirlFriend {

    private String name;
    private int age;
    private int id;

    public GirlFriend(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "女朋友的名字是：" + name + "，年龄是：" + age;
    }

}
