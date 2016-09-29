package ${mapper.packageName};

import ${mapper.bindingDomain.fullyQualifiedName};
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public interface ${mapper.simpleName} {
    <#assign mapperOperations=mapper.operations>
    <#if mapperOperations?exists>
        <#list mapperOperations?keys as operation>
    ${mapperOperations[operation].returnType} ${mapperOperations[operation].name}(<#assign map=mapperOperations[operation].args><#if map?exists><#list map?keys as arg>${arg} ${map[arg]}<#if arg_has_next>,</#if></#list></#if>);
        </#list>
    </#if>
}
