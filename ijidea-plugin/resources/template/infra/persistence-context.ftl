<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:property-placeholder location="classpath*:props/database.properties"/>

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
          init-method="init" destroy-method="close">
        <property name="driverClassName" value="${r'${db.jdbc.driver}'}"/>
        <property name="url" value="${r'${db.jdbc.connection.url}'}"/>
        <property name="username" value="${r'${db.jdbc.username}'}"/>
        <property name="password" value="${r'${db.jdbc.password}'}"/>
        <!-- 初始化连接数量 -->
        <property name="initialSize" value="${r'${druid.initialSize}'}"/>
        <!-- 最大并发连接数 -->
        <property name="maxActive" value="${r'${druid.maxActive}'}"/>
        <!-- 最小空闲连接数 -->
        <property name="minIdle" value="${r'${druid.minIdle}'}"/>
        <!-- 配置获取连接等待超时的时间 -->
        <property name="maxWait" value="${r'${druid.maxWait}'}"/>
        <!-- 超过时间限制是否回收 -->
        <property name="removeAbandoned" value="${r'${druid.removeAbandoned}'}"/>
        <!-- 超过时间限制多长； -->
        <property name="removeAbandonedTimeout" value="${r'${druid.removeAbandonedTimeout}'}"/>
        <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        <property name="timeBetweenEvictionRunsMillis" value="${r'${druid.timeBetweenEvictionRunsMillis}'}"/>
        <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
        <property name="minEvictableIdleTimeMillis" value="${r'${druid.minEvictableIdleTimeMillis}'}"/>
        <!-- 用来检测连接是否有效的sql，要求是一个查询语句-->
        <property name="validationQuery" value="select 1"/>
        <!-- 申请连接的时候检测 -->
        <property name="testWhileIdle" value="${r'${druid.testWhileIdle}'}"/>
        <!-- 申请连接时执行validationQuery检测连接是否有效，配置为true会降低性能 -->
        <property name="testOnBorrow" value="${r'${druid.testOnBorrow}'}"/>
        <!-- 归还连接时执行validationQuery检测连接是否有效，配置为true会降低性能  -->
        <property name="testOnReturn" value="${r'${druid.testOnReturn}'}"/>
        <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
        <property name="poolPreparedStatements" value="${r'${druid.poolPreparedStatements}'}"/>
        <property name="maxPoolPreparedStatementPerConnectionSize"
                  value="${r'${druid.maxPoolPreparedStatementPerConnectionSize}'}"/>
        <!--属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
                监控统计用的filter:stat
                日志用的filter:log4j
               防御SQL注入的filter:wall -->
        <property name="filters" value="${r'${druid.filters}'}"/>
    </bean>

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--<context:annotation-config/>-->

    <!-- Instructs Spring to perfrom declarative transaction managemenet on
    annotated classes -->
    <tx:annotation-driven transaction-manager="transactionManager" />

</beans>
