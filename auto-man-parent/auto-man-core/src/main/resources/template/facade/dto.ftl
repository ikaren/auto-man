package ${projectBasePackage}.facade.dto;

import org.albert.common.domain.CommonEntity;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public class ${vo.simpleName} extends CommonEntity{

<#list vo.properties as property>
    /**
    *  <#if property.desc?has_content>${property.desc}</#if>
    */
    @ThemisFieldConfig(desc = "<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if>")
    private ${property.javaType} ${property.propertyName};
</#list>
<#list domain.properties as property>
    //
    // getter Created by Auto-Man v1.0.0
    //
    public ${property.javaType} get${property.propertyName?cap_first}() {
        return this.${property.propertyName};
    }
    public void set${property.propertyName?cap_first}(${property.javaType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
</#list>
}