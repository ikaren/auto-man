package org.jason.automan.parser.support;

import org.jason.automan.Processer;
import org.jason.automan.ProcesserContext;
import org.jason.automan.ProcesserFactory;
import org.jason.automan.parser.AbstractParser;
import org.jason.automan.parser.Transporter;
import org.jason.automan.parser.bean.Project;

import java.util.List;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class XmlParser extends AbstractParser {
    private Transporter transporter;
    private ProcesserFactory processerFactory;
//    private TemplateManager templateManager;

    public void XmlParser(String templateDir) {
        init(templateDir);
    }

    public void init(String templateDir) {
        transporter = new XmlTransporter();
        processerFactory = new ProcesserFactory();
//        templateManager = new TemplateManager(templateDir);
    }

    public void parser(String in) {
        List<Project> projects = transporter.transport(in);
        for (Project item : projects) {
            Processer processer = processerFactory.createProcesser(new ProcesserContext(item.getPackageName(), item
                    .getProjectDir(), item.getPackageName(), item.getTemplateRoot()));
//            processer.generate(templateManager.build("core/domain-specified.ftl"),);
        }
    }
}
