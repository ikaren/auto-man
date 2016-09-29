package org.jason.automan.bean;

import java.util.Map;

/**
 * Created by Jason.Xia on 16/9/28.
 */
public class Interface extends Clazz {
    protected Map<OperationType, Operation> operations;
    private Domain bindingDomain;

    public Interface() {
    }

    public Interface(Map<OperationType, Operation> operations, Domain bindingDomain) {
        this.operations = operations;
        this.bindingDomain = bindingDomain;
    }

    public Map<OperationType, Operation> getOperations() {
        return operations;
    }

    public void setOperations(Map<OperationType, Operation> operations) {
        this.operations = operations;
    }

    public Domain getBindingDomain() {
        return bindingDomain;
    }

    public void setBindingDomain(Domain bindingDomain) {
        this.bindingDomain = bindingDomain;
    }
}
