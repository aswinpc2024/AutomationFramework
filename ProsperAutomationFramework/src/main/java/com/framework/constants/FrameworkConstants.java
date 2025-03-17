/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.constants;

import com.framework.utils.ReportPathUtils;

import java.time.Duration;

public
class FrameworkConstants {
    //D:\Automation\ProsperAutomationFramework\src\test\resources
    //D:\Automation\ProsperAutomationFramework\src\test\resources\config\config.properties
    private static final String RESOURCES_PATH = System.getProperty("user.dir")+"\\src\\test\\resources" ;
    private static final String CONFIG_FILE_PATH = RESOURCES_PATH + "\\config\\stg_config.properties";
    private static final String SCREENSHOT_PATH = RESOURCES_PATH + "\\screenshots\\";
    private static final String REPORTS_PATH = RESOURCES_PATH + "\\reports\\";

    private static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);
    private static final Duration EXPLICIT_WAIT = Duration.ofSeconds(30);

    private static final String PROJECT_PATH = System.getProperty("user.dir");
    // private static final String RESOURCES_PATH = System.getProperty("user.dir") +
    // "/src/test/resources";

    /* ICONS - START */
    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";

    public static final String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
    public static final String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";
    public static final String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";

//	public static final String ICON_BROWSER_OPERA = "<i class=\"fa fa-opera\" aria-hidden=\"true\"></i>";
//	public static final String ICON_BROWSER_EDGE = "<i class=\"fa fa-edge\" aria-hidden=\"true\"></i>";
//	public static final String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
//	public static final String ICON_BROWSER_FIREFOX = "<i class=\"fa fa-firefox\" aria-hidden=\"true\"></i>";
//	public static final String ICON_BROWSER_SAFARI = "<i class=\"fa fa-safari\" aria-hidden=\"true\"></i>";

    public static final String ICON_Navigate_Right = "<i class='fa fa-arrow-circle-right' ></i>";
    public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
    public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";

    /* style="text-align:center;" */
    public static final String ICON_SOCIAL_LINKEDIN_URL_PC = "https://www.linkedin.com/in/aswin-p-c/";
    public static final String ICON_SOCIAL_GITHUB_URL_PC = "https://github.com/aswinpc143";
    public static final String ICON_SOCIAL_LINKEDIN_URL_VK = "https://www.linkedin.com/in/aswin-p-c/";
    public static final String ICON_SOCIAL_GITHUB_URL_VK = "https://github.com/Vkpixbit?tab=repositories";

    public static final String ICON_SOCIAL_LINKEDIN_PC = "<a href='" + ICON_SOCIAL_LINKEDIN_URL_PC + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
    public static final String ICON_SOCIAL_GITHUB_PC = "<a href='" + ICON_SOCIAL_GITHUB_URL_PC + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";

    public static final String ICON_SOCIAL_LINKEDIN_VK = "<a href='" + ICON_SOCIAL_LINKEDIN_URL_VK + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
    public static final String ICON_SOCIAL_GITHUB_VK = "<a href='" + ICON_SOCIAL_GITHUB_URL_VK + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";


    public static final String ICON_CAMERA = "<i class=\"fa fa-camera\" aria-hidden=\"true\"></i>";

    public static final String ICON_BROWSER_EDGE = "edge";
    public static final String ICON_BROWSER_PREFIX = "<i class=\"fa fa-";
    public static final String ICON_BROWSER_SUFFIX = "\" aria-hidden=\"true\"></i>";

    /* ICONS - END */

    public static final String ASSERTION_FOR_RESPONSE_STATUS_CODE = "Assertion for Response Status Code";
    public static final String ASSERTION_FOR_RESPONSE_HEADER = "Assertion for Response Headers";
    public static final String ASSERTION_FOR_RESPONSE_CUSTOM_FIELD = "Assertion for Response Custom Field";

    public static final String YES = "yes";
    public static final String NO = "no";

//    private static final int EXPLICIT_WAIT = 10;
//    public static final int WAIT = 5;

    public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + "//ExtentReports//";
    public static final String EXTENT_REPORT_NAME = "AutomationReport.html";
    private static String extentReportFilePath = "";

    /** Zip file of Extent Reports */
//    public static final String Zipped_ExtentReports_Folder_Name = "ExtentReports.zip";

    private static final String Project_Name = "Automation Test Suite Report - Master Selenium Framework";

    public static String getProjectPath() {
        return PROJECT_PATH;
    }

    public static String getProjectName() {
        return Project_Name;
    }

//    public static String getZipped_ExtentReports_Folder_Name() {
//        return Zipped_ExtentReports_Folder_Name;
//    }
//
//    public static String getExtentReportFilePath() {
//
//        if (extentReportFilePath.isEmpty()) {
//            extentReportFilePath = ReportPathUtils.createExtentReportPath();
//        }
//        return extentReportFilePath;
//    }
//
//    public static int getExplicitWait() {
//        return EXPLICIT_WAIT;
//    }

    /** Zip file of Extent Reports */
    public static final String Zipped_ExtentReports_Folder_Name = "ExtentReports.zip";



    public static String getExtentReportFilePath() {

        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = ReportPathUtils.createExtentReportPath();
        }
        return extentReportFilePath;
    }

    private FrameworkConstants() {
        // Private constructor to prevent instantiation
    }

    public static String getConfigFilePath() {
        return CONFIG_FILE_PATH;
    }

    public static
    Duration getImplicitWait() {
        return IMPLICIT_WAIT;
    }

    public static Duration getExplicitWait() {
        return EXPLICIT_WAIT;
    }

    public static String getScreenshotPath() {
        return SCREENSHOT_PATH;
    }

    public static String getReportsPath() {
        return REPORTS_PATH;
    }

}
