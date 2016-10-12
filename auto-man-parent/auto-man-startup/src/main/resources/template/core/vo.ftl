package ${projectBasePackage}.core.vo;

import org.albert.common.domain.Entity;
<#if vo.properties?exists>
    <#list vo.properties as property>
        <#if property.javaType == "Date">
import java.util.Date;
            <#break>
        </#if>
    </#list>
</#if>
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
    public ${property.javaType} get${property.propertyName?cap_first}() {
        return this.${property.propertyName};
    }
    public void set${property.propertyName?cap_first}(${property.javaType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
</#list>
</#if>
}