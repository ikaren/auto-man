package org.jason.automan.parser.bean;

/**
 * Created by Jason.Xia on 16/10/8.
 */
public class Log4jELKConfig {
    private String env;
    private String port;
    private String host;
    private long reconnectDelay;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getReconnectDelay() {
        return reconnectDelay;
    }

    public void setReconnectDelay(long reconnectDelay) {
        this.reconnectDelay = reconnectDelay;
    }
}
