package com.jkxy.second;

import java.io.File;
import java.io.IOException;

/**
 * Created by dea on 16-7-5.
 */
public class UsingFileDir {

    public static void main(String[] args) {
        File file = new File("src/newHello.txt");

        try {
            System.out.println(file.createNewFile());
            System.out.println(file.getParent());
            System.out.println("length is " + file.length() + "byte");
            System.out.println("length is " + (float)file.length() / 1000 + "kb");
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getParent());
            System.out.println(new File(file.getAbsolutePath()).getParent());
            System.out.println(file.canExecute());
            System.out.println(file.canRead());
            System.out.println(file.canWrite());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("——————————————————————————————————————");
        System.out.println(file.setReadable(true));
    }
}
