package ${projectBasePackage}.core.domain;

<#list domain.bindingVOs as vo>
import ${projectBasePackage}.core.vo.${vo.simpleName};
import ${projectBasePackage}.core.repository.I${vo.simpleName}Repository;
</#list>
import org.albert.common.domain.Entity;

import javax.inject.Inject;
import javax.inject.Named;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@Named
public class ${domain.simpleName} extends Entity{

<#list domain.properties as property>
    /**
    *  <#if property.desc?has_content>${property.desc}</#if>
    */
    private ${property.javaType} ${property.propertyName};
</#list>

<#list domain.bindingVOs as vo>
    @Inject
    private I${vo.simpleName}Repository ${vo.uncapFirstName}Repository;
</#list>

<#if domain.hasConstructors>
    public ${domain.simpleName}() {
    }
</#if>

    <#-- create -->
    /**
     * Created by Auto-Man v1.0.0
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
     * Created by Auto-Man v1.0.0
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    public ${domain.simpleName} select${domain.simpleName}ById(long ${domain.uncapFirstName}Id){
        return this.${domain.uncapFirstName}Repository.select${domain.simpleName}ById(${domain.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * Created by Auto-Man v1.0.0
     * update ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}
     * @return
     */
    public int update${domain.simpleName}ById(${domain.simpleName} ${domain.uncapFirstName}){
        return this.${domain.uncapFirstName}Repository.update${domain.simpleName}ById(${domain.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * Created by Auto-Man v1.0.0
     * delete ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    public int delete${domain.simpleName}ById(long ${domain.uncapFirstName}Id){
        return this.${domain.uncapFirstName}Repository.delete${domain.simpleName}ById(${domain.uncapFirstName}Id);
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
    public int insert${vo.simpleName}Batch(List<${vo.simpleName}> ${vo.uncapFirstName}List){
        return this.${vo.uncapFirstName}Repository.insert${vo.simpleName}Batch(${vo.uncapFirstName}List);
    }

    <#-- Retrieve -->
    /**
     * Created by Auto-Man v1.0.0
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    public ${vo.simpleName} select${vo.simpleName}ById(long ${vo.uncapFirstName}Id){
        return this.${vo.uncapFirstName}Repository.select${vo.simpleName}ById(${vo.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * Created by Auto-Man v1.0.0
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    public int update${vo.simpleName}ById(${vo.simpleName} ${vo.uncapFirstName}){
        return this.${vo.uncapFirstName}Repository.update${vo.simpleName}ById(${vo.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * Created by Auto-Man v1.0.0
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    public int delete${vo.simpleName}ById(long ${vo.uncapFirstName}Id){
        return this.${vo.uncapFirstName}Repository.delete${vo.simpleName}ById(${vo.uncapFirstName}Id);
    }
</#list>
</#if>

<#list domain.properties as property>
    // getter Created by Auto-Man v1.0.0
    public ${property.javaType} get${property.propertyName?cap_first}() {
        return this.${property.propertyName};
    }
    // setter Created by Auto-Man v1.0.0
    public void set${property.propertyName?cap_first}(${property.javaType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
</#list>
}