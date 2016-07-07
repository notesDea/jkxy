package com.jkxy.second;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by dea on 16-7-5.
 */
public class UsingLib {

    public static void main(String[] args) {
        File srcFile = new File("src/Test.txt");
        File newFile = new File("src/Test(1).txt");

        try {
            FileUtils.copyFile(srcFile, newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
