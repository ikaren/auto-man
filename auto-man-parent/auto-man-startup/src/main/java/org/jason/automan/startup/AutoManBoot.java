package org.jason.automan.startup;

import org.jason.automan.parser.support.XmlParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class AutoManBoot {
    public static void main(String[] args) {
        String rootPath = args[0];
        System.out.println(rootPath);
        List<String> projects = new ArrayList<>();
        if (null != rootPath) {
            File dir = new File(rootPath);
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (null == files || 0 == files.length) {
                    System.out.println("Not found any project configuration file in path :" + rootPath);
                    return;
                }

                for (File item : files) {
                    if (item.getName().endsWith(".xml")) {
                        projects.add(item.getAbsolutePath());
                    }
                }
            }
        }

        for (String item : projects) {
            new XmlParser().parser(item);
        }

    }
}
