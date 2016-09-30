package org.jason.automan.bean;

import java.util.Map;

/**
 * Created by Jason.Xia on 16/9/28.
 */
public class Interface extends Clazz {
    private  Map<OperationType, Operation> operations;
    private ValueObject bindingVO;

    public Interface() {
    }

//    public Interface(Map<OperationType, Operation> operations, Domain bindingDomain) {
//        this.operations = operations;
//        this.bindingDomain = bindingDomain;
//    }


    public ValueObject getBindingVO() {
        return bindingVO;
    }

    public void setBindingVO(ValueObject bindingVO) {
        this.bindingVO = bindingVO;
    }

    public Map<OperationType, Operation> getOperations() {
        return operations;
    }

    public void setOperations(Map<OperationType, Operation> operations) {
        this.operations = operations;
    }

//    public Domain getBindingDomain() {
//        return bindingDomain;
//    }
//
//    public void setBindingDomain(Domain bindingDomain) {
//        this.bindingDomain = bindingDomain;
//    }
}
