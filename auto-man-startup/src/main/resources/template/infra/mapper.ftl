package ${projectBasePackage}.infra.repository.sql;

<#if isDomain?string('true','false') == "true">
import ${projectBasePackage}.core.domain.${vo.simpleName};
<#else>
import ${projectBasePackage}.core.vo.${vo.simpleName};
</#if>
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public interface ${vo.simpleName}Mapper {
    <#-- create -->
    /**
     * create new ${vo.simpleName}
     *
     * @param ${vo.uncapFirstName}List
     * @return
     */
    int insert${vo.simpleName}Batch(@Param("list")List<${vo.simpleName}> ${vo.uncapFirstName}List);

    <#-- Retrieve -->
    /**
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    ${vo.simpleName} select${vo.simpleName}ById(@Param("id")long ${vo.uncapFirstName}Id);

    <#-- Update -->
    /**
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    int update${vo.simpleName}ById(${vo.simpleName} ${vo.uncapFirstName});

    <#-- Delete -->
    /**
    * delete ${vo.simpleName} by id
    *
    * @param ${vo.uncapFirstName}Id
    * @return
    */
    int delete${vo.simpleName}ById(@Param("id")long ${vo.uncapFirstName}Id);
}
