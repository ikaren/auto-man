package ${projectBasePackage}.facade.dto;

import com.hans.jhd.infra.themis.service.annotation.ThemisFieldConfig;
import org.albert.common.domain.CommonEntity;
<#if vo.properties?exists>
    <#list vo.properties as property>
        <#if property.javaType == "Date">
import java.util.Date;
            <#break>
        </#if>
    </#list>
</#if>
/**
* Created by Auto-Man v1.0.0 on ${.now}
*/
public class ${vo.simpleName}DTO extends CommonEntity{
    private String id;
    private String others;
    private Integer state;
    private Date createdAt;
    private Date updatedAt;
<#if vo.properties?exists>
<#list vo.properties as property>
    /**
    *  <#if property.desc?has_content>${property.desc}</#if>
    */
    @ThemisFieldConfig(desc = "<#if property.desc?has_content>${property.desc}<#else>${vo.simpleName}</#if>")
    private <#if property.javaType == "Long">String<#else >${property.javaType}</#if> ${property.propertyName};
</#list>
</#if>
<#if vo.properties?exists>
<#list vo.properties as property>
    public <#if property.javaType == "Long">String<#else >${property.javaType}</#if> get${property.propertyName?cap_first}() {
        return this.${property.propertyName};
    }
    public void set${property.propertyName?cap_first}(<#if property.javaType == "Long">String<#else >${property.javaType}</#if> ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }
</#list>
</#if>

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}