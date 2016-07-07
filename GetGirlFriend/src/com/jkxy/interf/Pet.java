package com.jkxy.interf;

/**
 * Created by dea on 16-7-1.
 */
public interface Pet extends Eat, Play {
    String NAME = "小猫";

    void call();

    void play();
}
