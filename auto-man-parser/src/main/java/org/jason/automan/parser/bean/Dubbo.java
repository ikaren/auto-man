package org.jason.automan.parser.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason.Xia on 16/10/8.
 */
public class Dubbo {
    private String id;
    private String serviceName;
    private Map<String/*env*/, DubboConfig> envConfig = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Map<String, DubboConfig> getEnvConfig() {
        return envConfig;
    }

    public void setEnvConfig(Map<String, DubboConfig> envConfig) {
        this.envConfig = envConfig;
    }
}
