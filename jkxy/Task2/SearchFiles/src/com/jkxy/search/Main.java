package com.jkxy.search;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by dea on 16-7-6.
 *
 * a)读取指定目录的所有mp3文件，包括所有子目录
 * b)将所读取的文件呈现在控制台
 */

public class Main {

    public static void main(String[] args) {
        File[] files = SearchFile.searchFile(new File("src"), ".*\\.mp3");

        if (files == null) {
            System.out.println("No such file");
        } else {
            for (File f : files) {
//                System.out.println(f.getName());
                System.out.println(f.getPath());
            }
        }
    }
}
