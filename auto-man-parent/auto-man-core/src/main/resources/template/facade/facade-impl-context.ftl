<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-3.2.xsd">

    <!--扫描已经注册的bean-->
    <context:component-scan base-package="${projectBasePackage}.facade">
    </context:component-scan>

    <context:property-placeholder location="classpath*:props/*.properties" ignore-unresolvable="true"/>

    <import resource="classpath:/dubbo/dubbo.xml"/>
</beans>