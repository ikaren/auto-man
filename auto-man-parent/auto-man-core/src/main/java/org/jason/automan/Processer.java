package org.jason.automan;

import freemarker.template.Template;
import org.jason.automan.bean.FileType;
import org.jason.automan.constants.StringConstants;
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
    private ProcesserContext processerContext;

    public Processer(ProcesserContext processerContext) {
        this.processerContext = processerContext;
    }

    public void generate(Template template, Map<String, Object> root, String filePath, String fileName, FileType
            fileType) {
        if (null == filePath || 0 == filePath.length() || null == fileName || 0 == fileName.length()) {
            throw new IllegalArgumentException("file path and file name not be null.");
        }

        if (null == root) {
            root = new HashMap<>();
        }

        root.put(StringConstants.PROJECT_BASE_PACKAGE, processerContext.getBasePackage());
        root.put(StringConstants.PROJECT_HOME, processerContext.getProjectHome());
        root.put(StringConstants.PROJECT_BASE, processerContext.getProjectBase());

        if (fileName.endsWith("/")) {
            fileName = fileName.substring(0, fileName.length() - 1);
        }

        File dir = new File(filePath);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IllegalStateException("Failed to create directory :" + fileName);
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(fileName, fileName + fileType.value));
            template.process(root, writer);
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
