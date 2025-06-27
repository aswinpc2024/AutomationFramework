/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.reports;

import com.aventstack.extentreports.ExtentTest;

public
class ExtentManager {
    private static final ThreadLocal<ExtentTest> EXTENT_TEST_THREAD_LOCAL = new ThreadLocal<>();

    private ExtentManager() {
        // Private constructor to prevent instantiation
    }

    public static ExtentTest getExtentTest() {
        return EXTENT_TEST_THREAD_LOCAL.get();
    }

    public static void setExtentTest(ExtentTest test) {
        EXTENT_TEST_THREAD_LOCAL.set(test);
    }

    public static void unload() {
        EXTENT_TEST_THREAD_LOCAL.remove();
    }
}
