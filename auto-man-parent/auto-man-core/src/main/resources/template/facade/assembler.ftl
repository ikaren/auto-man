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
<#if domain.bindingVOs?exists>
<#list domain.bindingVOs as vo>
    public
</#list>
</#if>
}
