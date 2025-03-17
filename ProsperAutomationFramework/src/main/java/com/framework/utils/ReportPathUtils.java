/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.utils;

import static com.framework.constants.FrameworkConstants.*;

public
class ReportPathUtils {
    // private -> We do not want anyone to create the object of this class
    // Private constructor to avoid external instantiation
    private ReportPathUtils() {
    }

    public static String createExtentReportPath() {
        if (ConfigLoader.getInstance().getOverrideReports().equalsIgnoreCase(NO)) {
            /**
             * Report name ->
             *
             * Windows_10_Tue_Oct_05_02_30_46_IST_2021_AutomationReport.html
             * Mac_OS_X_Tue_Feb_22_17_09_05_IST_2022_AutomationReport.html
             */
            return EXTENT_REPORT_FOLDER_PATH + OSInfoUtils.getOSInfo() + "_" + DateUtils.getCurrentDate() + "_"
                    + EXTENT_REPORT_NAME;

        } else {
            return EXTENT_REPORT_FOLDER_PATH + EXTENT_REPORT_NAME;
        }
    }
}
