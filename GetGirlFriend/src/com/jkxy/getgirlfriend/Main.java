package com.jkxy.getgirlfriend;

import java.util.Random;

/**
 * Created by dea on 16-6-30.
 */
public class Main {
    static String[] firstNameArr = {"李", "王", "邹", "朱", "马", "石", "陈"};
    static String[] lastNameArr = {"丽丽", "勇", "锋", "达", "敏佳", "雨秋", "毅磊"};

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            String firstName = firstNameArr[random.nextInt(firstNameArr.length)];
            String lastName = lastNameArr[random.nextInt(firstNameArr.length)];

            GirlFriend girl = new GirlFriend(firstName + lastName, RandomGenerate.random(20, 31));
            System.out.println(girl.toString());
        }
    }
}
