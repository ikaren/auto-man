package org.jason.automan;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/6.
 */
public class ProcesserContext {
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

    private Map<String, String> extra = new HashMap<>();

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
}