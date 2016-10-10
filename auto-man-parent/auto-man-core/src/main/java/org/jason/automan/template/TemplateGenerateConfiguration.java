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
    ASSEMBLER("facade/assembler.ftl", "-facade-impl", "/facade/impl/assembler", FileType.JAVA, FileCategory.CODE);

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
