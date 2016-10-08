package org.jason.automan.bean;

/**
 * Created by Jason.Xia on 16/9/29.
 */
public class Repository extends Clazz {
    private Interface bindingInterface;
    private ValueObject bindingVO;
    private Mapper mapper;

    public Repository() {
    }

//    public Repository(Mapper mapper, Interface bindingInterface) {
//        this.mapper = mapper;
//        this.bindingInterface = bindingInterface;
//    }
//
    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }


    public ValueObject getBindingVO() {
        return bindingVO;
    }

    public void setBindingVO(ValueObject bindingVO) {
        this.bindingVO = bindingVO;
    }

    public Interface getBindingInterface() {
        return bindingInterface;
    }

    public void setBindingInterface(Interface bindingInterface) {
        this.bindingInterface = bindingInterface;
    }
}
