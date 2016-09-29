package ${repository.packageName};

import ${repository.bindingInterface.bindingDomain.fullyQualifiedName};
import ${repository.bindingInterface.fullyQualifiedName};
import ${repository.mapper.fullyQualifiedName};

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@Named
public class ${repository.simpleName} implements ${repository.bindingInterface.simpleName} {

    @Inject
    private ${repository.mapper.simpleName} ${repository.mapper.uncapFirstName};

    <#assign interfaceOperations=repository.dindingInterface.operations>
    <#assign mapperOperations=repository.mapper.operations>
    <#if interfaceOperations?size == mapperOperations?size>
        <#list interfaceOperations?keys as operation>
    @Override
    public ${interfaceOperations[operation].returnType} ${interfaceOperations[operation].name}(<#assign map=interfaceOperations[operation].args><#if map?exists><#list map?keys as arg>${arg} ${map[arg]}<#if arg_has_next>,</#if></#list></#if>){
        return this.${repository.mapper.uncapFirstName}.${mapperOperations[operation].name}(<#assign map=interfaceOperations[operation].args><#if map?exists><#list map?keys as arg>${map[arg]}<#if arg_has_next>,</#if></#list></#if>);
    }
        </#list>
    </#if>
}
