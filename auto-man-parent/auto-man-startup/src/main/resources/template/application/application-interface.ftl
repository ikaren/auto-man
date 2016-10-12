package ${projectBasePackage}.application;
<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
import ${projectBasePackage}.core.vo.${vo.simpleName};
</#list>
</#if>
import ${projectBasePackage}.core.domain.${domain.simpleName};

import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public interface I${domain.simpleName}Application {
    <#-- create -->
    /**
     * create new ${domain.simpleName}
     *
     * @param ${domain.uncapFirstName}List
     * @return
     */
    int insert${domain.simpleName}Batch(List<${domain.simpleName}> ${domain.uncapFirstName}List);

    <#-- Retrieve -->
    /**
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    ${domain.simpleName} select${domain.simpleName}ById(long ${domain.uncapFirstName}Id);

    <#-- Update -->
    /**
     * update ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}
     * @return
     */
    int update${domain.simpleName}ById(${domain.simpleName} ${domain.uncapFirstName});

    <#-- Delete -->
    /**
     * delete ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    int delete${domain.simpleName}ById(long ${domain.uncapFirstName}Id);

<#if domain.bindingVOs?exists>
    <#list domain.bindingVOs as vo>
    <#-- create -->
    /**
     * create new ${vo.simpleName}
     *
     * @param ${vo.uncapFirstName}List
     * @return
     */
    int insert${vo.simpleName}Batch(List<${vo.simpleName}> ${vo.uncapFirstName}List);

    <#-- Retrieve -->
    /**
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    ${vo.simpleName} select${vo.simpleName}ById(long ${vo.uncapFirstName}Id);

    <#-- Update -->
    /**
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    int update${vo.simpleName}ById(${vo.simpleName} ${vo.uncapFirstName});

    <#-- Delete -->
    /**
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    int delete${vo.simpleName}ById(long ${vo.uncapFirstName}Id);
    </#list>
</#if>
}
