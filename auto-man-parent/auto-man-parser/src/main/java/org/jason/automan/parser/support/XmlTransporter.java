package org.jason.automan.parser.support;

import com.alibaba.fastjson.JSONObject;
import org.jason.automan.parser.Transporter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public class XmlTransporter implements Transporter {

    @Override
    public JSONObject transport(String in) {
        File file = new File(in);
        Document doc;
        JSONObject result = new JSONObject();
        try {
            doc = Jsoup.parse(file, "utf-8");
            Elements projects = doc.getElementsByTag("project");
            if (null != projects) {
                for (Element project : projects) {
                    Map<String, String> map = new HashMap<>();
                    map.put("project-name", project.attr("project-name"));
                    map.put("project-dir", project.attr("project-dir"));
                    String id = project.attr("id");
                    project.getElementsByTag("datasource");
                    result.put(id, map);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    private Map<String,String>
}
