/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.utils;

public final class OSInfoUtils {
    /**
     * Private constructor to avoid external instantiation
     */
    private OSInfoUtils() {
    }

    public static String getOSInfo() {
        return System.getProperty("os.name").replace(" ", "_");
    }
}
