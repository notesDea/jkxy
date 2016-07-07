package com.jkxy.second;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dea on 16-7-5.
 */
public class UsingFIleOutputStream {

    public static void main(String[] args) {
        //Stream写入

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/Test.txt");
            String str = "我爱极客学院，所以我单身。, 12345, abcd, 上山打老虎。";
            byte[] bytes = str.getBytes();
            fileOutputStream.write(bytes);

            //test
            for (int i = 0; i < bytes.length; i++) {
                System.out.println(bytes[i] + " ");
            }

            fileOutputStream.close();



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
