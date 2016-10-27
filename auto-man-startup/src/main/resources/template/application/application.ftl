package ${projectBasePackage}.application.impl;

import ${projectBasePackage}.application.I${domain.simpleName}Application;
import ${projectBasePackage}.core.domain.${domain.simpleName};
import ${projectBasePackage}.core.exception.${projectName.capFirst}Exception;
<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
import ${projectBasePackage}.core.vo.${vo.simpleName};
</#list>
</#if>
import org.albert.common.util.SequenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@Transactional(value = "transactionManager")
@Named
public class ${domain.simpleName}Application implements I${domain.simpleName}Application {
    private Logger logger = LoggerFactory.getLogger(${domain.simpleName}Application.class);

    @Inject
    private ${domain.simpleName} ${domain.uncapFirstName};
    <#-- create -->
    /**
     * create new ${domain.simpleName}
     *
     * @param ${domain.uncapFirstName}List
     * @return
     */
    @Override
    public int insert${domain.simpleName}Batch(List<${domain.simpleName}> ${domain.uncapFirstName}List) {
        if (null == ${domain.uncapFirstName}List || 0 == ${domain.uncapFirstName}List.size()) {
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        for(${domain.simpleName} item : ${domain.uncapFirstName}List) {
            item.setId(SequenceUtil.nextId());
        }

        return this.${domain.uncapFirstName}.insert${domain.simpleName}Batch(${domain.uncapFirstName}List);
    }

    <#-- Retrieve -->
    /**
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    @Override
    public List<${domain.simpleName}> select${domain.simpleName}ByIdBatch(List<Long> ${domain.uncapFirstName}Id){
        if(null == ${domain.uncapFirstName}Id || 0 == ${domain.uncapFirstName}Id.size()){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.select${domain.simpleName}ByIdBatch(${domain.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * update ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}
     * @return
     */
    @Override
    public int update${domain.simpleName}ByIdBatch(List<${domain.simpleName}> ${domain.uncapFirstName}){
        if(null == ${domain.uncapFirstName} || 0 == ${domain.uncapFirstName}.size()){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.update${domain.simpleName}ByIdBatch(${domain.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * delete ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    @Override
    public int delete${domain.simpleName}ByIdBatch(List<Long> ${domain.uncapFirstName}Id){
        if(null == ${domain.uncapFirstName}Id || 0 == ${domain.uncapFirstName}Id.size()){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.delete${domain.simpleName}ByIdBatch(${domain.uncapFirstName}Id);
    }

<#if domain.bindingVOs?exists>
    <#list domain.bindingVOs as vo>
    <#-- create -->
    /**
     * create new ${vo.simpleName}
     *
     * @param ${vo.uncapFirstName}List
     * @return
     */
    @Override
    public int insert${vo.simpleName}Batch(List<${vo.simpleName}> ${vo.uncapFirstName}List){
        if (null == ${vo.uncapFirstName}List || 0 == ${vo.uncapFirstName}List.size()) {
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        for(${vo.simpleName} item : ${vo.uncapFirstName}List) {
            item.setId(SequenceUtil.nextId());
        }

        return this.${domain.uncapFirstName}.insert${vo.simpleName}Batch(${vo.uncapFirstName}List);
    }

    <#-- Retrieve -->
    /**
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @Override
    public List<${vo.simpleName}> select${vo.simpleName}ByIdBatch(List<Long> ${vo.uncapFirstName}Id){
        if(null == ${vo.uncapFirstName}Id || 0 == ${vo.uncapFirstName}Id.size()){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.select${vo.simpleName}ByIdBatch(${vo.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    @Override
    public int update${vo.simpleName}ByIdBatch(List<${vo.simpleName}> ${vo.uncapFirstName}){
        if(null == ${vo.uncapFirstName} || 0 == ${vo.uncapFirstName}.size()){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.update${vo.simpleName}ByIdBatch(${vo.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @Override
    public int delete${vo.simpleName}ByIdBatch(List<Long> ${vo.uncapFirstName}Id){
        if(null == ${vo.uncapFirstName}Id || 0 == ${vo.uncapFirstName}Id.size()){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.delete${vo.simpleName}ByIdBatch(${vo.uncapFirstName}Id);
    }
    </#list>
</#if>
}
