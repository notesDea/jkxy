package com.jkxy.second;

import java.io.*;

/**
 * Created by dea on 16-7-5.
 */
public class UsingFileInputStream {

    public static void main(String[] args) {
        //Stream读取
        try {
            java.io.FileInputStream fileInputStream = new java.io.FileInputStream("src/Test.txt");
            byte[] bytes = new byte[3];
            fileInputStream.read(bytes);
            String str = "";


            while (fileInputStream.read(bytes) != -1) {
                str += new String(bytes, "UTF-8");
            }
            System.out.println(str);

            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





//        File file = new File("src/Test.txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
