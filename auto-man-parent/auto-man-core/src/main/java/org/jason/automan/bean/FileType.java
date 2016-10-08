package org.jason.automan.bean;

/**
 * Created by Jason.Xia on 16/10/7.
 */
public enum FileType {
    JAVA(".java", 1);

    public String value;
    public int code;

    FileType(String value, int code) {
        this.value = value;
        this.code = code;
    }

}
