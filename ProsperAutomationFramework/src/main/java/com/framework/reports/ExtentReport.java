/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/

package com.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.constants.FrameworkConstants;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.utils.BrowserInfoUtils;
import com.framework.utils.IconUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.framework.constants.FrameworkConstants.*;

public
class ExtentReport {
    private ExtentReport() {
    }

    private static ExtentReports extent;

    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
            /*
             * .viewConfigurer() .viewOrder() .as(new ViewName[] { ViewName.DASHBOARD,
             * ViewName.TEST, //ViewName.TAG, ViewName.CATEGORY, ViewName.AUTHOR,
             * ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG }) .apply();
             */

            /*
             * You can even update the view of the ExtentRerport - Whta do you want to you
             * first, you can prioritize
             */
            /*
             * ExtentSparkReporter spark = new
             * ExtentSparkReporter(REPORTS_SPARK_CUSTOMISED_HTML).viewConfigurer().viewOrder
             * () .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY
             * }).apply();
             */
            extent.attachReporter(spark);

            // spark.config().setEncoding("utf-8");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle(FrameworkConstants.getProjectName() + " - ALL");
            spark.config().setReportName(FrameworkConstants.getProjectName() + " - ALL");

            extent.setSystemInfo("Organization", "PIXBIT SOLUTIONS PVT LTD");
            extent.setSystemInfo("Employee",
                                 "<b> Aswin Chandran PC </b>" + " " + ICON_SOCIAL_LINKEDIN_PC + " " + ICON_SOCIAL_GITHUB_PC);
            extent.setSystemInfo("Domain", "Engineering (IT - Software)"+"  "+ICON_LAPTOP);
            extent.setSystemInfo("Skill", "Test Automation Engineer");

            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle(FrameworkConstants.getProjectName() + " - ALL");
            spark.config().setReportName(FrameworkConstants.getProjectName() + " - ALL");

            extent.setSystemInfo("Employee",
                                 "<b> VISHNUDAS K  </b>" + " " + ICON_SOCIAL_LINKEDIN_VK + " " + ICON_SOCIAL_GITHUB_VK);
            extent.setSystemInfo("Domain", "Engineering (IT - Software)"+"  "+ICON_LAPTOP);
            extent.setSystemInfo("Skill", "Test Automation Engineer");
        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
        ExtentManager.unload();
        try {
            Desktop.getDesktop().browse( new File ( FrameworkConstants.getExtentReportFilePath()).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTest(String testCaseName) {
        // ExtentManager.setExtentTest(extent.createTest(testCaseName));
        ExtentManager.setExtentTest(extent.createTest( IconUtils.getBrowserIcon() + " : " + testCaseName));
    }

    synchronized public static void addAuthors(AuthorType[] authors) {
        for (AuthorType author : authors) {
            ExtentManager.getExtentTest().assignAuthor(author.toString());
        }
    }

    // public static void addCategories(String[] categories) {
    synchronized public static void addCategories(CategoryType[] categories) {
        // for (String category : categories) {
        for (CategoryType category : categories) {
            ExtentManager.getExtentTest().assignCategory(category.toString());
        }
    }

    synchronized public static void addDevices() {
        ExtentManager.getExtentTest().assignDevice( BrowserInfoUtils.getBrowserInfo());
//		ExtentManager.getExtentTest()
//				.assignDevice(BrowserIconUtils.getBrowserIcon() + " : " + BrowserInfoUtils.getBrowserInfo());
    }

    public static ExtentReports getExtentReports() {
        return extent;
    }
}
