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
import java.util.ArrayList;
import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@Named
public class ${domain.simpleName}QueryFacade implements I${domain.simpleName}QueryFacade {
    private static final Logger logger = LoggerFactory.getLogger(${domain.simpleName}QueryFacade.class);

    @Inject
    private I${domain.simpleName}Application ${domain.uncapFirstName}Application;

    /**
     * select ${domain.simpleName} by id
     *
     * @param ${domain.uncapFirstName}Id
     * @return
     */
    @Override
    public Response<List<${domain.simpleName}DTO>> select${domain.simpleName}ByIdBatch(List<String> ${domain.uncapFirstName}Id,String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[select${domain.simpleName}ByIdBatch] request:{}", ${domain.uncapFirstName}Id);
        Response<List<${domain.simpleName}DTO>> response = new Response<>();
        try{
            List<Long> param=new ArrayList<>();
            for(String item : ${domain.uncapFirstName}Id){
                param.add(Long.valueOf(item));
            }

            List<${domain.simpleName}> ${domain.uncapFirstName} = this.${domain.uncapFirstName}Application.select${domain.simpleName}ByIdBatch(param);
            List<${domain.simpleName}DTO> result = ${domain.simpleName}Assembler.domainToDtoBatch(${domain.uncapFirstName});
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("查询成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[select${domain.simpleName}ByIdBatch] error. param:{}, msg:{}, trace:{}",${domain.uncapFirstName}Id, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }

<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
    /**
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @Override
    public Response<List<${vo.simpleName}DTO>> select${vo.simpleName}ByIdBatch(List<String> ${vo.uncapFirstName}Id,String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[select${vo.simpleName}ByIdBatch] request:{}", ${vo.uncapFirstName}Id);
        Response<List<${vo.simpleName}DTO>> response = new Response<>();
        try{
            List<Long> param=new ArrayList<>();
            for(String item : ${vo.uncapFirstName}Id){
                param.add(Long.valueOf(item));
            }

            List<${vo.simpleName}> ${vo.uncapFirstName} = this.${domain.uncapFirstName}Application.select${vo.simpleName}ByIdBatch(param);
            List<${vo.simpleName}DTO> result = ${domain.simpleName}Assembler.${vo.uncapFirstName}DomainToDtoBatch(${vo.uncapFirstName});
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("查询成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[select${vo.simpleName}ByIdBatch] error. param:{}, msg:{}, trace:{}",${vo.uncapFirstName}Id, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }
</#list>
</#if>
}
