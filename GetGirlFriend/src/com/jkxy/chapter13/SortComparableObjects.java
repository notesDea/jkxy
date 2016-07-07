package com.jkxy.chapter13;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by dea on 16-7-2.
 */
public class SortComparableObjects {
    public static void main(String[] args) {
        String[] cities = {"Savannah", "Boston", "Atlanta", "Tampa"};
        Arrays.sort(cities);
        for (String s : cities) {
            System.out.print(s + " ");
        }
        System.out.println();

        BigInteger[] hugNumbers = {new BigInteger("2323231092923992"),
                 new BigInteger("432232323239292"),
                 new BigInteger("54623239292")};
        Arrays.sort(hugNumbers);
        for (BigInteger i : hugNumbers) {
            System.out.println(i);
        }
    }
}
