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
@ThemisServiceConfig(DSI = "${domain.uncapFirstName}QueryFacade",
        version = "1.0.0",
        cluster = ClusterType.FAIL_FAST,
        timeout = 60000,
        check = false,
        format = Format.JSON,
        protocol = HttpProtocol.HTTP,
        inProtocol = InnerProtocol.DUBBO,
        description = "<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>查询服务")
public interface I${domain.simpleName}QueryFacade {

    /**
     * Created by Auto-Man v1.0.0
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    @ThemisAPIConfig(apiName = "${projectLayer}.${projectName.uncapFirst}.${domain.uncapFirstName}.get",
        needSession = true,
        method = HttpMethod.GET,
        returnType = ${domain.simpleName}DTO.class,
        description = "通过id查询<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if>")
    Response<${domain.simpleName}DTO> select${domain.simpleName}ById(@ThemisArgConfig(argName = "${domain.uncapFirstName}Id", desc = "<#if domain.desc?has_content>${domain.desc}<#else>${domain.simpleName}</#if> id") String ${domain.uncapFirstName}Id,String traceId);

<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
    /**
     * Created by Auto-Man v1.0.0
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @ThemisAPIConfig(apiName = "${projectLayer}.${projectName.uncapFirst}.${vo.uncapFirstName}.get",
        needSession = true,
        method = HttpMethod.GET,
        returnType = ${vo.simpleName}DTO.class,
        description = "通过id查询<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if>")
    Response<${vo.simpleName}DTO> select${vo.simpleName}ById(@ThemisArgConfig(argName = "${vo.uncapFirstName}Id", desc = "<#if vo.desc?has_content>${vo.desc}<#else>${vo.simpleName}</#if> id") String ${vo.uncapFirstName}Id,String traceId);
</#list>
</#if>
}
