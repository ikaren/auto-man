package ${projectBasePackage}.infra.sql;

import ${projectBasePackage}.core.vo.${vo.simpleName};
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
    public int insert${vo.simpleName}Batch(@Param("list")List<${vo.simpleName}> ${vo.uncapFirstName}List);

    <#-- Retrieve -->
    /**
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    public ${vo.simpleName} select${vo.simpleName}ById(@Param("id")long ${vo.uncapFirstName}Id);

    <#-- Update -->
    /**
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    public int update${vo.simpleName}ById(${vo.simpleName} ${vo.uncapFirstName});

    <#-- Delete -->
    /**
    * delete ${vo.simpleName} by id
    *
    * @param ${vo.uncapFirstName}Id
    * @return
    */
    public int delete${vo.simpleName}ById(@Param("id")long ${vo.uncapFirstName}Id);
}
