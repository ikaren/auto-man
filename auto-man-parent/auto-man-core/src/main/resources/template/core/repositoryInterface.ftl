package ${interface.packageName};

import ${interface.bindingDomain.fullyQualifiedName};

import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public interface ${interface.simpleName} {
<#if interface.operations?exists>
    <#list interface.operations as operation>
    /**
     * ${operation.operationType}
     */
    ${operation.returnType} ${operation.name}(<#assign map=operation.args><#if map?exists><#list map?keys as key>${key} ${map[key]}<#if key_has_next>,</#if></#list></#if>);
    </#list>
</#if>
}
