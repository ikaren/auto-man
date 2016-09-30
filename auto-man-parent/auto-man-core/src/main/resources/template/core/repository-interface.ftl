<#-- @Deprecated -->
package ${domain.packageName};

import ${interface.bindingDomain.fullyQualifiedName};

import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public interface ${interface.simpleName} {
<#assign repositoryOperations=interface.operations>
<#if repositoryOperations?exists>
    <#list repositoryOperations?keys as item>
    /**
    * ${repositoryOperations[item].operationType}
    */
    ${repositoryOperations[item].returnType} ${repositoryOperations[item].name}(<#assign map=repositoryOperations[item].args><#if map?exists><#list map?keys as key>${key} ${map[key]}<#if key_has_next>,</#if></#list></#if>);
    </#list>
</#if>
}
