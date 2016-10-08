package ${projectBasePackage}.application.impl;

import ${projectBasePackage}.application.I${domain.simpleName}Application;
import ${projectBasePackage}.core.domain.${domain.simpleName};
import ${projectBasePackage}.core.exception.${projectName.capFirst}Exception;
<#list domain.bindingVOs as vo>
import ${projectBasePackage}.core.vo.${vo.simpleName};
</#list>

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
public class ${repository.simpleName}Application implements I${domain.simpleName}Application {
    private Logger logger = LoggerFactory.getLogger(${repository.simpleName}Application.class);

    @Inject
    private ${domain.simpleName} ${domain.uncapFirstName};
    <#-- create -->
    /**
     * Created by Auto-Man v1.0.0
     * create new ${domain.simpleName}
     *
     * @param ${domain.uncapFirstName}List
     * @return
     */
    @Override
    public int insert${domain.simpleName}Batch(List<${domain.simpleName}> ${domain.uncapFirstName}List) {
        if (null == express) {
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        for(${domain.simpleName} item : ${domain.uncapFirstName}List) {
            item.setId(SequenceUtil.nextId());
        }

        return this.${domain.uncapFirstName}.insert${domain.simpleName}Batch(${domain.uncapFirstName}List);
    }

    <#-- Retrieve -->
    /**
     * Created by Auto-Man v1.0.0
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    @Override
    public ${domain.simpleName} select${domain.simpleName}ById(long ${domain.uncapFirstName}Id){
        if(0L==${domain.uncapFirstName}Id){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.select${domain.simpleName}ById(${domain.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * Created by Auto-Man v1.0.0
     * update ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}
     * @return
     */
    @Override
    public int update${domain.simpleName}ById(${domain.simpleName} ${domain.uncapFirstName}){
        if(null==${domain.uncapFirstName}){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.update${domain.simpleName}ById(${domain.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * Created by Auto-Man v1.0.0
     * delete ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    @Override
    public int delete${domain.simpleName}ById(long ${domain.uncapFirstName}Id){
        if(0L==${domain.uncapFirstName}Id){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.delete${domain.simpleName}ById(${domain.uncapFirstName}Id);
    }

<#if domain.bindingVOs?exists>
    <#list domain.bindingVOs as vo>
    <#-- create -->
    /**
     * Created by Auto-Man v1.0.0
     * create new ${vo.simpleName}
     *
     * @param ${vo.uncapFirstName}List
     * @return
     */
    @Override
    public int insert${vo.simpleName}Batch(List<${vo.simpleName}> ${vo.uncapFirstName}List){
        if (null == express) {
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        for(${vo.simpleName} item : ${vo.uncapFirstName}List) {
            item.setId(SequenceUtil.nextId());
        }

        return this.${domain.uncapFirstName}.insert${vo.simpleName}Batch(${vo.uncapFirstName}List);
    }

    <#-- Retrieve -->
    /**
     * Created by Auto-Man v1.0.0
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @Override
    public ${vo.simpleName} select${vo.simpleName}ById(long ${vo.uncapFirstName}Id){
        if(0L==${vo.uncapFirstName}Id){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.select${vo.simpleName}ById(${vo.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * Created by Auto-Man v1.0.0
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    @Override
    public int update${vo.simpleName}ById(${vo.simpleName} ${vo.uncapFirstName}){
        if(null==${vo.uncapFirstName}){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.update${vo.simpleName}ById(${vo.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * Created by Auto-Man v1.0.0
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @Override
    public int delete${vo.simpleName}ById(long ${vo.uncapFirstName}Id){
        if(0L==${vo.uncapFirstName}Id){
            throw new ${projectName.capFirst}Exception("参数不能为空.");
        }

        return this.${domain.uncapFirstName}.delete${vo.simpleName}ById(${vo.uncapFirstName}Id);
    }
    </#list>
</#if>
}
