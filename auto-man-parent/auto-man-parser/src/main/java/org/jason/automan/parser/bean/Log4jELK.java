package org.jason.automan.parser.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/8.
 */
public class Log4jELK {
    private String id;
    private Map<String/*env*/, Log4jELKConfig> envConfig = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Log4jELKConfig> getEnvConfig() {
        return envConfig;
    }

    public void setEnvConfig(Map<String, Log4jELKConfig> envConfig) {
        this.envConfig = envConfig;
    }
}
