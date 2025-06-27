/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.driver;

import com.framework.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public
class Driver {
    private Driver() {
        // Private constructor to prevent instantiation
    }

    public static
    WebDriver initDriver(BrowserType browserType , String url) {
        WebDriver driver = null;

        switch (browserType) {
            case FIREFOX:
                driver = new FirefoxDriver ();
                driver.get ( url );
                break;
            case EDGE:
                driver= new EdgeDriver ();
                driver.get ( url );
                break;
            case SAFARI:
                driver= new SafariDriver ();
                driver.get ( url );
                break;
            case CHROME:
            default:
                driver = new ChromeDriver ();
                driver.get ( url );
        }

        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return driver;
    }

    public static void quitDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
