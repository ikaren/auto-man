package org.jason.automan;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import org.jason.automan.constants.StringConstants;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/9/29.
 */
public class TemplateManager {
    private static TemplateManager instance;

    static {
        instance = new TemplateManager();
        instance.init();
    }

    private Configuration configuration;
    private String templateFileDir;
    private Map<String, Template> templateMap = new HashMap<>();

    private TemplateManager getInstance() {
        if (null == instance) {
            instance = new TemplateManager();
            instance.init();
        }

        return instance;
    }

    public void init() {
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
    }

    public Template build(String templateName) {
        if (templateMap.containsKey(templateName)) {
            return templateMap.get(templateName);
        }

        Template template;
        try {
            template = configuration.getTemplate(templateName);
            if (null == template) {
                throw new IllegalArgumentException("cannot find the template :" + templateName);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        return template;
    }
}
