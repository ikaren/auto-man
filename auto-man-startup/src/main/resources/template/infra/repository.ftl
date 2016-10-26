package ${projectBasePackage}.infra.repository;

import ${projectBasePackage}.core.repository.I${vo.simpleName}Repository;
<#if isDomain?string('true','false') == "true">
import ${projectBasePackage}.core.domain.${vo.simpleName};
<#else>
import ${projectBasePackage}.core.vo.${vo.simpleName};
</#if>
import ${projectBasePackage}.infra.repository.sql.${vo.simpleName}Mapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
@Named
public class ${vo.simpleName}Repository implements I${vo.simpleName}Repository {

    @Inject
    private ${vo.simpleName}Mapper ${vo.uncapFirstName}Mapper;

    <#-- create -->
    /**
     * create new ${vo.simpleName}
     *
     * @param ${vo.uncapFirstName}List
     * @return
     */
    @Override
    public int insert${vo.simpleName}Batch(List<${vo.simpleName}> ${vo.uncapFirstName}List){
        return this.${vo.uncapFirstName}Mapper.insert${vo.simpleName}Batch(${vo.uncapFirstName}List);
    }

    <#-- Retrieve -->
    /**
     * select ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @Override
    public ${vo.simpleName} select${vo.simpleName}ById(long ${vo.uncapFirstName}Id){
        return this.${vo.uncapFirstName}Mapper.select${vo.simpleName}ById(${vo.uncapFirstName}Id);
    }

    <#-- Update -->
    /**
     * update ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}
     * @return
     */
    @Override
    public int update${vo.simpleName}ById(${vo.simpleName} ${vo.uncapFirstName}){
        return this.${vo.uncapFirstName}Mapper.update${vo.simpleName}ById(${vo.uncapFirstName});
    }

    <#-- Delete -->
    /**
     * delete ${vo.simpleName} by id
     *
     * @param ${vo.uncapFirstName}Id
     * @return
     */
    @Override
    public int delete${vo.simpleName}ById(long ${vo.uncapFirstName}Id){
        return this.${vo.uncapFirstName}Mapper.delete${vo.simpleName}ById(${vo.uncapFirstName}Id);
    }
}
