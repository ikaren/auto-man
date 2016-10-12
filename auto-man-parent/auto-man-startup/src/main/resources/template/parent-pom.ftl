<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.hans.jhd.common.pom</groupId>
        <artifactId>common-pom</artifactId>
        <version>1.0.0-SNAPSHOT</version>

    </parent>

    <groupId>${projectBasePackage}</groupId>
    <artifactId>${projectName.uncapFirst}</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>${projectName.uncapFirst}-core</module>
        <module>${projectName.uncapFirst}-infra</module>
        <module>${projectName.uncapFirst}-application</module>
        <module>${projectName.uncapFirst}-facade</module>
        <module>${projectName.uncapFirst}-facade-impl</module>
    </modules>

</project>