package org.jason.automan.bean;

import java.util.List;

/**
 * Created by Jason.Xia on 16/9/30.
 */
public class ValueObject extends Clazz {
    private List<Property> properties;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
