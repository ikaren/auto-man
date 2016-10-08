package org.jason.automan.parser;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public interface Transporter {

    JSONObject transport(String in);
}
