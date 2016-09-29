package org.jason.automan.bean;

import java.util.Map;

/**
 * Created by Jason.Xia on 16/9/29.
 */
public class Mapper extends Clazz {
    private Map<OperationType, Operation> operations;
    private Domain bindingDomain;

    public Mapper() {
    }

    public Mapper(Map<OperationType, Operation> operations) {
        this.operations = operations;
    }

    public Domain getBindingDomain() {
        return bindingDomain;
    }

    public void setBindingDomain(Domain bindingDomain) {
        this.bindingDomain = bindingDomain;
    }

    public Map<OperationType, Operation> getOperations() {
        return operations;
    }

    public void setOperations(Map<OperationType, Operation> operations) {
        this.operations = operations;
    }
}
