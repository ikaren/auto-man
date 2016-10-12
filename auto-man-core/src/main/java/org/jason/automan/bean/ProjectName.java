package org.jason.automan.bean;

/**
 * Created by Jason.Xia on 16/10/10.
 */
public class ProjectName {
    private String capFirst;
    private String uncapFirst;

    public ProjectName(String capFirst, String uncapFirst) {
        this.capFirst = capFirst;
        this.uncapFirst = uncapFirst;
    }

    public String getCapFirst() {
        return capFirst;
    }

    public void setCapFirst(String capFirst) {
        this.capFirst = capFirst;
    }

    public String getUncapFirst() {
        return uncapFirst;
    }

    public void setUncapFirst(String uncapFirst) {
        this.uncapFirst = uncapFirst;
    }
}
