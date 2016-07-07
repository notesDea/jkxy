package com.jkxy.getgirlfriend;

import java.util.Random;

/**
 * Created by dea on 16-6-30.
 */
public class RandomGenerate {

    public static int random(int start, int end) {
        Random random = new Random();
        return random.nextInt((end - start)) + start;
    }

    public static int random(int end) {
        Random random = new Random();
        return random.nextInt(end);
    }
}
