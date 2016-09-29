package org.jason.automan.bean;

import java.util.Map;

/**
 * Created by Jason.Xia on 16/9/28.
 */
public class Operation {
    private OperationType operationType;
    private String name;
    private String returnType;
    private Map<String, String> args;

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
