/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.tests;

import com.framework.config.ConfigFactory;
import com.framework.driver.Driver;
import com.listeners.ListenerClass;
import com.framework.reports.ExtentLogger;
import com.framework.reports.ExtentManager;
import com.framework.reports.ExtentReport;
import org.testng.annotations.*;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@Listeners(ListenerClass.class)
public class BaseTest {

    @BeforeSuite
    public void beforeSuite()
    {
        System.out.println("[DEBUG] Initializing Extent Reports...");
        ExtentReport.initReports();  // Initialize ExtentReports
        if (ExtentReport.getExtentReports() == null) {
            throw new RuntimeException("[ERROR] ExtentReports instance is null after initReports().");
        }
        System.out.println ("Before suite is working");
    }

    @AfterSuite
    public void afterSuite()
    {
        System.out.println("[DEBUG] Flushing Extent Reports...");
        ExtentReport.flushReports();  // Flush reports after execution
    }

    @BeforeMethod
    public void setUp(ITestResult result)
    {
        System.out.println("[DEBUG] Starting setup method...");

        // Initialize WebDriver
        System.out.println("[DEBUG] Initializing WebDriver...");
        Driver.initDriver(ConfigFactory.getConfig().getBrowserType(),ConfigFactory.getConfig().getUrl());

        // Validate ITestResult
        if (result == null || result.getMethod() == null) {
            throw new RuntimeException("[ERROR] ITestResult or its method is null!");
        }

        // Retrieve test method name
        String testName = result.getMethod().getMethodName();
        System.out.println("[DEBUG] Running test: " + testName);

        // Ensure ExtentReports is initialized before creating the test
        if (ExtentReport.getExtentReports() == null) {
            throw new RuntimeException("[ERROR] ExtentReports instance is null! Make sure initReports() was called.");
        }

        // Set up ExtentTest for the test method
        System.out.println("[DEBUG] Creating ExtentTest for: " + testName);
        ExtentTest test = ExtentReport.getExtentReports().createTest(testName);
        ExtentManager.setExtentTest(test);

        // Navigate to application URL
        System.out.println("[DEBUG] Navigating to URL: " + ConfigFactory.getConfig().getUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result)
    {
        System.out.println("[DEBUG] Starting tearDown method...");

        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("[DEBUG] Test failed: Logging failure...");
            ExtentLogger.fail(result.getThrowable().getMessage());
            {
                System.out.println("[DEBUG] Taking screenshot on failure...");
                ExtentLogger.logWithScreenshot("Test Failed", Status.FAIL);
            }
        }

        System.out.println("[DEBUG] Quitting WebDriver...");
        Driver.quitDriver();
    }
}
