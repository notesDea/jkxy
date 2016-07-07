package com.jkxy.second;

import java.util.*;

/**
 * Created by dea on 16-7-4.
 */
public class UsingMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("name", 953);
        map.put("age", 22);

        System.out.println(map.keySet());

        Set<String> set = map.keySet();
        System.out.println(set);

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("key: " + iterator.next());
        }

        System.out.println("————————————————————————");

        Collection<Integer> collection = map.values();
        Iterator<Integer> iterator1 = collection.iterator();
        while (iterator1.hasNext()) {
            System.out.println("value: " + iterator1.next());
        }

    }

}
