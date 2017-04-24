package org.jason.automan.parser.support;

import org.jason.automan.Processer;
import org.jason.automan.ProcesserContext;
import org.jason.automan.ProcesserFactory;
import org.jason.automan.ProgressListener;
import org.jason.automan.bean.*;
import org.jason.automan.parser.AbstractParser;
import org.jason.automan.parser.Transporter;
import org.jason.automan.parser.bean.*;
import org.jason.automan.template.SkeletonGenerateConfiguration;
import org.jason.automan.template.TemplateGenerateConfiguration;

import java.util.*;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class XmlParser extends AbstractParser {
    private Transporter transporter;
    private ProcesserFactory processerFactory;
    private ProgressListener listener;

    public XmlParser() {
        init();
    }

    public void init() {
        transporter = new XmlTransporter();
        processerFactory = new ProcesserFactory();
    }

    public void addNewDomainOrVO(String in, String templatePath, String projectDir, String projectName,
                                 ProgressListener listener, boolean supportPlugin) {
        this.listener = listener;
        List<Project> transport = transporter.transport(in, templatePath);
        if (1 != transport.size()) {
            if (null != listener) {
                listener.update("add new domain allow only one project tag");
                return;
            }

            throw new IllegalArgumentException("add new domain allow only one project tag");
        }

        Project project = transport.get(0);
        project.setProjectDir(projectDir);
        Processer processer = this.processerFactory.createProcesser(false, new ProcesserContext(projectName, projectDir,
                project.getPackageName(), templatePath, supportPlugin), listener);
        List<Domain> domains = getDomains(project.getTables());
        for (Domain item : domains) {
            generateCodeFile(item, processer);
        }
    }

    public void parser(String in, String templatePath) {
        parser(in, templatePath, null, null, false);
    }

    public void parser(String in, String templatePath, String projectDir, ProgressListener listener, boolean
            supportPlugin) {
        this.listener = listener;
        List<Project> projects = transporter.transport(in, templatePath);
        if (null != projectDir && projectDir.length() != 0) {
            for (Project item : projects) {
                item.setProjectDir(projectDir);
            }
        }

        for (Project item : projects) {
            double projectWeight = 1.0 / projects.size();
            Processer processer = processerFactory.createProcesser(true, new ProcesserContext(item.getProjectName(),
                    item.getProjectDir(), item.getPackageName(), item.getTemplateRoot(), supportPlugin), listener);

            List<Domain> domains = getDomains(item.getTables());
            processer.generate(TemplateGenerateConfiguration.EXCEPTION, buildCodeRootMap
                    (TemplateGenerateConfiguration
                            .EXCEPTION, null), item.getProjectName().substring(0, 1).toUpperCase() + item
                    .getProjectName()
                    .substring(1) + "Exception");
            processer.generate(TemplateGenerateConfiguration.RESPONSE_CODE, buildCodeRootMap
                    (TemplateGenerateConfiguration
                            .RESPONSE_CODE, null), "ResponseCode");
            updateProgress(projectWeight * ProgressWeight.INITIAL);
            for (Domain domain : domains) {
                generateCodeFile(domain, processer);
                updateProgress(projectWeight * (1.0 / domains.size()) * ProgressWeight.DOMAIN);
            }

            generatePomFile(processer);
            updateProgress(projectWeight * ProgressWeight.POM);
            generateResourcesFile(processer, domains, item.getPackageName());
            updateProgress(projectWeight * ProgressWeight.RESOURCES_FILE);
            generateDatabaseProps(processer, mapConvertToList(item.getDataSourceConfig().getEnvConfig()));
            updateProgress(projectWeight * ProgressWeight.DATABASE_PROPS);
            generateDubboProps(processer, mapConvertToList(item.getDubboConfig().getEnvConfig()));
            updateProgress(projectWeight * ProgressWeight.DUBBO_PROPS);
            generateSkeleton(processer);
            updateProgress(projectWeight * ProgressWeight.SKELETON);
        }
    }

    private void updateProgress(double increase) {
        if (null == listener) {
            return;
        }

        listener.update(increase);
    }

    private void generateCodeFile(Domain domain, Processer processer) {
        processer.generate(TemplateGenerateConfiguration.DOMAIN, buildCodeRootMap(TemplateGenerateConfiguration
                .DOMAIN, domain), domain.getSimpleName());
        processer.generate(TemplateGenerateConfiguration.REPOSITORY, buildCodeRootMap(TemplateGenerateConfiguration
                .REPOSITORY, domain), "I" + domain.getSimpleName() + "Repository");
        processer.generate(TemplateGenerateConfiguration.MAPPER, buildCodeRootMap(TemplateGenerateConfiguration
                .MAPPER, domain), domain.getSimpleName() + "Mapper");
        processer.generate(TemplateGenerateConfiguration.REPOSITORY_IMPL, buildCodeRootMap
                (TemplateGenerateConfiguration.REPOSITORY_IMPL, domain), domain.getSimpleName() + "Repository");
        processer.generate(TemplateGenerateConfiguration.DTO, buildCodeRootMap(TemplateGenerateConfiguration
                .DTO, domain), domain.getSimpleName() + "DTO");
        processer.generate(TemplateGenerateConfiguration.FACADE_COMMAND, buildCodeRootMap
                (TemplateGenerateConfiguration.FACADE_COMMAND, domain), "I" + domain.getSimpleName() + "CommandFacade");
        processer.generate(TemplateGenerateConfiguration.FACADE_COMMAND_IMPL, buildCodeRootMap
                (TemplateGenerateConfiguration.FACADE_COMMAND_IMPL, domain), domain.getSimpleName() + "CommandFacade");
        processer.generate(TemplateGenerateConfiguration.FACADE_QUERY, buildCodeRootMap
                (TemplateGenerateConfiguration.FACADE_QUERY, domain), "I" + domain.getSimpleName() + "QueryFacade");
        processer.generate(TemplateGenerateConfiguration.FACADE_QUERY_IMPL, buildCodeRootMap
                (TemplateGenerateConfiguration.FACADE_QUERY_IMPL, domain), domain.getSimpleName() + "QueryFacade");
        processer.generate(TemplateGenerateConfiguration.ASSEMBLER, buildCodeRootMap(TemplateGenerateConfiguration
                .ASSEMBLER, domain), domain.getSimpleName() + "Assembler");
        processer.generate(TemplateGenerateConfiguration.APPLICATION, buildCodeRootMap(TemplateGenerateConfiguration
                .APPLICATION, domain), "I" + domain.getSimpleName() + "Application");
        processer.generate(TemplateGenerateConfiguration.APPLICATION_IMPL, buildCodeRootMap
                (TemplateGenerateConfiguration.APPLICATION_IMPL, domain), domain.getSimpleName() + "Application");
        processer.generate(TemplateGenerateConfiguration.MYBATIS_MAPPER, buildCodeRootMap
                (TemplateGenerateConfiguration.MYBATIS_MAPPER, domain), domain.getSimpleName() + "Mapper");
        if (null != domain.getBindingVOs()) {
            for (ValueObject valueObject : domain.getBindingVOs()) {
                processer.generate(TemplateGenerateConfiguration.VO, buildCodeRootMap
                        (TemplateGenerateConfiguration.VO, valueObject, false), valueObject.getSimpleName());
                processer.generate(TemplateGenerateConfiguration.REPOSITORY, buildCodeRootMap
                        (TemplateGenerateConfiguration.REPOSITORY, valueObject, false), "I" + valueObject
                        .getSimpleName() + "Repository");
                processer.generate(TemplateGenerateConfiguration.REPOSITORY_IMPL, buildCodeRootMap
                        (TemplateGenerateConfiguration.REPOSITORY_IMPL, valueObject, false), valueObject
                        .getSimpleName() + "Repository");
                processer.generate(TemplateGenerateConfiguration.MAPPER, buildCodeRootMap
                        (TemplateGenerateConfiguration.MAPPER, valueObject, false), valueObject.getSimpleName() +
                        "Mapper");
                processer.generate(TemplateGenerateConfiguration.MYBATIS_MAPPER, buildCodeRootMap
                        (TemplateGenerateConfiguration.MYBATIS_MAPPER, valueObject, false), valueObject.getSimpleName
                        () + "Mapper");
                processer.generate(TemplateGenerateConfiguration.DTO, buildCodeRootMap
                        (TemplateGenerateConfiguration.DTO, valueObject, false), valueObject.getSimpleName() + "DTO");
            }
        }
    }

    private void generateResourcesFile(Processer processer, List<Domain> domains, String packageName) {
        processer.generate(TemplateGenerateConfiguration.ROOT_CORE, null, "root-core");
        processer.generate(TemplateGenerateConfiguration.ROOT_INFRA, null, "root-infra");
        processer.generate(TemplateGenerateConfiguration.ROOT_APPLICATION, null, "root-application");
        processer.generate(TemplateGenerateConfiguration.ROOT_FACADE_IMPL, null, "root-facade-impl");


        processer.generate(TemplateGenerateConfiguration.CORE_CONTEXT, getBaseModulePackage("core", packageName),
                "core-context");
        processer.generate(TemplateGenerateConfiguration.INFRA_CONTEXT, getBaseModulePackage("infra", packageName),
                "infra-context");
        processer.generate(TemplateGenerateConfiguration.APPLICATION_CONTEXT, getBaseModulePackage("application",
                packageName), "application-context");
        processer.generate(TemplateGenerateConfiguration.FACADE_IMPL_CONTEXT, getBaseModulePackage("facade",
                packageName), "facade-impl-context");

        processer.generate(TemplateGenerateConfiguration.MYBATIS_DB, null, "db-mybatis");
        processer.generate(TemplateGenerateConfiguration.MYBATIS_PERSISTENCE, null, "persistence-context");

        processer.generate(TemplateGenerateConfiguration.DUBBO, null, "dubbo");
        Map<String, Object> root = new HashMap<>();
        root.put("domains", domains);
        processer.generate(TemplateGenerateConfiguration.DUBBO_PROVIDER, root, "dubbo-provider");

        processer.generate(TemplateGenerateConfiguration.LOG4J, null, "log4j");
        processer.generate(TemplateGenerateConfiguration.META_INF_ROOT, null, "root");
        processer.generate(TemplateGenerateConfiguration.ASSEMBLY, null, "assembly");

    }

    private void generateDatabaseProps(Processer processer, List<DatabaseConfig> datas) {
        if (null == datas) {
            return;
        }

        for (DatabaseConfig item : datas) {
            Map<String, Object> root = new HashMap<>();
            root.put("env", item.getEnv());
            root.put("host", item.getHost());
            root.put("port", item.getPort());
            root.put("databaseName", item.getDbName());
            root.put("username", item.getUsername());
            root.put("password", item.getPassword());
            switch (item.getEnv()) {
                case Environment.PROD:
                    processer.generate(TemplateGenerateConfiguration.PROD_DATABASE_PROPERTIES, root, "database");
                    break;
                case Environment.BETA:
                    processer.generate(TemplateGenerateConfiguration.BETA_DATABASE_PROPERTIES, root, "database");
                    break;
                case Environment.ALPHA:
                    processer.generate(TemplateGenerateConfiguration.ALPHA_DATABASE_PROPERTIES, root, "database");
                    break;
                case Environment.DEV:
                    processer.generate(TemplateGenerateConfiguration.DEV_DATABASE_PROPERTIES, root, "database");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Environment: " + item.getEnv());
            }
        }
    }

    private void generateDubboProps(Processer processer, List<DubboConfig> datas) {
        if (null == datas) {
            return;
        }

        for (DubboConfig item : datas) {
            Map<String, Object> root = new HashMap<>();
            root.put("env", item.getEnv());
            root.put("port", item.getPort());
            root.put("zkAddress", item.getZkAddress());
            root.put("group", item.getGroup());
            switch (item.getEnv()) {
                case Environment.PROD:
                    processer.generate(TemplateGenerateConfiguration.PROD_DUBBO_PROPERTIES, root, "dubbo");
                    break;
                case Environment.BETA:
                    processer.generate(TemplateGenerateConfiguration.BETA_DUBBO_PROPERTIES, root, "dubbo");
                    break;
                case Environment.ALPHA:
                    processer.generate(TemplateGenerateConfiguration.ALPHA_DUBBO_PROPERTIES, root, "dubbo");
                    break;
                case Environment.DEV:
                    processer.generate(TemplateGenerateConfiguration.DEV_DUBBO_PROPERTIES, root, "dubbo");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Environment: " + item.getEnv());
            }
        }
    }

    private Map<String, Object> getBaseModulePackage(String moduleName, String packageName) {
        Map<String, Object> result = new HashMap<>();
        result.put("baseModulePackage", packageName + "." + moduleName);
        return result;
    }

    private void generatePomFile(Processer processer) {
        processer.generate(TemplateGenerateConfiguration.PARENT_POM, null, "pom");
        processer.generate(TemplateGenerateConfiguration.CORE_POM, null, "pom");
        processer.generate(TemplateGenerateConfiguration.INFRA_POM, null, "pom");
        processer.generate(TemplateGenerateConfiguration.APPLICATION_POM, null, "pom");
        processer.generate(TemplateGenerateConfiguration.FACADE_POM, null, "pom");
        processer.generate(TemplateGenerateConfiguration.FACADE_IMPL_POM, null, "pom");
    }

    private <T> Map<String, Object> buildCodeRootMap(TemplateGenerateConfiguration templateKey, T value) {
        return buildCodeRootMap(templateKey, value, true);
    }

    private <T> Map<String, Object> buildCodeRootMap(TemplateGenerateConfiguration templateKey, T value, boolean
            isDomain) {
        Map<String, Object> root = new HashMap<>();
        switch (templateKey) {
            case FACADE_COMMAND:
            case FACADE_COMMAND_IMPL:
            case FACADE_QUERY:
            case FACADE_QUERY_IMPL:
            case ASSEMBLER:
            case APPLICATION:
            case APPLICATION_IMPL:
            case DOMAIN:
                root.put("domain", value);
                root.put("isDomain", true);
                break;
            case REPOSITORY:
            case MAPPER:
            case REPOSITORY_IMPL:
            case DTO:
            case MYBATIS_MAPPER:
            case VO:
                root.put("vo", value);
                root.put("isDomain", isDomain);
                break;
            case EXCEPTION:
                break;
            case RESPONSE_CODE:
                break;
            default:
                throw new IllegalArgumentException("Unsupported Template Configuration:" + templateKey);
        }

        return root;
    }

    private List<Domain> getDomains(Map<String, Table> tableMap) {
        List<Domain> domains = new ArrayList<>();
        Map<String/*domainName*/, List<ValueObject>> voMap = new HashMap<>();
        for (Map.Entry<String, Table> tableEntry : tableMap.entrySet()) {
            Table value = tableEntry.getValue();
            if (value.isDomain()) {
                Domain domain = new Domain();
                domain.setSimpleName(value.getClassName());
                domain.setUncapFirstName(value.getClassName().substring(0, 1).toLowerCase() + value.getClassName
                        ().substring(1));
                domain.setDesc(value.getDesc());
                domain.setTableName(value.getName());
                domain.setProperties(getProperties(value.getColumns()));

                domains.add(domain);
            } else {
                ValueObject vo = new ValueObject();
                vo.setSimpleName(value.getClassName());
                vo.setUncapFirstName(value.getClassName().substring(0, 1).toLowerCase() + value.getClassName
                        ().substring(1));
                vo.setDesc(value.getDesc());
                vo.setTableName(value.getName());
                vo.setProperties(getProperties(value.getColumns()));
                if (voMap.containsKey(value.getBindingDomain())) {
                    voMap.get(value.getBindingDomain()).add(vo);
                } else {
                    List<ValueObject> newVOList = new ArrayList<>();
                    newVOList.add(vo);
                    voMap.put(value.getBindingDomain(), newVOList);
                }
            }
        }

        for (Domain item : domains) {
            if (voMap.containsKey(item.getSimpleName())) {
                item.setBindingVOs(voMap.get(item.getSimpleName()));
            }
        }

        return domains;
    }

    private List<Property> getProperties(Set<Column> columnSet) {
        List<Property> result = new ArrayList<>();
        for (Column item : columnSet) {
            Property property = new Property();
            DataType type = DataType.getType(item.getJdbcType());
            property.setJdbcType(type.jdbcType);
            property.setTableColumn(item.getName());
            property.setJavaType(type.javaType);
            property.setPropertyName(item.getProperty());
            property.setDesc(item.getDesc());

            result.add(property);
        }

        return result;
    }

    private <T> List<T> mapConvertToList(Map<String, T> map) {
        List<T> result = new ArrayList<>();
        for (Map.Entry<String, T> item : map.entrySet()) {
            result.add(item.getValue());
        }

        return result;
    }

    private void generateSkeleton(Processer processer) {
        processer.generate(SkeletonGenerateConfiguration.CORE_SERVICE);
        processer.generate(SkeletonGenerateConfiguration.FACADE_IMPL_INTERACTION);
    }
}
