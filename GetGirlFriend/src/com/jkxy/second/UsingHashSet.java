package com.jkxy.second;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dea on 16-7-4.
 */
public class UsingHashSet {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
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

        set.removeAll(set);

        System.out.println(set);

    }
}
