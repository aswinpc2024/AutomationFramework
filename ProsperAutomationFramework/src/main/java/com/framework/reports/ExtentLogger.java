/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.utils.ScreenshotUtils;

public
class ExtentLogger {
    private ExtentLogger() {
        // Private constructor to prevent instantiation
    }

    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }

    public static void info(String message) {
        ExtentManager.getExtentTest().info(message);
    }

    public static void warning(String message) {
        ExtentManager.getExtentTest().warning(message);
    }

    public static void logWithScreenshot(String message, Status status) {
        String screenshotPath = ScreenshotUtils.captureScreenshot( ExtentManager.getExtentTest().getModel().getName());
        ExtentManager.getExtentTest().log( status, message,
                                           MediaEntityBuilder.createScreenCaptureFromPath( screenshotPath).build());
    }
}
