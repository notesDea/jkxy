package com.jkxy.chapter13;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by dea on 16-7-2.
 */
public class TestCalendar {
    public static void main(String[] args) {
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar(2011, 1, 1);

        System.out.println(calendar1.get(Calendar.YEAR));
        System.out.println(calendar2.get(Calendar.YEAR));
    }
}
