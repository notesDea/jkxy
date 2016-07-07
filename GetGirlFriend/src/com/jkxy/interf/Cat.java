package com.jkxy.interf;

/**
 * Created by dea on 16-7-1.
 */
public class Cat extends Abss implements Pet {

    @Override
    public void call() {
        System.out.println("我饿了");
    }

    @Override
    public void play() {
        System.out.println(Pet.NAME + "喜欢和我们玩");            //最好加上类名+常量，别人或息看代码的时候，可以清楚的知道这个方法来自于哪个类的。
    }

    @Override
    public void eatFood() {

        System.out.println(Pet.NAME + "喜欢吃" + FISH);
    }

    @Override
    public void abc() {

    }
}
