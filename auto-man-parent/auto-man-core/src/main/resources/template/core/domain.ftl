<#-- @Deprecated -->
package ${domain.packageName};

<#list domain.interfaces as interface>
import ${interface.fullyQualifiedName};
</#list>
<#if domain.superclass?has_content>
import ${domain.superclass.fullyQualifiedName};
</#if>

import javax.inject.Inject;
import javax.inject.Named;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@Named
public class ${domain.simpleName}<#if domain.superclass?has_content> extends ${domain.superclass.simpleName}</#if>{

<#list domain.properties as property>
    /**
    *  <#if property.desc?has_content>${property.desc}</#if>
    */
    private ${property.javaType} ${property.propertyName};
</#list>

<#list domain.interfaces as interface>
    @Inject
    private ${interface.simpleName} ${interface.uncapFirstName};
</#list>

<#if domain.hasConstructors>
    public ${domain.simpleName}() {
    }
</#if>

<#list domain.interfaces as interface>
    <#assign operations=interface.operations>
    <#if operations?exists>
        <#list operations?keys as operation>
        public ${operations[operation].returnType} ${operations[operation].name}(<#assign map=operations[operation].args><#if map?exists><#list map?keys as arg>${arg} ${map[arg]}<#if arg_has_next>,</#if></#list></#if>){
            return this.${interface.uncapFirstName}.${operations[operation].name}(<#assign map=operations[operation].args><#if map?exists><#list map?keys as arg>${arg} ${map[arg]}<#if arg_has_next>,</#if></#list></#if>);
        }
        </#list>
    </#if>
</#list>

<#list domain.properties as property>
    public ${property.javaType} get${property.propertyName?cap_first}() {
        return this.${property.propertyName};
    }

    public void set${property.propertyName?cap_first}(${property.javaType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
</#list>
}