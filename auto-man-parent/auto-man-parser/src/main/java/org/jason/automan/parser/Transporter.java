package org.jason.automan.parser;

import org.jason.automan.parser.bean.Project;

import java.util.List;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public interface Transporter {

    List<Project> transport(String in);
}
