<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>${PARENT_ARTIFACT}</artifactId>
        <groupId>${GROUP_ID}</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>jar</packaging>
    <artifactId>${PROJECT_NAME}-facade</artifactId>
    <dependencies>
        <dependency>
            <groupId>com.hans.jhd.infra.themis</groupId>
            <artifactId>themis-service</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.albert.common</groupId>
            <artifactId>domain</artifactId>
        </dependency>
    </dependencies>

</project>