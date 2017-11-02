package ${projectBasePackage}.core.repository;

<#if isDomain?string('true','false') == "true">
import ${projectBasePackage}.core.domain.${vo.simpleName};
<#else>
import ${projectBasePackage}.core.vo.${vo.simpleName};
</#if>


import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public interface I${vo.simpleName}Repository {
    <#-- create -->
    /**
     * create new ${vo.simpleName}
     *
     * @param ${vo.uncapFirstName}List
     * @return
     */
    int insert${vo.simpleName}Batch(List<${vo.simpleName}> ${vo.uncapFirstName}List);

    <#-- Retrieve -->
    /**
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    List<${vo.simpleName}> select${vo.simpleName}ByIdBatch(List<Long> ${vo.uncapFirstName}Id);

    <#-- Update -->
    /**
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    int update${vo.simpleName}ByIdBatch(List<${vo.simpleName}> ${vo.uncapFirstName});

    <#-- Delete -->
    /**
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    int delete${vo.simpleName}ByIdBatch(List<Long> ${vo.uncapFirstName}Id);
}