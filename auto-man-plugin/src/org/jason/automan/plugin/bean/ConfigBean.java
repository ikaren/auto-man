package org.jason.automan.plugin.bean;

import java.io.Serializable;

/**
 * Created by Jason.Xia on 17/4/18.
 */
public class ConfigBean implements Serializable {
    private String targetPath;

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
