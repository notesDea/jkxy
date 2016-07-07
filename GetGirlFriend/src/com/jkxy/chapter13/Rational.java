package com.jkxy.chapter13;

import java.util.Random;

/**
 * Created by dea on 16-7-3.
 */
public class Rational extends Number implements Comparable<Rational> {
    private long numberator, denominator;

    public Rational() {
        this(0, 1);
    }

    public Rational(long numberator, long denominator) {
        long gcd = gcd(numberator, denominator);
        this.numberator = (denominator > 0 ? 1 : -1) * numberator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    public long getNumberator() {
        return numberator;
    }

    public long getDenominator() {
        return denominator;
    }

    public Rational add(Rational anotherRational) {
        long n = numberator * anotherRational.getDenominator() + anotherRational.getNumberator() * denominator;
        long d = denominator * anotherRational.getDenominator();

        long gcd = gcd(n, d);
        n /= gcd;
        d /= gcd;
        return new Rational(n, d);
    }

    public Rational subtract(Rational anotherRational) {
        long n = numberator * anotherRational.getDenominator() - anotherRational.getNumberator() * denominator;
        long d = denominator * anotherRational.getDenominator();

        long gcd = gcd(n, d);
        n /= gcd;
        d /= gcd;
        return new Rational(n, d);
    }

    public Rational multiply(Rational anotherRational) {
        long n = numberator * anotherRational.getNumberator();
        long d = denominator * anotherRational.getDenominator();

        long gcd = gcd(n, d);
        n /= gcd;
        d /= gcd;
        return new Rational(n, d);
    }

    public Rational divide(Rational anotherRational) {
        long n = numberator / anotherRational.getDenominator();
        long d = denominator / anotherRational.getNumberator();

        long gcd = gcd(n, d);
        n /= gcd;
        d /= gcd;
        return new Rational(n, d);
    }

    private static long gcd(long n, long d) {
        long min = Math.abs(min(n, d));

        for (long k = min; ; k--) {
            if (n % k == 0 && d % k == 0) {
                return k;
            }
        }
    }

    private static long min(long n, long m) {
        if (n < m) {
            return n;
        } else {
            return m;
        }
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return numberator + "";
        } else {
            return numberator + "/" + denominator;
        }
    }

    @Override
    public int intValue() {
        return (int) (numberator / denominator);
    }

    @Override
    public long longValue() {
        return (long) (numberator / denominator);
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        return numberator * 1.0 / denominator;
    }


    @Override
    public int compareTo(Rational o) {
        if (this.subtract(o).getNumberator() < 0) {
            return -1;
        } else if (this.subtract(o).getNumberator() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this.subtract((Rational) other).getNumberator() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
