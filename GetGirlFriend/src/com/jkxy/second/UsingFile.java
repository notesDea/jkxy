package com.jkxy.second;

import java.io.File;
import java.io.IOException;

/**
 * Created by dea on 16-7-4.
 */
public class UsingFile {

    public static void main(String[] args) throws IOException {
        File file = new File("src/*.txt");

        try {
            if (file.exists()) {
                System.out.println("file exists");
            } else {
                System.out.println("no such file");
//                file.renameTo(new File("src/newHello.txt"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
