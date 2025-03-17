/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.pages;

import com.framework.driver.DriverManager;
import com.framework.enums.WaitStrategy;
import com.framework.reports.ExtentLogger;
import com.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

    protected void click(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = WaitUtils.performExplicitWait(by, waitStrategy);
        element.click();
        ExtentLogger.pass(elementName + " is clicked");
    }

    protected void sendKeys(By by, String text, WaitStrategy waitStrategy, String elementName) {
        WebElement element = WaitUtils.performExplicitWait(by, waitStrategy);
        element.clear();
        element.sendKeys(text);
        ExtentLogger.pass(text + " is entered in " + elementName);
    }

    protected String getText(By by, WaitStrategy waitStrategy) {
        return WaitUtils.performExplicitWait(by, waitStrategy).getText();
    }

    protected boolean isElementDisplayed(By by) {
        try {
            return DriverManager.getDriver().findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void selectDropdownByVisibleText(By by, String visibleText, String elementName) {
        Select select = new Select(DriverManager.getDriver().findElement(by));
        select.selectByVisibleText(visibleText);
        ExtentLogger.pass(visibleText + " is selected from dropdown " + elementName);
    }

    protected void selectDropdownByValue(By by, String value, String elementName) {
        Select select = new Select(DriverManager.getDriver().findElement(by));
        select.selectByValue(value);
        ExtentLogger.pass(value + " is selected from dropdown " + elementName);
    }

    protected void scrollToElement(By by) {
        WebElement element = DriverManager.getDriver().findElement(by);
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("return document.readyState").equals("complete");
    }
}