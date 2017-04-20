package org.jason.automan;

/**
 * Created by Jason.Xia on 17/4/20.
 */
public interface ProgressListener {

    /**
     * max 100, "increase" period of 0 to 100
     *
     * @param increase
     */
    void update(double increase);

    void update(String comment);
}
