package org.jason.automan;

import org.jason.automan.bean.ProjectName;
import org.jason.automan.constants.StringConstants;
import org.jason.automan.template.SkeletonGenerateConfiguration;
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
    private ProgressListener listener;

    public Processer(ProcesserContext context, ProgressListener listener) {
        this.listener = listener;
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

        root.put(StringConstants.PROJECT_NAME, new ProjectName(context.getProjectName().substring(0, 1).toUpperCase()
                + context.getProjectName().substring(1), context.getProjectName().substring(0, 1).toLowerCase() +
                context.getProjectName().substring(1)));
        root.put(StringConstants.PROJECT_LAYER, context.getProjectLayer());

        StringBuilder dirSb = new StringBuilder();
        switch (templateKey.fileCategory) {
            case CODE:
                dirSb.append(context.getProjectHome())
                        .append(context.getProjectLayer())
                        .append("-")
                        .append(context.getProjectName())
                        .append("/")
                        .append(context.getProjectName())
                        .append(templateKey.modulePath)
                        .append("/src/main/")
                        .append(templateKey.fileCategory.value)
                        .append(context.getProjectBase())
                        .append(templateKey.targetFilePath);
                break;
            case POM:
                dirSb.append(context.getProjectHome())
                        .append(context.getProjectLayer())
                        .append("-")
                        .append(context.getProjectName());
                if (!"".equals(templateKey.modulePath)) {
                    dirSb.append("/")
                            .append(context.getProjectName())
                            .append(templateKey.modulePath);
                }
                break;
            case RESOURCES_PROD:
            case RESOURCES_BETA:
            case RESOURCES_ALPHA:
            case RESOURCES_DEV:
            case RESOURCES:
                dirSb.append(context.getProjectHome())
                        .append(context.getProjectLayer())
                        .append("-")
                        .append(context.getProjectName())
                        .append("/")
                        .append(context.getProjectName())
                        .append(templateKey.modulePath)
                        .append("/src/main/")
                        .append(templateKey.fileCategory.value)
                        .append(templateKey.targetFilePath);
                break;
            default:
                throw new IllegalArgumentException("Unsupported File Category: " + templateKey.fileCategory);
        }

        File dir = new File(dirSb.toString());
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IllegalStateException("Failed to create directory :" + fileName);
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(dirSb.toString(), fileName + templateKey.fileType.value));
            templateManager.getTemplate(templateKey.templateName).process(root, writer);
            writer.flush();
            String comment = "Generate file: " + fileName + " OK! path: " + dirSb;
            if (null == listener) {
                System.out.println(comment);
            } else {
                listener.update(comment);
            }
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

    public void generate(SkeletonGenerateConfiguration skeletonKey) {
        if (null == skeletonKey) {
            throw new IllegalArgumentException("SkeletonGenerateConfiguration not be null.");
        }

        StringBuilder sb = new StringBuilder();
        switch (skeletonKey.fileCategory) {
            case CODE:
                sb.append(context.getProjectHome())
                        .append(context.getProjectLayer())
                        .append("-")
                        .append(context.getProjectName())
                        .append("/")
                        .append(context.getProjectName())
                        .append(skeletonKey.modulePath)
                        .append("/src/main/")
                        .append(skeletonKey.fileCategory.value)
                        .append(context.getProjectBase())
                        .append(skeletonKey.targetFilePath)
                        .append(skeletonKey.dirName);
                break;
            case RESOURCES:
                break;
            default:
                throw new IllegalArgumentException("Unsupported File Category: " + skeletonKey.fileCategory);
        }

        File javaPackage = new File(sb.toString());
        if (javaPackage.exists()) {
            return;
        }

        if (!javaPackage.mkdirs()) {
            throw new IllegalStateException("Failed to create java package: " + sb.toString());
        }

        String comment = "Generate package: " + javaPackage.getName() + " OK! path: " + sb.toString();
        if (null == listener) {
            System.out.println(comment);
        } else {
            listener.update(comment);
        }
    }
}
