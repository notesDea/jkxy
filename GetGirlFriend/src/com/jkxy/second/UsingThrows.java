package com.jkxy.second;

/**
 * Created by dea on 16-7-4.
 */
public class UsingThrows {

    public static void main(String[] args) {
        try {
            Caculation caculation = new Caculation(5, 0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static class Caculation {

        public Caculation(int a, int b) throws Exception{
            int result = a / b;
            System.out.println(result);
        }
    }
}
