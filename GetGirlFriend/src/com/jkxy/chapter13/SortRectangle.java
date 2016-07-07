package com.jkxy.chapter13;

import java.util.Arrays;

/**
 * Created by dea on 16-7-2.
 */
public class SortRectangle {
    public static void main(String[] args) {
        Rectangle[] rectangles = {
                new Rectangle(3.4, 5.4),
                new Rectangle(13.24, 55.4),
                new Rectangle(7.4, 35.4),
                new Rectangle(1.4, 25.4)};
        Arrays.sort(rectangles);                   //+Arrays.sort是根据 compareTo方法来排序的，而这个方法已经在Rectangle里重写了，所以调用这个方法就可以直接给矩形排序了。

        for (Rectangle rectangle : rectangles) {
            System.out.println(rectangle);
        }
    }
}
