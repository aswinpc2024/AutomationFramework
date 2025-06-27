/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.config;

import com.framework.constants.FrameworkConstants;

import com.framework.enums.BrowserType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public
class ConfigFactory {
    private static final Properties PROPERTIES = new Properties();
    private static final FrameworkConfig CONFIG = new FrameworkConfig();

    static {
        try (FileInputStream fileInputStream = new FileInputStream( FrameworkConstants.getConfigFilePath())) {
            PROPERTIES.load(fileInputStream);

            CONFIG.setBrowserType( BrowserType.valueOf( PROPERTIES.getProperty( "browser").toUpperCase()));
            CONFIG.setUrl(PROPERTIES.getProperty("url"));
            CONFIG.setHeadless(Boolean.parseBoolean(PROPERTIES.getProperty("headless")));
            CONFIG.setImplicitWait(Long.parseLong(PROPERTIES.getProperty("implicitWait")));
            CONFIG.setExplicitWait(Long.parseLong(PROPERTIES.getProperty("explicitWait")));
            CONFIG.setTakeScreenshotOnFailure(Boolean.parseBoolean(PROPERTIES.getProperty("screenshotOnFailure")));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private ConfigFactory() {
        // Private constructor to prevent instantiation
    }

    public static FrameworkConfig getConfig() {
        return CONFIG;
    }
}
