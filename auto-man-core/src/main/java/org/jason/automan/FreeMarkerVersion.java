package org.jason.automan;

import freemarker.template.Version;

/**
 * Created by Jason.Xia on 16/9/29.
 */
public class FreeMarkerVersion {
    private static Version version;

    static {
        version = new Version(2, 3, 25);
    }

    public static Version getVersion() {
        if (null == version) {
            version = new Version(2, 3, 25);
        }

        return version;
    }
}
