package com.framework.config;

import com.framework.enums.BrowserType;
import java.util.Objects;

/**
 * An immutable class that encapsulates all configuration settings for the test automation framework.
 * <p>
 * This class is designed using the Builder pattern to ensure that once a configuration object
 * is created, its state cannot be changed, promoting stability and thread-safety.
 *
 * @see ConfigFactory#getConfig() to get the constructed instance of this class.
 */
public final class FrameworkConfig {

    // All fields are final, making the object immutable after construction.
    private final BrowserType browserType;
    private final String url;
    private final boolean headless;
    private final long implicitWait;
    private final long explicitWait;
    private final boolean takeScreenshotOnFailure;

    /**
     * Private constructor that takes a Builder instance.
     * This forces the creation of the object to go through the builder.
     * @param builder The builder instance with all the configuration set.
     */
    private FrameworkConfig(Builder builder) {
        this.browserType = builder.browserType;
        this.url = builder.url;
        this.headless = builder.headless;
        this.implicitWait = builder.implicitWait;
        this.explicitWait = builder.explicitWait;
        this.takeScreenshotOnFailure = builder.takeScreenshotOnFailure;
    }

    // --- Public Getters to access the configuration values ---

    public BrowserType getBrowserType() {
        return browserType;
    }

    public String getUrl() {
        return url;
    }

    public boolean isHeadless() {
        return headless;
    }

    public long getImplicitWait() {
        return implicitWait;
    }

    public long getExplicitWait() {
        return explicitWait;
    }

    public boolean isTakeScreenshotOnFailure() {
        return takeScreenshotOnFailure;
    }

    // --- The public static nested Builder class ---

    /**
     * A builder for creating immutable {@link FrameworkConfig} instances.
     * It provides a fluent API for setting configuration properties.
     */
    public static class Builder {
        // Builder fields mirror the main class, with default values where appropriate.
        private BrowserType browserType = BrowserType.CHROME;
        private String url;
        private boolean headless = false;
        private long implicitWait = 10;
        private long explicitWait = 10;
        private boolean takeScreenshotOnFailure = true;

        public Builder browserType(BrowserType browserType) {
            this.browserType = browserType;
            return this; // Return the builder for method chaining
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder headless(boolean headless) {
            this.headless = headless;
            return this;
        }

        public Builder implicitWait(long implicitWait) {
            this.implicitWait = implicitWait;
            return this;
        }

        public Builder explicitWait(long explicitWait) {
            this.explicitWait = explicitWait;
            return this;
        }

        public Builder takeScreenshotOnFailure(boolean takeScreenshotOnFailure) {
            this.takeScreenshotOnFailure = takeScreenshotOnFailure;
            return this;
        }

        /**
         * Constructs the final, immutable {@link FrameworkConfig} object.
         * This is the place for any final validation before object creation.
         *
         * @return A new, configured instance of {@link FrameworkConfig}.
         */
        public FrameworkConfig build() {
            // Validate that essential properties are set before creating the object.
            Objects.requireNonNull(this.url, "URL must not be null in the configuration");
            return new FrameworkConfig(this);
        }
    }
}