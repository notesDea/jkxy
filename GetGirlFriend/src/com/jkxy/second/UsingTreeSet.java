package com.jkxy.second;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by dea on 16-7-4.
 */
public class UsingTreeSet {

    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("1");
        set.add("D");
        set.add("E");
        set.add("F");
        set.add("10");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("A");

        System.out.println(set);

    }
}
