package org.jason.automan.parser.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/8.
 */
public class DataSourceConfig {
    private String id;
    private Map<String/*environment*/, DatabaseConfig> envConfig = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, DatabaseConfig> getEnvConfig() {
        return envConfig;
    }

    public void setEnvConfig(Map<String, DatabaseConfig> envConfig) {
        this.envConfig = envConfig;
    }
}
