<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    	http://www.springframework.org/schema/beans/spring-beans.xsd
		http://code.alibabatech.com/schema/dubbo
		http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <!-- Created by Auto-Man v1.0.0 on ${.now}  -->

    <dubbo:application id="${projectName.uncapFirst}DubboApplication" name="${projectLayer}-${projectName.uncapFirst}" owner="${author}" organization="hans.jhd"/>

    <dubbo:protocol name="dubbo" port="${r'${dubbo.protocol.port}'}" threads="20" queues="50"/>

    <dubbo:registry protocol="zookeeper" address="${r'${dubbo.zk.address}'}" group="${r'${dubbo.registry.group}'}"
                    id="${projectName.uncapFirst}Registry"/>
    <import resource="dubbo-provider.xml"/>
</beans>