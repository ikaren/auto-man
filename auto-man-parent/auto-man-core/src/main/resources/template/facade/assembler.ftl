package ${projectBasePackage}.facade.impl.assembler;

import ${projectBasePackage}.core.domain.${domain.simpleName};
import ${projectBasePackage}.facade.dto.${domain.simpleName}DTO;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public class ExpressAssembler {

    public static List<${domain.simpleName}> dtoToDomainBatch(List<${domain.simpleName}DTO> params) throws Exception {
        if (null == params) {
            return null;
        }

        List<${domain.simpleName}> result = new ArrayList<>();
        for (${domain.simpleName}DTO item : params) {
            result.add(dtoToDomain(item));
        }

        return result;
    }

    public static ${domain.simpleName} dtoToDomain(${domain.simpleName}DTO param) throws Exception {
        if (null == param) {
            return null;
        }

        ${domain.simpleName} result = new ${domain.simpleName}();
        BeanUtils.copyProperties(result, param);
        if (null == result.getOthers()) {
            result.setOthers("{}");
        }

        return result;
    }

    public static List<${domain.simpleName}DTO> domainToDtoBatch(List<${domain.simpleName}> params) throws Exception {
        if (null == params) {
        return null;
        }

        List<${domain.simpleName}DTO> result = new ArrayList<>();
        for (${domain.simpleName} item : params) {
            result.add(domainToDto(item));
        }

        return result;
    }

    public static ${domain.simpleName}DTO domainToDto(${domain.simpleName} param) throws Exception{
        if (null == param) {
            return null;
        }

        ${domain.simpleName}DTO result = new ${domain.simpleName}DTO();
        BeanUtils.copyProperties(result, param);
        return result;
    }
<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
    public static List<${vo.simpleName}> ${vo.uncapFirstName}DtoToDomainBatch(List<${vo.simpleName}DTO> params) throws Exception {
        if (null == params) {
            return null;
        }

        List<${vo.simpleName}> result = new ArrayList<>();
        for (${vo.simpleName}DTO item : params) {
            result.add(${vo.uncapFirstName}DtoToDomain(item));
        }

        return result;
    }

    public static ${vo.simpleName} ${vo.uncapFirstName}DtoToDomain(${vo.simpleName}DTO param) throws Exception {
        if (null == param) {
            return null;
        }

        ${vo.simpleName} result = new ${vo.simpleName}();
        BeanUtils.copyProperties(result, param);
        if (null == result.getOthers()) {
            result.setOthers("{}");
        }

        return result;
    }

    public static List<${vo.simpleName}DTO> ${vo.uncapFirstName}DomainToDtoBatch(List<${vo.simpleName}> params) throws Exception {
        if (null == params) {
            return null;
        }

        List<${vo.simpleName}DTO> result = new ArrayList<>();
        for (${vo.simpleName} item : params) {
            result.add(${vo.uncapFirstName}DomainToDto(item));
        }

        return result;
    }

    public static ${vo.simpleName}DTO ${vo.uncapFirstName}DomainToDto(${vo.simpleName} param) throws Exception{
        if (null == param) {
            return null;
        }

        ${vo.simpleName}DTO result = new ${vo.simpleName}DTO();
        BeanUtils.copyProperties(result, param);
        return result;
    }
</#list>
</#if>
}
