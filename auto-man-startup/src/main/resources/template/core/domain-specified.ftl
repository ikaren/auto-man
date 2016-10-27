package ${projectBasePackage}.core.domain;

import ${projectBasePackage}.core.repository.I${domain.simpleName}Repository;
<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
import ${projectBasePackage}.core.vo.${vo.simpleName};
import ${projectBasePackage}.core.repository.I${vo.simpleName}Repository;
</#list>
</#if>
import org.albert.common.domain.Entity;
<#if domain.properties?exists>
    <#list domain.properties as property>
        <#if property.javaType == "Date">
import java.util.Date;
        <#break>
        </#if>
    </#list>
</#if>
import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@Named
public class ${domain.simpleName} extends Entity{
<#if domain.properties?exists>
<#list domain.properties as property>
    /**
    *  <#if property.desc?has_content>${property.desc}</#if>
    */
    private ${property.javaType} ${property.propertyName};
</#list>
</#if>

    @Inject
    private I${domain.simpleName}Repository ${domain.uncapFirstName}Repository;

<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
    @Inject
    private I${vo.simpleName}Repository ${vo.uncapFirstName}Repository;
</#list>
</#if>
<#if domain.hasConstructors>
    public ${domain.simpleName}() {
    }
</#if>

    <#-- create -->
    /**
     * create new ${domain.simpleName}
     *
     * @param ${domain.uncapFirstName}List
     * @return
     */
    public int insert${domain.simpleName}Batch(List<${domain.simpleName}> ${domain.uncapFirstName}List){
        return this.${domain.uncapFirstName}Repository.insert${domain.simpleName}Batch(${domain.uncapFirstName}List);
    }

    <#-- Retrieve -->
    /**
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    public ${domain.simpleName} select${domain.simpleName}ByIdBatch(List<Long> ${domain.uncapFirstName}Id){
        return this.${domain.uncapFirstName}Repository.select${domain.simpleName}ByIdBatch(${domain.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * update ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}
     * @return
     */
    public int update${domain.simpleName}ByIdBatch(List<${domain.simpleName}> ${domain.uncapFirstName}){
        return this.${domain.uncapFirstName}Repository.update${domain.simpleName}ByIdBatch(${domain.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * delete ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    public int delete${domain.simpleName}ByIdBatch(List<Long> ${domain.uncapFirstName}Id){
        return this.${domain.uncapFirstName}Repository.delete${domain.simpleName}ByIdBatch(${domain.uncapFirstName}Id);
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
    public int insert${vo.simpleName}Batch(List<${vo.simpleName}> ${vo.uncapFirstName}List){
        return this.${vo.uncapFirstName}Repository.insert${vo.simpleName}Batch(${vo.uncapFirstName}List);
    }

    <#-- Retrieve -->
    /**
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    public ${vo.simpleName} select${vo.simpleName}ByIdBatch(List<Long> ${vo.uncapFirstName}Id){
        return this.${vo.uncapFirstName}Repository.select${vo.simpleName}ByIdBatch(${vo.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    public int update${vo.simpleName}ByIdBatch(List<${vo.simpleName}> ${vo.uncapFirstName}){
        return this.${vo.uncapFirstName}Repository.update${vo.simpleName}ByIdBatch(${vo.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    public int delete${vo.simpleName}ByIdBatch(List<Long> ${vo.uncapFirstName}Id){
        return this.${vo.uncapFirstName}Repository.delete${vo.simpleName}ByIdBatch(${vo.uncapFirstName}Id);
    }
</#list>
</#if>
<#if domain.properties?exists>
<#list domain.properties as property>
    public ${property.javaType} get${property.propertyName?cap_first}() {
        return this.${property.propertyName};
    }
    public void set${property.propertyName?cap_first}(${property.javaType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
</#list>
</#if>
}