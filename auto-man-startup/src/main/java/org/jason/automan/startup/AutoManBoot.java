package org.jason.automan.startup;

import org.jason.automan.ProgressListener;
import org.jason.automan.parser.ActionParams;
import org.jason.automan.parser.support.XmlParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class AutoManBoot {
    public static void main(String[] args) {
        String rootPath = args[0];
        String templatePath = args[1];
        if (null == templatePath) {
            System.out.println("Need template path.");
            return;
        }

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
                        System.out.println("Load config file: " + item.getName());
                        projects.add(item.getAbsolutePath());
                    }
                }
            }
        }

        for (String item : projects) {
            new XmlParser().parser(item, templatePath);
        }

        System.out.println("Auto-Man run completed!");
    }

    private static void createNewProject(String configFile, String templatePath, String targetPath, ProgressListener
            listener) {
        File targetDir = new File(targetPath);
        if (!targetDir.isDirectory() || !targetDir.exists()) {
            listener.update("TargetPath Not Exists or Not Directory");
            return;
        }

        if (null == configFile || configFile.length() == 0) {
            throw new IllegalStateException("configFile not be null");
        }

        if (!configFile.endsWith(".xml")) {
            listener.update("ConfigFile expect .xml file");
            return;
        }

        new XmlParser().parser(configFile, templatePath, targetPath, listener);
        if (null != listener) {
            listener.update("completed!");
        }
    }

    private static void createNewDomain(String in, String templatePath, String projectDir, String projectName,
                                        ProgressListener listener) {
        new XmlParser().addNewDomainOrVO(in, templatePath, projectDir, projectName, listener);
        if (null != listener) {
            listener.update("completed!");
        }
    }

    public static void create(ActionParams.Action action, ActionParams params) {
        switch (action) {
            case CREATE_PROJECT:
                ActionParams.validate(params);
                createNewProject(params.getConfigFile(), params.getTemplatePath(), params.getTargetPath(), params
                        .getListener());
                break;
            case ADD_NEW_DOMAIN:
                ActionParams.validate(params);
                Map<String, String> extra = params.getExtra();
                createNewDomain(params.getConfigFile(), params.getTemplatePath(), params.getTargetPath(), extra.get
                        ("projectName"), params.getListener());
                break;
        }
    }
}
