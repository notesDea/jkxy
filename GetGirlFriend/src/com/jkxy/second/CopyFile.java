package com.jkxy.second;

import java.io.*;
import java.io.FileInputStream;

/**
 * Created by dea on 16-7-5.
 */
public class CopyFile {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("src/dd_launcher.jpg");
            FileOutputStream fos = new FileOutputStream("src/notesDea.jpg");
            byte[] bytes = new byte[30];

            while ((fis.read(bytes)) != -1) {
                fos.write(bytes);
            }

            fos.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
