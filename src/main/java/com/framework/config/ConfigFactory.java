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
import java.util.Objects;
import java.util.Properties;

/**
 * A factory class for reading and providing configuration properties for the framework.
 * <p>
 * This class loads configuration from a .properties file once and provides a
 * singleton {@link FrameworkConfig} object. It uses a static initializer block
 * to ensure the configuration is loaded only once when the class is first accessed.
 */
public final class ConfigFactory {

    // Declare the final CONFIG field. It will be initialized once in the static block.
    private static final FrameworkConfig CONFIG;

    /**
     * Static initializer block to load properties from the file and initialize the CONFIG object.
     * This block is executed only once when the class is loaded into memory by the JVM.
     */
    static {
        // Use a local Properties object, as it's only needed during initialization.
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.getConfigFilePath())) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            // Throw a custom runtime exception for better error handling and diagnostics.
            throw new IllegalStateException("Failed to load the configuration properties file at: " + FrameworkConstants.getConfigFilePath(), e);
        }

        // Initialize the final CONFIG object using the Builder pattern.
        // This provides a clean, readable way to set properties and creates an immutable object.
        // I've also added default values to make the framework more resilient.
        CONFIG = new FrameworkConfig.Builder()
                .browserType(BrowserType.valueOf(properties.getProperty("browser", "CHROME").toUpperCase()))
                .url(Objects.requireNonNull(properties.getProperty("url"), "Property 'url' cannot be null in config file"))
                .headless(Boolean.parseBoolean(properties.getProperty("headless", "false")))
                .implicitWait(Long.parseLong(properties.getProperty("implicitWait", "10")))
                .explicitWait(Long.parseLong(properties.getProperty("explicitWait", "10")))
                .takeScreenshotOnFailure(Boolean.parseBoolean(properties.getProperty("screenshotOnFailure", "true")))
                .build();
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ConfigFactory() {
        // This class should not be instantiated.
    }

    /**
     * Provides access to the singleton {@link FrameworkConfig} object.
     *
     * @return The populated {@link FrameworkConfig} instance containing all framework configurations.
     */
    public static FrameworkConfig getConfig() {
        return CONFIG;
    }
}