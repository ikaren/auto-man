package org.jason.automan.bean;

import java.util.List;

/**
 * Created by Jason.Xia on 16/9/27.
 */
public class Domain extends Clazz<Domain>{
    private List<Property> properties;
    private List<Interface> interfaces;
    private boolean hasConstructors;

    public Domain() {
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public boolean isHasConstructors() {
        return hasConstructors;
    }

    public void setHasConstructors(boolean hasConstructors) {
        this.hasConstructors = hasConstructors;
    }
}
