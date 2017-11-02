package org.jason.automan.parser;

import org.jason.automan.ProgressListener;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Jason.Xia on 17/4/22.
 */
public class ActionParams implements Serializable {
    private String targetPath;
    private String configFile;
    private String templatePath;
    private ProgressListener listener;
    private Map<String, String> extra;

    public ActionParams() {
    }

    public ActionParams(String targetPath, String configFile, String templatePath, ProgressListener listener) {
        this.targetPath = targetPath;
        this.configFile = configFile;
        this.templatePath = templatePath;
        this.listener = listener;
    }

    public static void validate(ActionParams actionParams) {
        File targetDir = new File(actionParams.getTargetPath());
        if (!targetDir.isDirectory() || !targetDir.exists()) {
            if (null != actionParams.getListener()) {
                actionParams.getListener().update("TargetPath Not Exists or Not Directory");
            } else {
                throw new IllegalArgumentException("TargetPath Not Exists or Not Directory");
            }
            return;
        }

        if (null == actionParams.getConfigFile() || actionParams.getConfigFile().length() == 0) {
            if (null != actionParams.getListener()) {
                actionParams.getListener().update("configFile not be null");
            } else {
                throw new IllegalArgumentException("configFile not be null");
            }
            return;
        }

        if (!actionParams.getConfigFile().endsWith(".xml")) {
            if (null != actionParams.getListener()) {
                actionParams.getListener().update("ConfigFile expect .xml file");
            } else {
                throw new IllegalArgumentException("ConfigFile expect .xml file");
            }
        }
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public ProgressListener getListener() {
        return listener;
    }

    public void setListener(ProgressListener listener) {
        this.listener = listener;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    public enum Action {
        CREATE_PROJECT(1, "CREATE_PROJECT"),
        ADD_NEW_DOMAIN(2, "ADD_NEW_DOMAIN");

        public int code;
        public String desc;

        private Action(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}
