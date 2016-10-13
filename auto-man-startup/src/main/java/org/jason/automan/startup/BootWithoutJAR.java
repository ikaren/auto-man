package org.jason.automan.startup;

import org.jason.automan.parser.support.XmlParser;

import java.net.URL;

/**
 * Created by Jason.Xia on 16/10/13.
 */
public class BootWithoutJAR {
    public static void main(String[] args) {
        URL demo = Thread.currentThread().getContextClassLoader().getResource("./demo/simple-demo.xml");
        URL template = Thread.currentThread().getContextClassLoader().getResource("./template");

        new XmlParser().parser(demo.getFile(), template.getFile());
    }
}
