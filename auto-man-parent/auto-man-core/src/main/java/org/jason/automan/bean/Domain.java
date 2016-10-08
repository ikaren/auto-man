package org.jason.automan.bean;

import java.util.List;

/**
 * Created by Jason.Xia on 16/9/27.
 */
public class Domain extends Clazz<Domain> {
    private List<Property> properties;
//    private List<Repository> repositories;
//    private List<Interface> interfaces;
//    private List<Mapper> mappers;
    private List<ValueObject> bindingVOs;
//    private String projectBasePackage;
    private boolean hasConstructors;

    public Domain() {
    }

    public List<ValueObject> getBindingVOs() {
        return bindingVOs;
    }

    public void setBindingVOs(List<ValueObject> bindingVOs) {
        this.bindingVOs = bindingVOs;
    }

//    public String getProjectBasePackage() {
//        return projectBasePackage;
//    }
//
//    public void setProjectBasePackage(String projectBasePackage) {
//        this.projectBasePackage = projectBasePackage;
//    }
//
//    public List<Interface> getInterfaces() {
//        return interfaces;
//    }
//
//    public void setInterfaces(List<Interface> interfaces) {
//        this.interfaces = interfaces;
//    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

//    public List<Repository> getRepositories() {
//        return repositories;
//    }
//
//    public void setRepositories(List<Repository> repositories) {
//        this.repositories = repositories;
//    }
//
//    public List<Mapper> getMappers() {
//        return mappers;
//    }
//
//    public void setMappers(List<Mapper> mappers) {
//        this.mappers = mappers;
//    }

    public boolean isHasConstructors() {
        return hasConstructors;
    }

    public void setHasConstructors(boolean hasConstructors) {
        this.hasConstructors = hasConstructors;
    }
}
