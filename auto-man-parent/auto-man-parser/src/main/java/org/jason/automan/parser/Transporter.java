package org.jason.automan.parser;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public interface Transporter {

    Map<String, Object> transport(InputStream inputStream);
}
