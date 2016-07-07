package com.jkxy.chapter13;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by dea on 16-7-1.
 */
public class LargestNumber {

    public static void main(String[] args) {
        ArrayList<Number> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(31);
        numbers.add(new BigInteger("3432323234344343101"));
        numbers.add(new BigDecimal("4584434558.48488"));

        System.out.println("The largest number: " + getLargestNumber(numbers));
    }

    public static Number getLargestNumber(ArrayList<Number> numbers) {
        if (numbers == null || numbers.size() == 0) {
            return null;
        }

        Number max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (max.doubleValue() < numbers.get(i).doubleValue()) {
                max = numbers.get(i);
            }
        }
        return max;
    }
}
