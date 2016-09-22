<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    	http://www.springframework.org/schema/beans/spring-beans.xsd
		http://code.alibabatech.com/schema/dubbo
		http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

    <dubbo:application id="${PROJECT_NAME}" name="${zkServiceAppName}" owner="${AUTHOR_NAME}" organization="jhd.com"/>

    <dubbo:protocol name="dubbo" port="${dubbo.protocol.port}" threads="20" queues="50"/>

    <dubbo:registry protocol="zookeeper" address="${dubbo.zk.address}" group="${dubbo.registry.group}"
                    id="${PROJECT_NAME}Registry"/>
    <import resource="dubbo-provider.xml"/>
    <import resource="dubbo-consumer.xml"/>
</beans>