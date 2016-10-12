package org.jason.automan;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import org.jason.automan.constants.StringConstants;
import org.jason.automan.template.TemplateConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/9/29.
 */
public class TemplateManager {
    private Configuration configuration;
    private String templateFileDir;
    private Map<String, Template> templates = new HashMap<>();
    private TemplateConfig templateConfig;

    public TemplateManager(TemplateConfig config) {
        this.templateConfig = config;
        this.templateFileDir = config.getTemplateFileDir();
        init();
    }

    protected void init() {
        if (null == configuration) {
            synchronized (this) {
                if (null == configuration) {
                    configuration = new Configuration(FreeMarkerVersion.getVersion());
                }
            }
        }

        if (null == templateFileDir) {
            URL resource;
            resource = Thread.currentThread().getContextClassLoader().getResource("./template");
            if (null == resource) {
                resource = Thread.currentThread().getContextClassLoader().getResource("/template");
                if (null == resource) {
                    String fileDir = System.getProperty(StringConstants.TEMPLATE_BASE_KEY);
                    if (null == fileDir || 0 == fileDir.length()) {
                        throw new IllegalStateException("Cannot find template directory!");
                    }

                    templateFileDir = fileDir;
                }
            }

            if (null != resource && !new File(resource.getFile()).isDirectory()) {
                throw new IllegalStateException("template path:" + resource.getFile() + " not a directory!");
            }

            if (null != resource) {
                templateFileDir = resource.getFile();
            }
        }

        try {
            configuration.setDirectoryForTemplateLoading(new File(templateFileDir));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot resolved the template path:" + templateFileDir, e);
        }

        configuration.setObjectWrapper(new DefaultObjectWrapperBuilder(FreeMarkerVersion.getVersion()).build());
        if (!templateConfig.isLazyLoadTemplate()) {
            initTemplate();
        }
    }

    public Template getTemplate(String templateName) {
        if (templates.containsKey(templateName)) {
            return templates.get(templateName);
        }

        return build(templateName);
    }

    protected Template build(String templateName) {
        if (templates.containsKey(templateName)) {
            return templates.get(templateName);
        }

        Template template;
        try {
            template = configuration.getTemplate(templateName);
            if (null == template) {
                throw new IllegalArgumentException("cannot find the template :" + templateName);
            }

            templates.put(templateName, template);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        return template;
    }

    private void initTemplate() {
        File root = new File(templateFileDir);
        if (root.exists() && root.isDirectory()) {
            loadTemplate(root.getAbsolutePath());
        } else {
            throw new IllegalStateException("Cannot resolved the template path:" + templateFileDir);
        }
    }

    private void loadTemplate(String filePath) {
        File parent = new File(filePath);
        File[] files = parent.listFiles();
        if (null == files || 0 == files.length) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                loadTemplate(file.getAbsolutePath());
            }

            if (file.getName().endsWith(".ftl") && file.getName().startsWith(templateFileDir)) {
                String relativityPath = file.getName().replace(templateFileDir, "");
                if (relativityPath.startsWith("/")) {
                    relativityPath = relativityPath.substring(1);
                }

                build(relativityPath);
            }
        }
    }
}
