package ${projectBasePackage}.facade.impl;

import ${projectBasePackage}.application.I${domain.simpleName}Application;
import ${projectBasePackage}.core.domain.${domain.simpleName};
import ${projectBasePackage}.core.exception.${projectName.capFirst}Exception;
import ${projectBasePackage}.facade.I${domain.simpleName}QueryFacade;
import ${projectBasePackage}.facade.dto.${domain.simpleName}DTO;
import ${projectBasePackage}.facade.dto.ResponseCode;
import ${projectBasePackage}.facade.impl.assembler.${domain.simpleName}Assembler;
<#if domain.bindingVOs?exists>
    <#list domain.bindingVOs as vo>
import ${projectBasePackage}.facade.dto.${vo.simpleName}DTO;
import ${projectBasePackage}.core.vo.${vo.simpleName};
    </#list>
</#if>
import org.albert.common.constant.ConstString;
import org.albert.common.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.inject.Inject;
import javax.inject.Named;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@Named
public class ${domain.simpleName}QueryFacade implements I${domain.simpleName}QueryFacade {
    private static final Logger logger = LoggerFactory.getLogger(${domain.simpleName}QueryFacade.class);

    @Inject
    private I${domain.simpleName}Application ${domain.uncapFirstName}Application;

    /**
     * Created by Auto-Man v1.0.0
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    @Override
    public Response<${domain.simpleName}DTO> select${domain.simpleName}ById(String ${domain.uncapFirstName}Id,String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[select${domain.simpleName}ById] request:{}", ${domain.uncapFirstName}Id);
        Response<${domain.simpleName}DTO> response = new Response<>();
        try{
            ${domain.simpleName} ${domain.uncapFirstName} = this.${domain.uncapFirstName}Application.select${domain.simpleName}ById(Long.valueOf(${domain.uncapFirstName}Id));
            ${domain.simpleName}DTO result = ${domain.simpleName}Assembler.domainToDTO(${domain.uncapFirstName});
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("查询成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[select${domain.simpleName}ById] error. param:{], msg:{}, trace:{}",${domain.uncapFirstName}Id, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }

<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
    /**
     * Created by Auto-Man v1.0.0
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @Override
    public Response<${vo.simpleName}DTO> select${vo.simpleName}ById(String ${vo.uncapFirstName}Id,String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[select${vo.simpleName}ById] request:{}", ${vo.uncapFirstName}Id);
        Response<${vo.simpleName}DTO> response = new Response<>();
        try{
            ${vo.simpleName} ${vo.uncapFirstName} = this.${domain.uncapFirstName}Application.select${vo.simpleName}ById(Long.valueOf(${vo.uncapFirstName}Id));
            ${vo.simpleName}DTO result = ${domain.simpleName}Assembler.${vo.uncapFirstName}DomainToDTO(${vo.uncapFirstName});
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("查询成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[select${vo.simpleName}ById] error. param:{], msg:{}, trace:{}",${vo.uncapFirstName}Id, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }
</#list>
</#if>
}
