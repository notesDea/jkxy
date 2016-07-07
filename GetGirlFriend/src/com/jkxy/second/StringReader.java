package com.jkxy.second;

import java.io.*;
import java.io.FileInputStream;

/**
 * Created by dea on 16-7-5.
 */
public class StringReader {

    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/Test.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");

            FileOutputStream fileOutputStream = new FileOutputStream("src/newTest.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");

            char[] chars = new char[100];
//            String str = "";
            int len;
            while ((len = inputStreamReader.read(chars)) != -1) {             //+数组每100个字符的加上去
//                str += new String(chars, 0, len);                             //最后不满100时不会执行，所以需要设置长度
                outputStreamWriter.write(chars, 0, len);
            }

//            System.out.println(str);

            outputStreamWriter.close();
            fileOutputStream.close();
            inputStreamReader.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
