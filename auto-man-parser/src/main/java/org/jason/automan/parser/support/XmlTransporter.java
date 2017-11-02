package org.jason.automan.parser.support;

import org.jason.automan.parser.Transporter;
import org.jason.automan.parser.bean.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class XmlTransporter implements Transporter {
    private String templatePath;

    @Override
    public List<Project> transport(String in, String templatePath) {
        this.templatePath = templatePath;
        File file = new File(in);
        Document doc;
        List<Project> result = new ArrayList<>();
        try {
            doc = Jsoup.parse(file, "utf-8");
            Elements projects = doc.getElementsByTag("project");
            if (null != projects) {
                for (Element project : projects) {
                    result.add(getProject(project));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Project getProject(Element project) {
        Project result = new Project();
        result.setId(project.attr("id"));
        result.setProjectName(project.attr("project-name"));
        String projectHome = project.attr("project-dir");
        if (projectHome.endsWith("/")) {
            result.setProjectDir(projectHome);
        } else {
            result.setProjectDir(projectHome + "/");
        }

        result.setPackageName(project.attr("package-name"));
        String template = project.attr("template-root");
        if (null != template && 0 != template.length()) {
            result.setTemplateRoot(template);
        } else {
            result.setTemplateRoot(this.templatePath);
        }

        Elements datasource = project.getElementsByTag("datasource");
        if (null != datasource && 0 < datasource.size()) {
            result.setDataSourceConfig(getDataSource(datasource.get(0)));
        }

        Elements dubbo = project.getElementsByTag("dubbo");
        if (null != dubbo && 0 < dubbo.size()) {
            result.setDubboConfig(getDubboConfig(dubbo.get(0)));
        }

        Elements log4jConfig = project.getElementsByTag("log4j-elk");
        if (null != log4jConfig && 0 < log4jConfig.size()) {
            result.setLog4jConfig(getLog4jConfig(log4jConfig.get(0)));
        }

        Elements tables = project.getElementsByTag("data-table");
        if (null != tables && 0 < tables.size()) {
            for (Element item : tables) {
                Table table = getTable(item);
                result.getTables().put(table.getName(), table);
            }
        }

        return result;
    }

    private DataSourceConfig getDataSource(Element datasource) {
        DataSourceConfig result = new DataSourceConfig();
        result.setId(datasource.attr("id"));
        Elements envConfig = datasource.getElementsByTag("database-config");
        if (null != envConfig && 0 < envConfig.size()) {
            for (Element item : envConfig) {
                DatabaseConfig databaseConfig = new DatabaseConfig();
                databaseConfig.setEnv(item.attr("env"));
                databaseConfig.setDbName(item.attr("db-name"));
                databaseConfig.setHost(item.attr("host"));
                databaseConfig.setPort(item.attr("port"));
                databaseConfig.setUsername(item.attr("username"));
                databaseConfig.setPassword(item.attr("password"));

                result.getEnvConfig().put(databaseConfig.getEnv(), databaseConfig);
            }
        }

        return result;
    }

    private Dubbo getDubboConfig(Element dubbo) {
        Dubbo result = new Dubbo();
        result.setId(dubbo.attr("id"));
        result.setServiceName(dubbo.attr("service-name"));
        Elements envConfig = dubbo.getElementsByTag("dubbo-config");
        if (null != envConfig && 0 < envConfig.size()) {
            for (Element item : envConfig) {
                DubboConfig dubboConfig = new DubboConfig();
                dubboConfig.setEnv(item.attr("env"));
                dubboConfig.setPort(item.attr("port"));
                dubboConfig.setZkAddress(item.attr("zk-address"));
                dubboConfig.setGroup(item.attr("group"));

                result.getEnvConfig().put(dubboConfig.getEnv(), dubboConfig);
            }
        }

        return result;
    }

    private Log4jELK getLog4jConfig(Element log4jConfig) {
        Log4jELK result = new Log4jELK();
        result.setId(log4jConfig.attr("id"));
        Elements envConfig = log4jConfig.getElementsByTag("log4j-elk-config");
        if (null != envConfig && 0 < envConfig.size()) {
            for (Element item : envConfig) {
                Log4jELKConfig log4jELKConfig = new Log4jELKConfig();
                log4jELKConfig.setEnv(item.attr("env"));
                log4jELKConfig.setPort(item.attr("port"));
                log4jELKConfig.setHost(item.attr("host"));
                log4jELKConfig.setReconnectDelay(Long.parseLong(item.attr("reconn-delay")));

                result.getEnvConfig().put(log4jELKConfig.getEnv(), log4jELKConfig);
            }
        }

        return result;
    }

    private Table getTable(Element table) {
        Table result = new Table();

        result.setId(table.attr("id"));
        result.setName(table.attr("name"));
        result.setDesc(table.attr("desc"));
        result.setClassName(table.attr("class-name"));
        result.setDomain(Boolean.parseBoolean(table.attr("is-domain")));
        result.setBindingDomain(table.attr("binding-domain"));
        Elements columns = table.getElementsByTag("column");
        if (null != columns && 1 <= columns.size()) {
            for (Element item : columns) {
                Column column = new Column();
                column.setName(item.attr("name"));
                column.setJdbcType(item.attr("jdbcType"));
                column.setProperty(item.attr("property"));
                column.setNotNull(null == item.attr("notNull") || Boolean.parseBoolean(item.attr("notNull")));
                column.setUnique(null != item.attr("unique") && Boolean.parseBoolean(item.attr("unique")));
                column.setDesc(null != item.attr("desc") ? item.attr("desc") : item.attr("name"));
                result.getColumns().add(column);
            }
        }

        return result;
    }
}
