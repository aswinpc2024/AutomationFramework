/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.config;

import com.framework.enums.BrowserType;

public class FrameworkConfig
{
    private BrowserType browserType;
    private String url;
    private boolean headless;
    private long implicitWait;
    private long explicitWait;
    private boolean takeScreenshotOnFailure;

    public BrowserType getBrowserType() {
        return browserType;
    }

    public void setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public long getImplicitWait() {
        return implicitWait;
    }

    public void setImplicitWait(long implicitWait) {
        this.implicitWait = implicitWait;
    }

    public long getExplicitWait() {
        return explicitWait;
    }

    public void setExplicitWait(long explicitWait) {
        this.explicitWait = explicitWait;
    }

    public boolean isTakeScreenshotOnFailure() {
        return takeScreenshotOnFailure;
    }

    public void setTakeScreenshotOnFailure(boolean takeScreenshotOnFailure) {
        this.takeScreenshotOnFailure = takeScreenshotOnFailure;
    }
}
