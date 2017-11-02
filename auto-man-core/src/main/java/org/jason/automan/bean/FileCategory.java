package org.jason.automan.bean;

/**
 * Created by Jason.Xia on 16/10/9.
 */
public enum FileCategory {
    CODE("java", 1),
    RESOURCES("resources", 2),
    RESOURCES_PROD("resources.prod", 3),
    RESOURCES_BETA("resources.beta", 4),
    RESOURCES_ALPHA("resources.alpha", 5),
    RESOURCES_DEV("resources.dev", 6),
    POM("pom", 7);
    public String value;
    public int code;

    FileCategory(String category, int code) {
        this.value = category;
        this.code = code;
    }

}
