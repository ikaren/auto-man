package org.jason.automan.parser.support;

import org.jason.automan.parser.AbstractParser;
import org.jason.automan.parser.Transporter;
import org.jason.automan.parser.bean.Project;

import java.util.List;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class XmlParser extends AbstractParser {
    private Transporter transporter;

    public void init() {
        transporter = new XmlTransporter();
    }

    public void parser(String in) {
        List<Project> projects = transporter.transport(in);

    }
}
