<?xml version="1.0" encoding="UTF-8"?>
<!-- Created by Auto-Man v1.0.0 on ${.now}  -->
${r'<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >'}
<mapper namespace="${projectBasePackage}.infra.sql.${vo.simpleName}Mapper">
    <resultMap id="BASE_RESULT_MAP" type="<#if isDomain?string('true','false') == "true">${projectBasePackage}.core.domain.${vo.simpleName}<#else>${projectBasePackage}.core.vo.${vo.simpleName}</#if>">
        <result column="id" property="id" jdbcType="BIGINT"/>
        <#if vo.properties?exists>
        <#list vo.properties as property>
        <result column="${property.tableColumn}" property="${property.propertyName}" jdbcType="${property.jdbcType}"/>
        </#list>
        </#if>
        <result column="others" property="others" jdbcType="VARCHAR"/>
        <result column="state" property="state" jdbcType="TINYINT"/>
        <result column="updated_at" property="updatedAt" jdbcType="TIMESTAMP"/>
        <result column="created_at" property="createdAt" jdbcType="TIMESTAMP"/>
    </resultMap>

    <sql id="BASE_COLUMN">
    id,<#if vo.properties?exists><#list vo.properties as property>${property.tableColumn}<#if property?has_next>,</#if></#list></#if>,others,state,updated_at,created_at
    </sql>
    <sql id="INSERT_COLUMN">
        id,<#if vo.properties?exists><#list vo.properties as property>${property.tableColumn}<#if property?has_next>,</#if></#list></#if>,others
    </sql>

    <!-- 插入 -->
    <insert id="insert${vo.simpleName}Batch" parameterType="<#if isDomain?string('true','false') == "true">${projectBasePackage}.core.domain.${vo.simpleName}<#else>${projectBasePackage}.core.vo.${vo.simpleName}</#if>">
        INSERT INTO `${vo.tableName}` (<include refid="INSERT_COLUMN"/>)
        VALUES
        <foreach collection="list" index="id" item="item" separator=",">
        (${r'#{item.id}'},<#if vo.properties?exists><#list vo.properties as property>${r'#{item.'}${property.tableColumn}${r'}'}<#if property?has_next>,</#if></#list></#if>,${r'#{item.others}'})
        </foreach>
    </insert>

    <select id="select${vo.simpleName}ById" resultMap="BASE_RESULT_MAP">
        SELECT
        <include refid="BASE_COLUMN"/>
        FROM `${vo.tableName}` WHERE state=1 AND id = ${r'#{id}'}
    </select>

    <update id="update${vo.simpleName}ById">
        UPDATE `${vo.tableName}`
        SET
        <trim suffix="" suffixOverrides=",">
        <#if vo.properties?exists>
        <#list vo.properties as property>
        <if test="${property.propertyName}!=null">
        ${property.tableColumn} = ${r'#{'}${property.propertyName}${r'}'},
        </if>
        </#list>
        </#if>
        </trim>
        WHERE state = 1 AND id = ${r'#{id}'} LIMIT 1
    </update>

    <update id="delete${vo.simpleName}ById">
        UPDATE `${vo.tableName}`
        SET state = 0
        WHERE state = 1 AND id = ${r'#{id}'} LIMIT 1
    </update>
</mapper>