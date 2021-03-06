package ${projectBasePackage}.facade;

import com.hans.jhd.infra.themis.service.annotation.ThemisAPIConfig;
import com.hans.jhd.infra.themis.service.annotation.ThemisArgConfig;
import com.hans.jhd.infra.themis.service.annotation.ThemisServiceConfig;
import com.hans.jhd.infra.themis.service.enumeration.*;
import ${projectBasePackage}.facade.dto.${domain.simpleName}DTO;
<#if domain.bindingVOs?exists>
    <#list domain.bindingVOs as vo>
import ${projectBasePackage}.facade.dto.${vo.simpleName}DTO;
    </#list>
</#if>
import org.albert.common.domain.Response;

import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@ThemisServiceConfig(DSI = "${domain.uncapFirstName}CommandFacade",
        version = "1.0.0",
        cluster = ClusterType.FAIL_FAST,
        timeout = 60000,
        check = false,
        format = Format.JSON,
        protocol = HttpProtocol.HTTP,
        inProtocol = InnerProtocol.DUBBO,
        description = "<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>写入服务")
public interface I${domain.simpleName}CommandFacade {
    /**
      * create new ${domain.simpleName}
      *
      * @param ${domain.uncapFirstName}List
      * @return
      */
    @ThemisAPIConfig(apiName = "${projectLayer}.${projectName.uncapFirst}.${domain.uncapFirstName}.add",
        needSession = true,
        method = HttpMethod.PUT,
        returnType = Integer.class,
        description = "批量添加<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>")
    Response<Integer> insert${domain.simpleName}Batch(@ThemisArgConfig(argName = "${domain.uncapFirstName}List", desc = "<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>") List<${domain.simpleName}DTO> ${domain.uncapFirstName}List,String traceId);

    /**
     * update ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}
     * @return
     */
    @ThemisAPIConfig(apiName = "${projectLayer}.${projectName.uncapFirst}.${domain.uncapFirstName}.update",
    needSession = true,
    method = HttpMethod.POST,
    returnType = Integer.class,
    description = "根据id更新<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>")
    Response<Integer> update${domain.simpleName}ByIdBatch(@ThemisArgConfig(argName = "${domain.uncapFirstName}", desc = "<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>数组") List<${domain.simpleName}DTO> ${domain.uncapFirstName},String traceId);

    /**
      * delete ${domain.simpleName} by id
      *
      * @param ${domain.uncapFirstName}Id
      * @return
      */
    @ThemisAPIConfig(apiName = "${projectLayer}.${projectName.uncapFirst}.${domain.uncapFirstName}.delete",
        needSession = true,
        method = HttpMethod.DELETE,
        returnType = Integer.class,
        description = "根据id删除<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>")
    Response<Integer> delete${domain.simpleName}ByIdBatch(@ThemisArgConfig(argName = "${domain.uncapFirstName}Id", desc = "<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>数组") List<String> ${domain.uncapFirstName}Id,String traceId);

<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
    /**
     * create new ${vo.simpleName}
     *
     * @param ${vo.uncapFirstName}List
     * @return
     */
    @ThemisAPIConfig(apiName = "${projectLayer}.${projectName.uncapFirst}.${vo.uncapFirstName}.add",
    needSession = true,
    method = HttpMethod.PUT,
    returnType = Integer.class,
    description = "批量添加<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if>")
    Response<Integer> insert${vo.simpleName}Batch(@ThemisArgConfig(argName = "${vo.uncapFirstName}List", desc = "<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if>数组") List<${vo.simpleName}DTO> ${vo.uncapFirstName}List,String traceId);

    /**
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    @ThemisAPIConfig(apiName = "${projectLayer}.${projectName.uncapFirst}.${vo.uncapFirstName}.update",
    needSession = true,
    method = HttpMethod.POST,
    returnType = Integer.class,
    description = "根据id更新<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if>")
    Response<Integer> update${vo.simpleName}ByIdBatch(@ThemisArgConfig(argName = "${vo.uncapFirstName}", desc = "<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if>数组") List<${vo.simpleName}DTO> ${vo.uncapFirstName},String traceId);

    /**
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @ThemisAPIConfig(apiName = "${projectLayer}.${projectName.uncapFirst}.${vo.uncapFirstName}.delete",
    needSession = true,
    method = HttpMethod.DELETE,
    returnType = Integer.class,
    description = "根据id删除<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if>")
    Response<Integer> delete${vo.simpleName}ByIdBatch(@ThemisArgConfig(argName = "${vo.uncapFirstName}Id", desc = "<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if>数组") List<String> ${vo.uncapFirstName}Id,String traceId);
</#list>
</#if>
}
