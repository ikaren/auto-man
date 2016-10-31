<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    	http://www.springframework.org/schema/beans/spring-beans.xsd
		http://code.alibabatech.com/schema/dubbo
		http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <#if domains?exists>
    <#list domains as domain>
    <!-- Created by Auto-Man v1.0.0 on ${.now}  -->
    <dubbo:service
        ref="${domain.uncapFirstName}CommandFacade"
        interface="${projectBasePackage}.facade.I${domain.simpleName}CommandFacade"
        version="1.0.0"
        cluster="failfast"
        timeout="50000"
        registry="${projectName.uncapFirst}Registry">
    </dubbo:service>
    <bean id="${domain.uncapFirstName}CommandFacade" class="${projectBasePackage}.facade.impl.${domain.simpleName}CommandFacade"/>
    <!-- Created by Auto-Man v1.0.0 on ${.now}  -->
    <dubbo:service
        ref="${domain.uncapFirstName}QueryFacade"
        interface="${projectBasePackage}.facade.I${domain.simpleName}QueryFacade"
        version="1.0.0"
        cluster="failfast"
        timeout="50000"
        registry="${projectName.uncapFirst}Registry">
    </dubbo:service>
    <bean id="${domain.uncapFirstName}CommandFacade" class="${projectBasePackage}.facade.impl.${domain.simpleName}QueryFacade"/>
    </#list>
    </#if>
</beans>

