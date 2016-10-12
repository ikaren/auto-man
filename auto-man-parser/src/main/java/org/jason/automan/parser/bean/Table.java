package org.jason.automan.parser.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jason.Xia on 16/10/8.
 */
public class Table {
    private String id;
    private String name;
    private String className;
    private String bindingDomain;
    private boolean isDomain;
    private String desc;
    private Set<Column> columns = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBindingDomain() {
        return bindingDomain;
    }

    public void setBindingDomain(String bindingDomain) {
        this.bindingDomain = bindingDomain;
    }

    public boolean isDomain() {
        return isDomain;
    }

    public void setDomain(boolean domain) {
        isDomain = domain;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Column> getColumns() {
        return columns;
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }
}
