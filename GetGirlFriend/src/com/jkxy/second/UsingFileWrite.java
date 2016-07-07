package com.jkxy.second;

import java.io.*;

/**
 * Created by dea on 16-7-5.
 */
public class UsingFileWrite {

    public static void main(String[] args) {
        File file = new File("src/newHello.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (int i = 60; i < 100; i++) {
                bufferedWriter.newLine();
                bufferedWriter.write(i);
                System.out.println((char) i);
            }

            bufferedWriter.close();
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
