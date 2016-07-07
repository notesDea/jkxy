package com.jkxy.second;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dea on 16-7-4.
 */
public class UsingList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.toString());

        list.add("d");
        System.out.println(list.toString());

        list.remove(1);
        System.out.println(list.toString());

        System.out.println(list.size());
    }
}
