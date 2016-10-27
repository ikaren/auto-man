package ${projectBasePackage}.facade.impl;

import ${projectBasePackage}.application.I${domain.simpleName}Application;
import ${projectBasePackage}.core.domain.${domain.simpleName};
import ${projectBasePackage}.core.exception.${projectName.capFirst}Exception;
import ${projectBasePackage}.facade.I${domain.simpleName}CommandFacade;
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
public class ${domain.simpleName}CommandFacade implements I${domain.simpleName}CommandFacade {
    private static final Logger logger = LoggerFactory.getLogger(${domain.simpleName}CommandFacade.class);

    @Inject
    private I${domain.simpleName}Application ${domain.uncapFirstName}Application;

    /**
    * create new ${domain.simpleName}
    *
    * @param ${domain.uncapFirstName}DTOList
    * @return
    */
    @Override
    public Response<Integer> insert${domain.simpleName}Batch(List<${domain.simpleName}DTO> ${domain.uncapFirstName}DTOList,String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[insert${domain.simpleName}Batch] request:{}", ${domain.uncapFirstName}DTOList);
        Response<Integer> response = new Response<>();
        try{
            List<${domain.simpleName}>  param= ${domain.simpleName}Assembler.dtoToDomainBatch(${domain.uncapFirstName}DTOList);
            int result = this.${domain.uncapFirstName}Application.insert${domain.simpleName}Batch(param);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("插入成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[insert${domain.simpleName}Batch] error. param:{], msg:{}, trace:{}",${domain.uncapFirstName}DTOList, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }

    /**
    * update ${domain.simpleName} by id
    *
    * @param ${domain.uncapFirstName}
    * @return
    */
    @Override
    public Response<Integer> update${domain.simpleName}ByIdBatch(List<${domain.simpleName}DTO> ${domain.uncapFirstName},String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[update${domain.simpleName}ById] request:{}", ${domain.uncapFirstName});
        Response<Integer> response = new Response<>();
        try{
            List<${domain.simpleName}>  param= ${domain.simpleName}Assembler.dtoToDomainBatch(${domain.uncapFirstName});
            int result = this.${domain.uncapFirstName}Application.update${domain.simpleName}ByIdBatch(param);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("更新成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[update${domain.simpleName}ById] error. param:{], msg:{}, trace:{}",${domain.uncapFirstName}, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }

    /**
    * delete ${domain.simpleName} by id
    *
    * @param ${domain.uncapFirstName}Id
    * @return
    */
    @Override
    public Response<Integer> delete${domain.simpleName}ByIdBatch(List<String> ${domain.uncapFirstName}Id,String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[delete${domain.simpleName}ById] request:{}", ${domain.uncapFirstName}Id);
        Response<Integer> response = new Response<>();
        try{
            List<Long> param=new ArrayList<>();
            for(String item : ${domain.uncapFirstName}Id){
                param.add(Long.valueOf(item));
            }

            int result = this.${domain.uncapFirstName}Application.delete${domain.simpleName}ByIdBatch(param);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("删除成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[delete${domain.simpleName}ById] error. param:{], msg:{}, trace:{}",${domain.uncapFirstName}Id, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }

<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
    /**
    * create new ${vo.simpleName}
    *
    * @param ${vo.uncapFirstName}DTOList
    * @return
    */
    @Override
    public Response<Integer> insert${vo.simpleName}Batch(List<${vo.simpleName}DTO> ${vo.uncapFirstName}DTOList,String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[insert${vo.simpleName}Batch] request:{}", ${vo.uncapFirstName}DTOList);
        Response<Integer> response = new Response<>();
        try{
            List<${vo.simpleName}>  param= ${domain.simpleName}Assembler.${vo.uncapFirstName}DtoToDomainBatch(${vo.uncapFirstName}DTOList);
            int result = this.${domain.uncapFirstName}Application.insert${vo.simpleName}Batch(param);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("插入成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[insert${vo.simpleName}Batch] error. param:{], msg:{}, trace:{}",${vo.uncapFirstName}DTOList, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }

    /**
    * update ${vo.simpleName} by id
    *
    * @param ${vo.uncapFirstName}
    * @return
    */
    @Override
    public Response<Integer> update${vo.simpleName}ByIdBatch(List<${vo.simpleName}DTO> ${vo.uncapFirstName},String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[update${vo.simpleName}ById] request:{}", ${vo.uncapFirstName});
        Response<Integer> response = new Response<>();
        try{
            List<${vo.simpleName}> param= ${domain.simpleName}Assembler.${vo.uncapFirstName}DtoToDomainBatch(${vo.uncapFirstName});
            int result = this.${domain.uncapFirstName}Application.update${vo.simpleName}ByIdBatch(param);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("成功更新成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[update${vo.simpleName}ById] error. param:{], msg:{}, trace:{}",${vo.uncapFirstName}, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }

    /**
    * delete ${vo.simpleName} by id
    *
    * @param ${vo.uncapFirstName}Id
    * @return
    */
    @Override
    public Response<Integer> delete${vo.simpleName}ByIdBatch(List<String> ${vo.uncapFirstName}Id,String traceId){
        MDC.put(ConstString.TRACE_ID, traceId);
        logger.debug("[delete${vo.simpleName}ById] request:{}", ${vo.uncapFirstName}Id);
        Response<Integer> response = new Response<>();
        try{
            List<Long> param=new ArrayList<>();
            for(String item : ${vo.uncapFirstName}Id){
                param.add(Long.valueOf(item));
            }

            int result = this.${domain.uncapFirstName}Application.delete${vo.simpleName}ByIdBatch(param);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(result);
            response.setMsg("删除成功");
        } catch (Throwable e) {
            if ( e instanceof ${projectName.capFirst}Exception) {
                response.setMsg(e.getMessage());
                response.setCode(ResponseCode.FAIL);
            } else {
                logger.error("[delete${vo.simpleName}ById] error. param:{], msg:{}, trace:{}",${vo.uncapFirstName}Id, e.getMessage(), e.getStackTrace());
                response.setMsg("服务异常");
                response.setCode(ResponseCode.EXCEPTION);
            }
        }

        return response;
    }
</#list>
</#if>
}
