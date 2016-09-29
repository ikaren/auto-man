package org.jason.automan;

import freemarker.template.*;
import org.jason.automan.bean.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/9/27.
 */
public class AutoManMain {
    private static final String tempDir = "/Users/yuqingxia/IdeaProject/supreme/auto-man/auto-man-parent/auto-man" +
            "-core/src/main/resources/template";

    public static void main(String[] args) throws IOException, TemplateException {
        Version version = new Version(2, 3, 25);
        Configuration cfg = new Configuration(version);
        cfg.setDirectoryForTemplateLoading(new File(tempDir));
        cfg.setObjectWrapper(new DefaultObjectWrapperBuilder(version).build());
        Template template = cfg.getTemplate("domain.ftl");
        Map<String, Object> root = new HashMap<>();
        Domain domain = new Domain();
        domain.setPackageName("org.jason.automan.core.domain");
//        domain.setClassName("org.jason.automan.core.domain.Report");
        domain.setSimpleName("Report");
//        domain.setSuperclass(new Domain("org.albert.common.domain.Entity", "Entity"));
        List<Property> properties = new ArrayList<>();
        properties.add(new Property("String", "name"));
        properties.add(new Property("String", "url"));
        properties.add(new Property("int", "num"));
        domain.setProperties(properties);

        List<Interface> repositories = new ArrayList<>();
        Interface repository = new Interface();
//        repository.setRepositoryType("org.jason.automan.core.repository.IReportRepository");
//        repository.setSimpleType("IReportRepository");
//        repository.setName("reportRepository");
        List<Operation> operations = new ArrayList<>();
        Operation operation = new Operation();
        operation.setName(OperationType.Create.toString());
        operation.setOperationType(OperationType.Create);
        operation.setReturnType(PropertyType.Integer.toString());
        Map<String, String> ars = new HashMap<>();
        ars.put(domain.getSimpleName(), domain.getSimpleName().toLowerCase());
        operation.setArgs(ars);
        operations.add(operation);
//        repository.setOperations(operations);
        repositories.add(repository);

//        domain.setRepositories(repositories);

        root.put("domain", domain);
        File dir = new File("/Users/yuqingxia/IdeaProject/supreme/auto-man/auto-man-parent/auto-man-core/src/main" +
                "/java/org/jason/automan/core/domain");
        dir.mkdirs();
        File file = new File("/Users/yuqingxia/IdeaProject/supreme/auto-man/auto-man-parent/auto-man-core/src/main" +
                "/java/org/jason/automan/core/domain", "Report.java");
        FileWriter fileWriter = new FileWriter(file);
        template.process(root, fileWriter);
        fileWriter.flush();
        System.out.println(file.getCanonicalPath());
        fileWriter.close();

    }
}
