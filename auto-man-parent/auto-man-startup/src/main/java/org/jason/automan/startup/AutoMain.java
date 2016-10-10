package org.jason.automan.startup;

import org.jason.automan.parser.support.XmlParser;

import java.net.URL;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class AutoMain {
    public static void main(String[] args) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("./reservation-demo.xml");
        if (null != resource) {
            new XmlParser().parser(resource.getFile());
        }
    }
}
