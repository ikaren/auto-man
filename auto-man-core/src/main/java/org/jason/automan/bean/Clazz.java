package org.jason.automan.bean;

/**
 * Created by Jason.Xia on 16/9/29.
 */
public class Clazz<T> {
    private String fullyQualifiedName;
    private String strName;
    private String uncapFirstName;
    private String simpleName;
    private String packageName;
    private boolean isPrimitive;
    private boolean isPrimitiveWrapper;
    private String desc;
    private T superclass;
    private String tableName;
    public String getUncapFirstName() {
        return uncapFirstName;
    }

    public void setUncapFirstName(String uncapFirstName) {
        this.uncapFirstName = uncapFirstName;
    }

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public void setFullyQualifiedName(String fullyQualifiedName) {
        this.fullyQualifiedName = fullyQualifiedName;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public void setPrimitive(boolean primitive) {
        isPrimitive = primitive;
    }

    public boolean isPrimitiveWrapper() {
        return isPrimitiveWrapper;
    }

    public void setPrimitiveWrapper(boolean primitiveWrapper) {
        isPrimitiveWrapper = primitiveWrapper;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getSuperclass() {
        return superclass;
    }

    public void setSuperclass(T superclass) {
        this.superclass = superclass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
