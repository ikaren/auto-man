package org.jason.automan.template;

import org.jason.automan.bean.FileCategory;
import org.jason.automan.bean.FileType;

/**
 * Created by Jason.Xia on 16/10/9.
 */
public enum TemplateGenerateConfiguration {
    DOMAIN("core/domain-specified.ftl", "-core", "/core/domain", FileType.JAVA, FileCategory.CODE),
    VO("core/vo.ftl", "-core", "/core/vo", FileType.JAVA, FileCategory.CODE),
    EXCEPTION("core/exception.ftl", "-core", "/core/exception", FileType.JAVA, FileCategory.CODE),
    REPOSITORY("core/repository.ftl", "-core", "/core/repository", FileType.JAVA, FileCategory.CODE),
    MAPPER("infra/mapper.ftl", "-infra", "/infra/repository/sql", FileType.JAVA, FileCategory.CODE),
    REPOSITORY_IMPL("infra/repository.ftl", "-infra", "/infra/repository", FileType.JAVA, FileCategory.CODE),
    APPLICATION("application/application-interface.ftl", "-application", "/application", FileType.JAVA,
            FileCategory.CODE),
    APPLICATION_IMPL("application/application.ftl", "-application", "/application/impl", FileType.JAVA, FileCategory
            .CODE),
    DTO("facade/dto.ftl", "-facade", "/facade/dto", FileType.JAVA, FileCategory.CODE),
    FACADE_QUERY("facade/facade-query.ftl", "-facade", "/facade", FileType.JAVA, FileCategory.CODE),
    FACADE_COMMAND("facade/facade-command.ftl", "-facade", "/facade", FileType.JAVA, FileCategory.CODE),
    FACADE_QUERY_IMPL("facade/facade-query-impl.ftl", "-facade-impl", "/facade/impl", FileType.JAVA, FileCategory.CODE),
    FACADE_COMMAND_IMPL("facade/facade-command-impl.ftl", "-facade-impl", "/facade/impl", FileType.JAVA, FileCategory
            .CODE),
    ASSEMBLER("facade/assembler.ftl", "-facade-impl", "/facade/impl/assembler", FileType.JAVA, FileCategory.CODE),
    RESPONSE_CODE("facade/response-code.ftl", "-facade", "/facade/dto", FileType.JAVA, FileCategory.CODE),

    // resources
    PARENT_POM("parent-pom.ftl", "", "", FileType.XML, FileCategory.POM),
    // core
    CORE_POM("core/core-pom.ftl", "-core", "", FileType.XML, FileCategory.POM),
    ROOT_CORE("core/root-core.ftl", "-core", "", FileType.XML, FileCategory.RESOURCES),
    CORE_CONTEXT("module-context.ftl", "-core", "/spring", FileType.XML, FileCategory.RESOURCES),
    // infra
    INFRA_POM("infra/infra-pom.ftl", "-infra", "", FileType.XML, FileCategory.POM),
    ROOT_INFRA("infra/root-infra.ftl", "-infra", "", FileType.XML, FileCategory.RESOURCES),
    INFRA_CONTEXT("module-context.ftl", "-infra", "/spring", FileType.XML, FileCategory.RESOURCES),
    MYBATIS_MAPPER("infra/sql-mapper.ftl", "-infra", "/mybatis/mapper", FileType.XML, FileCategory.RESOURCES),
    MYBATIS_DB("infra/db-mybatis.ftl", "-infra", "/mybatis", FileType.XML, FileCategory.RESOURCES),
    MYBATIS_PERSISTENCE("infra/persistence-context.ftl", "-infra", "/mybatis", FileType.XML, FileCategory.RESOURCES),
    PROD_DATABASE_PROPERTIES("infra/database.ftl", "-infra", "/props", FileType.PROPERTIES, FileCategory
            .RESOURCES_PROD),
    BETA_DATABASE_PROPERTIES("infra/database.ftl", "-infra", "/props", FileType.PROPERTIES, FileCategory
            .RESOURCES_BETA),
    ALPHA_DATABASE_PROPERTIES("infra/database.ftl", "-infra", "/props", FileType.PROPERTIES, FileCategory
            .RESOURCES_ALPHA),
    DEV_DATABASE_PROPERTIES("infra/database.ftl", "-infra", "/props", FileType.PROPERTIES, FileCategory.RESOURCES_DEV),
    // application
    APPLICATION_POM("application/application-pom.ftl", "-application", "", FileType.XML, FileCategory.POM),
    ROOT_APPLICATION("application/root-application.ftl", "-application", "", FileType.XML, FileCategory.RESOURCES),
    APPLICATION_CONTEXT("module-context.ftl", "-application", "/spring", FileType.XML, FileCategory.RESOURCES),
    // facade
    FACADE_POM("facade/facade-pom.ftl", "-facade", "", FileType.XML, FileCategory.POM),
    // facade impl
    FACADE_IMPL_POM("facade/facade-impl-pom.ftl", "-facade-impl", "", FileType.XML, FileCategory.POM),
    ROOT_FACADE_IMPL("facade/root-facade-impl.ftl", "-facade-impl", "", FileType.XML, FileCategory.RESOURCES),
    FACADE_IMPL_CONTEXT("module-context.ftl", "-facade-impl", "/spring", FileType.XML, FileCategory.RESOURCES),
    DUBBO("facade/dubbo.ftl", "-facade-impl", "/dubbo", FileType.XML, FileCategory.RESOURCES),
    DUBBO_PROVIDER("facade/dubbo-provider.ftl", "-facade-impl", "/dubbo", FileType.XML, FileCategory.RESOURCES),
    META_INF_ROOT("facade/root.ftl", "-facade-impl", "/META-INF/spring", FileType.XML, FileCategory.RESOURCES),
    PROD_DUBBO_PROPERTIES("facade/dubbo-properties.ftl", "-facade-impl", "/props", FileType.PROPERTIES, FileCategory
            .RESOURCES_PROD),
    BETA_DUBBO_PROPERTIES("facade/dubbo-properties.ftl", "-facade-impl", "/props", FileType.PROPERTIES, FileCategory
            .RESOURCES_BETA),
    ALPHA_DUBBO_PROPERTIES("facade/dubbo-properties.ftl", "-facade-impl", "/props", FileType.PROPERTIES, FileCategory
            .RESOURCES_ALPHA),
    DEV_DUBBO_PROPERTIES("facade/dubbo-properties.ftl", "-facade-impl", "/props", FileType.PROPERTIES, FileCategory
            .RESOURCES_DEV),
    LOG4J("facade/log4j.ftl", "-facade-impl", "", FileType.PROPERTIES, FileCategory.RESOURCES),
    ASSEMBLY("facade/assembly.ftl", "-facade-impl", "", FileType.XML, FileCategory.RESOURCES);

    public String templateName;
    public String modulePath;
    public String targetFilePath;
    public FileType fileType;
    public FileCategory fileCategory;

    TemplateGenerateConfiguration(String templateName, String modulePath, String targetFilePath, FileType fileType,
                                  FileCategory fileCategory) {
        this.templateName = templateName;
        this.modulePath = modulePath;
        this.targetFilePath = targetFilePath;
        this.fileType = fileType;
        this.fileCategory = fileCategory;
    }
}
