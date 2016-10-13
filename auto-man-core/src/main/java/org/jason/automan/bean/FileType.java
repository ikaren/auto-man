package org.jason.automan.bean;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public enum FileType {
    JAVA(".java", 1),
    XML(".xml", 2),
    PROPERTIES(".properties", 3),
    SQL(".sql", 4),
    DIR("",5);
    public String value;
    public int code;

    FileType(String value, int code) {
        this.value = value;
        this.code = code;
    }

}
