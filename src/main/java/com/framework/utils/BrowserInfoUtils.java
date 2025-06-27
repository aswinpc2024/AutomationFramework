/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.utils;

import com.framework.driver.DriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class BrowserInfoUtils
{
    /**
     * Private constructor to avoid external instantiation
     */
    private BrowserInfoUtils() {
    }

    public static String getBrowserInfo() {
        Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        return capabilities.getBrowserName().toUpperCase();

    }

    public static String getBrowserVersionInfo() {
        Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        return capabilities.getBrowserVersion ().toUpperCase ();

    }
}
