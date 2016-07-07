package com.jkxy.search;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dea on 16-7-7.
 */
public class SearchFile {

    public static File[] searchFile(File dir, String regex) {
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                //directory or matches keyword, return true
                return  (pathname.isDirectory() || (pathname.isFile() && pathname.getName().matches(regex)));
            }
        });


        List<File> match = new ArrayList<>();
        for (File i : files) {
            if (i.isFile()) {
                match.add(i);
            } else {
                File[] subFiles = searchFile(i, regex);
                for (File j : subFiles) {
                    match.add(j);
                }
            }
        }

        File[] reslut = new File[match.size()];
        match.toArray(reslut);
        return reslut;
    }
}
