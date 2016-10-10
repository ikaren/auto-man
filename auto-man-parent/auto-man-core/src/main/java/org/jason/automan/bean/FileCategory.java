package org.jason.automan.bean;

/**
 * Created by Jason.Xia on 16/10/9.
 */
public enum FileCategory {
    CODE("java", 1),
    RESOURCES("resources", 2),
    POM("pom",3);

    public String value;
    public int code;

    FileCategory(String category, int code) {
        this.value = category;
        this.code = code;
    }

}
