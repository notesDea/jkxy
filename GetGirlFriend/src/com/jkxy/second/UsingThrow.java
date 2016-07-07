package com.jkxy.second;

/**
 * Created by dea on 16-7-4.
 */
public class UsingThrow {

    public static void main(String[] args) {

        try {
            throw new Exception("Instantiation Exception");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static class Peaple {

        public Peaple(int a, int b)  {
            int result = a / b;
            System.out.println(result);
        }
    }
}
