/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.annotations.FrameworkAnnotation;
import com.framework.reports.ExtentLogger;
import com.framework.reports.ExtentReport;
import com.framework.utils.BrowserOSInfoUtils;
import com.framework.utils.EmailSendUtils;
import com.framework.utils.IconUtils;
import com.framework.utils.ZipUtils;
import org.testng.*;


import java.util.Arrays;

import static com.framework.constants.FrameworkConstants.*;

public class ListenerClass implements ITestListener, ISuiteListener {
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;
    static int count_totalTCs;

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();
        ZipUtils.zip();
        System.out.println ("Before Email after Zip" );
        EmailSendUtils.sendEmail( count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs);
    }

    @Override
    public void onTestStart(ITestResult result) {

        // System.out.println("onTestStart() ");
        count_totalTCs = count_totalTCs + 1;
        ExtentReport.createTest(result.getMethod().getMethodName());
        // ExtentReport.createTest(result.getMethod().getDescription());

         ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation( FrameworkAnnotation.class).author());

        ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation( FrameworkAnnotation.class).category());

        ExtentReport.addDevices();
        // ExtentLogger.info("<b>" +
        // BrowserOSInfoUtils.getOS_Browser_BrowserVersionInfo() + "</b>");
        ExtentLogger.info( "<b>" + IconUtils.getOSIcon() + "  &  " + IconUtils.getBrowserIcon() + " --------- "
                                  + BrowserOSInfoUtils.getOS_Browser_BrowserVersionInfo() + "</b>");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        count_passedTCs = count_passedTCs + 1;
        String logText = "<b><font color=greeen>" + result.getMethod().getMethodName() + " is passed." + ICON_SMILEY_PASS+"</font></b>";
        ExtentLogger.pass( logText );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        count_failedTCs = count_failedTCs + 1;
        ExtentLogger.fail(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
        String exceptionMessage = Arrays.toString( result.getThrowable().getStackTrace());
        String message = "<details><summary><b><font color=red> Exception occured, click to see details: "
                + ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
                + "</details> \n";

        ExtentLogger.fail(message);

        String logText = "<details><summary><b><font color=red>" + result.getMethod().getMethodName() + " is failed." +  ICON_SMILEY_FAIL+ "</font></b></summary></details>";
        ExtentLogger.fail( logText );

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        count_skippedTCs = count_skippedTCs + 1;

        ExtentLogger.skip(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
        String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>" + "  " + ICON_SMILEY_FAIL;
        Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        ExtentLogger.skip( String.valueOf ( markup_message ) );

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        /*
         * As of now, we are not using it
         */
    }

    @Override
    public void onStart(ITestContext result) {
        /*
         * As of now, we are not using it.
         */
    }

    @Override
    public void onFinish(ITestContext result) {
        /*
         * As of now, we are not using it.
         */
    }
}
