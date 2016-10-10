package org.jason.automan;

import org.jason.automan.constants.StringConstants;
import org.jason.automan.template.TemplateConfig;
import org.jason.automan.template.TemplateGenerateConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class Processer {
    private static final Logger logger = LoggerFactory.getLogger(Processer.class);
    private ProcesserContext context;
    private TemplateManager templateManager;

    public Processer(ProcesserContext context) {
        this.context = context;
        templateManager = new TemplateManager(new TemplateConfig(context.getTemplateRepository(), false));
    }

    public void generate(TemplateGenerateConfiguration templateKey, Map<String, Object> root, String fileName) {
        if (null == fileName || 0 == fileName.length()) {
            throw new IllegalArgumentException("file name not be null.");
        }

        if (null == root) {
            root = new HashMap<>();
        }

        root.put(StringConstants.PROJECT_BASE_PACKAGE, context.getBasePackage());
        root.put(StringConstants.PROJECT_HOME, context.getProjectHome());
        root.put(StringConstants.PROJECT_BASE, context.getProjectBase());

        StringBuilder dirSb = new StringBuilder();
        dirSb.append(context.getProjectHome())
                .append(context.getProjectName())
                .append(templateKey.modulePath)
                .append("/src/main/")
                .append(templateKey.fileCategory.value)
                .append("/")
                .append(context.getProjectBase())
                .append(templateKey.targetFilePath);
        File dir = new File(dirSb.toString());
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IllegalStateException("Failed to create directory :" + fileName);
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(dirSb.toString(), fileName + templateKey.fileType.value));
            templateManager.getTemplate(templateKey.templateName).process(root, writer);
            writer.flush();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.warn("FileWriter stream close failure!", e);
                }
            }
        }
    }
}
