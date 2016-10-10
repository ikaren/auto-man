package org.jason.automan.parser.support;

import org.jason.automan.Processer;
import org.jason.automan.ProcesserContext;
import org.jason.automan.ProcesserFactory;
import org.jason.automan.bean.DataType;
import org.jason.automan.bean.Domain;
import org.jason.automan.bean.Property;
import org.jason.automan.bean.ValueObject;
import org.jason.automan.parser.AbstractParser;
import org.jason.automan.parser.Transporter;
import org.jason.automan.parser.bean.Column;
import org.jason.automan.parser.bean.Project;
import org.jason.automan.parser.bean.Table;
import org.jason.automan.template.TemplateGenerateConfiguration;

import java.util.*;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class XmlParser extends AbstractParser {
    private Transporter transporter;
    private ProcesserFactory processerFactory;

    public XmlParser() {
        init();
    }

    public void init() {
        transporter = new XmlTransporter();
        processerFactory = new ProcesserFactory();
    }

    public void parser(String in) {
        List<Project> projects = transporter.transport(in);
        for (Project item : projects) {
            Processer processer = processerFactory.createProcesser(new ProcesserContext(item.getProjectName(), item
                    .getProjectDir(), item.getPackageName(), item.getTemplateRoot()));

            List<Domain> domains = getDomains(item.getTables());
            processer.generate(TemplateGenerateConfiguration.EXCEPTION, buildRootMap(TemplateGenerateConfiguration
                    .EXCEPTION, null), item.getProjectName().substring(0, 1).toUpperCase() + item.getProjectName()
                    .substring(1));
            for (Domain domain : domains) {
                generateCodeFile(domain, processer);
            }


        }
    }

    private void generateCodeFile(Domain domain, Processer processer) {
        processer.generate(TemplateGenerateConfiguration.DOMAIN, buildRootMap(TemplateGenerateConfiguration
                .DOMAIN, domain), domain.getSimpleName());
        processer.generate(TemplateGenerateConfiguration.REPOSITORY, buildRootMap(TemplateGenerateConfiguration
                .REPOSITORY, domain), "I" + domain.getSimpleName() + "Repository");
        processer.generate(TemplateGenerateConfiguration.MAPPER, buildRootMap(TemplateGenerateConfiguration
                .MAPPER, domain), domain.getSimpleName() + "Mapper");
        processer.generate(TemplateGenerateConfiguration.REPOSITORY_IMPL, buildRootMap
                (TemplateGenerateConfiguration.REPOSITORY_IMPL, domain), domain.getSimpleName() + "Repository");
        processer.generate(TemplateGenerateConfiguration.DTO, buildRootMap(TemplateGenerateConfiguration
                .DTO, domain), domain.getSimpleName() + "DTO");
        processer.generate(TemplateGenerateConfiguration.FACADE_COMMAND, buildRootMap
                (TemplateGenerateConfiguration.FACADE_COMMAND, domain), "I" + domain.getSimpleName() +
                "CommandFacade");
        processer.generate(TemplateGenerateConfiguration.FACADE_COMMAND_IMPL, buildRootMap
                (TemplateGenerateConfiguration.FACADE_COMMAND_IMPL, domain), domain.getSimpleName() +
                "CommandFacade");
        processer.generate(TemplateGenerateConfiguration.FACADE_QUERY, buildRootMap
                (TemplateGenerateConfiguration.FACADE_QUERY, domain), "I" + domain.getSimpleName() +
                "QueryFacade");
        processer.generate(TemplateGenerateConfiguration.FACADE_QUERY_IMPL, buildRootMap
                (TemplateGenerateConfiguration.FACADE_QUERY_IMPL, domain), domain.getSimpleName() +
                "QueryFacade");
        processer.generate(TemplateGenerateConfiguration.ASSEMBLER, buildRootMap(TemplateGenerateConfiguration
                .ASSEMBLER, domain), domain.getSimpleName() + "Assembler");
        processer.generate(TemplateGenerateConfiguration.APPLICATION, buildRootMap(TemplateGenerateConfiguration
                .APPLICATION, domain), "I" + domain.getSimpleName() + "Application");
        processer.generate(TemplateGenerateConfiguration.APPLICATION_IMPL, buildRootMap
                (TemplateGenerateConfiguration.APPLICATION_IMPL, domain), domain.getSimpleName() +
                "Application");
        if (null != domain.getBindingVOs()) {
            for (ValueObject valueObject : domain.getBindingVOs()) {
                processer.generate(TemplateGenerateConfiguration.VO, buildRootMap
                        (TemplateGenerateConfiguration.VO, valueObject), valueObject.getSimpleName());
                processer.generate(TemplateGenerateConfiguration.REPOSITORY, buildRootMap
                        (TemplateGenerateConfiguration.REPOSITORY, valueObject), "I" + valueObject
                        .getSimpleName() + "Repository");
                processer.generate(TemplateGenerateConfiguration.REPOSITORY_IMPL, buildRootMap
                        (TemplateGenerateConfiguration.REPOSITORY_IMPL, valueObject), valueObject
                        .getSimpleName() + "Repository");
                processer.generate(TemplateGenerateConfiguration.MAPPER, buildRootMap
                        (TemplateGenerateConfiguration.MAPPER, valueObject), valueObject.getSimpleName() +
                        "Mapper");
                processer.generate(TemplateGenerateConfiguration.DTO, buildRootMap
                        (TemplateGenerateConfiguration.DTO, valueObject), valueObject.getSimpleName() + "DTO");
            }
        }
    }

    private void generateResourcesFile(){

    }

    private <T> Map<String, Object> buildRootMap(TemplateGenerateConfiguration templateKey, T value) {
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
                break;
            case REPOSITORY:
            case MAPPER:
            case REPOSITORY_IMPL:
            case DTO:
            case VO:
                root.put("vo", value);
                break;
            case EXCEPTION:
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
                domain.setProperties(getProperties(value.getColumns()));

                domains.add(domain);
            } else {
                ValueObject vo = new ValueObject();
                vo.setSimpleName(value.getClassName());
                vo.setUncapFirstName(value.getClassName().substring(0, 1).toLowerCase() + value.getClassName
                        ().substring(1));
                vo.setDesc(value.getDesc());
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
}
