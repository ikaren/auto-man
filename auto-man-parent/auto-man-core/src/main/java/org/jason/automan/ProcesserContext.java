package org.jason.automan;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/6.
 */
public class ProcesserContext {
    private String projectName;
    /**
     * absolute dir.
     * eg:/Users/username/project/
     */
    private String projectHome;
    /**
     * project base package name
     * eg:com.foo.service.aaa
     */
    private String basePackage;
    /**
     * project root path
     * eg:/com/foo/service/aaa/
     */
    private String projectBase;

    /**
     * template repository root path
     */
    private String templateRepository;

    private transient String projectLayer;

    private Map<String, String> extra = new HashMap<>();

    public ProcesserContext(String projectName, String projectHome, String basePackage, String projectBase, String
            templateRepository) {
        this.projectName = projectName;
        this.projectHome = projectHome;

        this.basePackage = basePackage;
        if (null == projectBase) {
            this.projectBase = "/" + basePackage.replace(".", "/");
        }

        String[] split = basePackage.split("\\.");
        if (split.length >= 2) {
            this.projectLayer = split[split.length - 2];
        } else {
            this.projectLayer = this.projectName;
        }

        this.templateRepository = templateRepository;
    }

    public ProcesserContext(String projectName, String projectHome, String basePackage) {
        this(projectName, projectHome, basePackage, null, null);
    }

    public ProcesserContext(String projectName, String projectHome, String basePackage, String templateRepository) {
        this(projectName, projectHome, basePackage, null, templateRepository);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectHome() {
        return projectHome;
    }

    public void setProjectHome(String projectHome) {
        this.projectHome = projectHome;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getProjectBase() {
        return projectBase;
    }

    public void setProjectBase(String projectBase) {
        this.projectBase = projectBase;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    public String getTemplateRepository() {
        return templateRepository;
    }

    public void setTemplateRepository(String templateRepository) {
        this.templateRepository = templateRepository;
    }

    public String getProjectLayer() {
        return projectLayer;
    }

    public void setProjectLayer(String projectLayer) {
        this.projectLayer = projectLayer;
    }
}