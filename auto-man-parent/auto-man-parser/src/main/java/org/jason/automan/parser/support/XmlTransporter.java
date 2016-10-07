package org.jason.automan.parser.support;

import org.jason.automan.parser.Transporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class XmlTransporter implements Transporter {
    @Override
    public Map<String, Object> transport(InputStream in) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(in);
            // root <university>
            Element root = doc.getDocumentElement();
            if (null == root) {
                return null;
            }

            NodeList project = root.getElementsByTagName("project");


            System.err.println(root.getAttribute("name"));
            // all college node
            NodeList collegeNodes = root.getChildNodes();
            if (collegeNodes == null) {
                return null;
            }
            for (int i = 0; i < collegeNodes.getLength(); i++) {
                Node college = collegeNodes.item(i);
                if (college != null && college.getNodeType() == Node.ELEMENT_NODE) {
                    System.err.println("\t" + college.getAttributes().getNamedItem("name").getNodeValue());
                    // all class node
                    NodeList classNodes = college.getChildNodes();
                    if (classNodes == null) continue;
                    for (int j = 0; j < classNodes.getLength(); j++) {
                        Node clazz = classNodes.item(j);
                        if (clazz != null && clazz.getNodeType() == Node.ELEMENT_NODE) {
                            System.err.println("\t\t" + clazz.getAttributes().getNamedItem("name").getNodeValue());
                            // all student node
                            NodeList studentNodes = clazz.getChildNodes();
                            if (studentNodes == null) continue;
                            for (int k = 0; k < studentNodes.getLength(); k++) {
                                Node student = studentNodes.item(k);
                                if (student != null && student.getNodeType() == Node.ELEMENT_NODE) {
                                    System.err.print("\t\t\t" + student.getAttributes().getNamedItem("name")
                                            .getNodeValue());
                                    System.err.print(" " + student.getAttributes().getNamedItem("sex").getNodeValue());
                                    System.err.println(" " + student.getAttributes().getNamedItem("age").getNodeValue
                                            ());
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
