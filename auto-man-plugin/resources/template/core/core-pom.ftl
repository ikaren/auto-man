<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>${projectBasePackage}</groupId>
        <artifactId>${projectName.uncapFirst}</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <modelVersion>4.0.0</modelVersion>
    <packaging>jar</packaging>
    <artifactId>${projectName.uncapFirst}-core</artifactId>
    
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.deploy.skip>true</maven.deploy.skip>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.albert.common</groupId>
            <artifactId>domain</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
    </dependencies>
</project>