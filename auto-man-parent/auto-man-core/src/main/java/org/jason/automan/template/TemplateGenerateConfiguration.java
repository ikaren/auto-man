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
    MAPPER("infra/mapper.ftl", "-infra", "/infra/sql", FileType.JAVA, FileCategory.CODE),
    REPOSITORY_IMPL("infra/repository.ftl", "-infra", "/infra", FileType.JAVA, FileCategory.CODE),
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

    // resources
    PARENT_POM("parent-pom.ftl","","",FileType.XML,FileCategory.POM),
    // core
    CORE_POM("core/core-pom.ftl","-core","",FileType.XML,FileCategory.POM),
    ROOT_CORE("core/root-core.ftl","-core","",FileType.XML,FileCategory.RESOURCES),
    CORE_CONTEXT("module-context.ftl","-core","/spring",FileType.XML,FileCategory.RESOURCES),
    // infra
    INFRA_POM("infra/infra-pom.ftl","-infra","",FileType.XML,FileCategory.POM),
    ROOT_INFRA("infra/root-infra.ftl","-infra","",FileType.XML,FileCategory.RESOURCES),
    INFRA_CONTEXT("module-context.ftl","-infra","/spring",FileType.XML,FileCategory.RESOURCES),
    MYBATIS_MAPPER("","-infra","/mybatis/mapper",FileType.XML,FileCategory.RESOURCES),
    MYBATIS_DB("infra/db-mybatis.ftl","-infra","/mybatis",FileType.XML,FileCategory.RESOURCES),
//    MYBATIS_PERSISTENCE(""),
    PROD_DATABASE_PROPERTIES("infra/databse.ftl","-infra","/props",FileType.XML,FileCategory.RESOURCES),
    BETA_DATABASE_PROPERTIES(""),
    ALPHA_DATABASE_PROPERTIES(""),
    DEV_DATABASE_PROPERTIES(""),
    // application
    APPLICATION_POM(""),
    ROOT_APPLICATION(""),
    APPLICATION_CONTEXT(""),
    // facade
    FACADE_POM(""),
    // facade impl
    FACADE_IMPL_POM(""),
    ROOT_FACADE_IMPL(""),
    FACADE_IMPL_CONTEXT(""),
    DUBBO(""),
    DUBBO_PROVIDER(""),
    MATA_INF_ROOT(""),
    PROD_DUBBO_PROPERTIES(""),
    BETA_DUBBO_PROPERTIES(""),
    ALPHA_DUBBO_PROPERTIES(""),
    DEV_DUBBO_PROPERTIES(""),

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
