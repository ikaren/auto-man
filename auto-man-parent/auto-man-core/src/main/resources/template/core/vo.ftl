package ${projectBasePackage}.core.vo;

import org.albert.common.domain.Entity;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public class ${vo.simpleName} extends Entity{
<#if vo.properties?exists>
<#list vo.properties as property>
    /**
     *  <#if property.desc?has_content>${property.desc}</#if>
     */
    private ${property.javaType} ${property.propertyName};
</#list>
</#if>
<#if vo.properties?exists>
<#list vo.properties as property>
    // getter Created by Auto-Man v1.0.0
    public ${property.javaType} get${property.propertyName?cap_first}() {
        return this.${property.propertyName};
    }
    // setter Created by Auto-Man v1.0.0
    public void set${property.propertyName?cap_first}(${property.javaType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
</#list>
</#if>
}