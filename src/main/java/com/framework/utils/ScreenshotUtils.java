/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.utils;

import com.framework.constants.FrameworkConstants;
import com.framework.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public
class ScreenshotUtils {
    private ScreenshotUtils() {
        // Private constructor to prevent instantiation
    }

    public static String captureScreenshot(String testName) {
        String sanitizedTestName = testName.replaceAll("[<>:\"/\\\\|?*]", "_");
        File source = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs( OutputType.FILE);
        String timestamp = new SimpleDateFormat ( "yyyyMMdd_HHmmss").format( new Date ());
        String screenshotName = STR."\{sanitizedTestName}_\{timestamp}.png";
        Path destination = Paths.get( FrameworkConstants.getScreenshotPath() + screenshotName);

        try {
            Files.createDirectories( Paths.get( FrameworkConstants.getScreenshotPath()));
            Files.copy(source.toPath(), destination);
            return destination.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
