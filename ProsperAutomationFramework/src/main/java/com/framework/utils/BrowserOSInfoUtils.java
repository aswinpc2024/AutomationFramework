/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/

package com.framework.utils;

public
class BrowserOSInfoUtils {
    /**
     * Private constructor to avoid external instantiation
     */
    private BrowserOSInfoUtils() {
    }

    public static String getOS_Browser_BrowserVersionInfo() {
        return OSInfoUtils.getOSInfo() + " & " + BrowserInfoUtils.getBrowserInfo() + " - "
                + BrowserInfoUtils.getBrowserVersionInfo();

    }
}
