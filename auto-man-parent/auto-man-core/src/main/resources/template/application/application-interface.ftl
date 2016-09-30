package ${projectBasePackage}.application;

<#list domain.bindingVOs as vo>
import ${projectBasePackage}.core.vo.${vo.simpleName};
</#list>
import ${projectBasePackage}.core.domain.${domain.simpleName};

import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public interface I${domain.simpleName}Application {
    <#-- create -->
    /**
     * Created by Auto-Man v1.0.0
     * create new ${domain.simpleName}
     *
     * @param ${domain.uncapFirstName}List
     * @return
     */
    public int insert${domain.simpleName}Batch(List<${domain.simpleName}> ${domain.uncapFirstName}List);

    <#-- Retrieve -->
    /**
     * Created by Auto-Man v1.0.0
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    public ${domain.simpleName} select${domain.simpleName}ById(long ${domain.uncapFirstName}Id);

    <#-- Update -->
    /**
     * Created by Auto-Man v1.0.0
     * update ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}
     * @return
     */
    public int update${domain.simpleName}ById(${domain.simpleName} ${domain.uncapFirstName});

    <#-- Delete -->
    /**
     * Created by Auto-Man v1.0.0
     * delete ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    public int delete${domain.simpleName}ById(long ${domain.uncapFirstName}Id);

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
    public int insert${vo.simpleName}Batch(List<${vo.simpleName}> ${vo.uncapFirstName}List);

    <#-- Retrieve -->
    /**
     * Created by Auto-Man v1.0.0
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    public ${vo.simpleName} select${vo.simpleName}ById(long ${vo.uncapFirstName}Id);

    <#-- Update -->
    /**
     * Created by Auto-Man v1.0.0
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    public int update${vo.simpleName}ById(${vo.simpleName} ${vo.uncapFirstName});

    <#-- Delete -->
    /**
     * Created by Auto-Man v1.0.0
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    public int delete${vo.simpleName}ById(long ${vo.uncapFirstName}Id);
    </#list>
</#if>
}
