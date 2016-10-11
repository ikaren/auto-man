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
    <artifactId>${projectName.uncapFirst}-application</artifactId>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.deploy.skip>true</maven.deploy.skip>
    </properties>

    <dependencies>
        <dependency>
            <groupId>${projectBasePackage}</groupId>
            <artifactId>${projectName.uncapFirst}-infra</artifactId>
            <version>${r'${parent.version}'}</version>
        </dependency>
        <dependency>
            <groupId>${projectBasePackage}</groupId>
            <artifactId>${projectName.uncapFirst}-core</artifactId>
            <version>${r'${parent.version}'}</version>
        </dependency>
    </dependencies>

</project>