package org.jason.automan.template;

/**
 * Created by Jason.Xia on 16/10/9.
 */
public class TemplateConfig {
    /**
     * template file root path
     */
    private String templateFileDir;
    /**
     * load all template when init template manager if false
     */
    private boolean lazyLoadTemplate;

    private boolean supportIdeaijPlugin = false;

    public TemplateConfig(String templateFileDir, boolean lazyLoadTemplate) {
        this(templateFileDir, lazyLoadTemplate, false);
    }

    public TemplateConfig(String templateFileDir, boolean lazyLoadTemplate, boolean supportIdeaijPlugin) {
        this.templateFileDir = templateFileDir;
        this.lazyLoadTemplate = lazyLoadTemplate;
        this.supportIdeaijPlugin = supportIdeaijPlugin;
    }

    public String getTemplateFileDir() {
        return templateFileDir;
    }

    public void setTemplateFileDir(String templateFileDir) {
        this.templateFileDir = templateFileDir;
    }

    public boolean isLazyLoadTemplate() {
        return lazyLoadTemplate;
    }

    public void setLazyLoadTemplate(boolean lazyLoadTemplate) {
        this.lazyLoadTemplate = lazyLoadTemplate;
    }

    public boolean isSupportIdeaijPlugin() {
        return supportIdeaijPlugin;
    }

    public void setSupportIdeaijPlugin(boolean supportIdeaijPlugin) {
        this.supportIdeaijPlugin = supportIdeaijPlugin;
    }
}
