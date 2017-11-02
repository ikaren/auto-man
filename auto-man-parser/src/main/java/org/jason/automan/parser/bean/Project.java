package org.jason.automan.parser.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/8.
 */
public class Project {
    private String id;
    private String projectName;
    private String projectDir;
    private String packageName;
    private String templateRoot;
    private DataSourceConfig dataSourceConfig;
    private Dubbo dubboConfig;
    private Log4jELK log4jConfig;
    private Map<String/*table name*/, Table> tables = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDir() {
        return projectDir;
    }

    public void setProjectDir(String projectDir) {
        this.projectDir = projectDir;
    }

    public String getTemplateRoot() {
        return templateRoot;
    }

    public void setTemplateRoot(String templateRoot) {
        this.templateRoot = templateRoot;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public Dubbo getDubboConfig() {
        return dubboConfig;
    }

    public void setDubboConfig(Dubbo dubboConfig) {
        this.dubboConfig = dubboConfig;
    }

    public Log4jELK getLog4jConfig() {
        return log4jConfig;
    }

    public void setLog4jConfig(Log4jELK log4jConfig) {
        this.log4jConfig = log4jConfig;
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public void setTables(Map<String, Table> tables) {
        this.tables = tables;
    }
}
