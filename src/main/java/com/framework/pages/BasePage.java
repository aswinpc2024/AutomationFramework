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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected void click(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = WaitUtils.performExplicitWait(by, waitStrategy);
        element.click();
        System.out.println ("[DEBUG] "+elementName + " is clicked");
        ExtentLogger.pass(elementName + " is clicked");
    }

    protected void sendKeys(By by, String text, WaitStrategy waitStrategy, String elementName) {
        WebElement element = WaitUtils.performExplicitWait(by, waitStrategy);
        element.clear();
        element.sendKeys(text);
        System.out.println ("[DEBUG] "+text + " is entered in " + elementName);
        ExtentLogger.pass(text + " is entered in " + elementName);
    }

    protected String getText(By by, WaitStrategy waitStrategy) {
        WebElement element = WaitUtils.performExplicitWait(by, waitStrategy);
        String text = element.getText ();
        ExtentLogger.pass(text);
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
        System.out.println ("[DEBUG] "+visibleText + " is selected from dropdown " + elementName);
        ExtentLogger.pass(visibleText + " is selected from dropdown " + elementName);
    }

    protected void selectDropdownByValue(By by, String value, String elementName) {
        Select select = new Select(DriverManager.getDriver().findElement(by));
        select.selectByValue(value);
        System.out.println ("[DEBUG] "+value + " is selected from dropdown " + elementName);
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

    protected void highlightByElement (By by)
    {
        WebDriver driver =DriverManager.getDriver ();
        WebElement element = driver.findElement( by );
        ( (JavascriptExecutor) driver ).executeScript ( "arguments[0].style.border='3px solid red'" , element );
    }

    protected void highlightXpathElement (WebElement by)
    {
        WebDriver driver =DriverManager.getDriver ();

        ( (JavascriptExecutor) driver ).executeScript ( "arguments[0].style.border='3px solid red'" , by );
    }

    protected String selectFeedbackCategory(By feedbackCategories, String feedBackCategory) {
        List<WebElement> feedbacks = DriverManager.getDriver().findElements(feedbackCategories);
        String matchedCategory = "";

        for (WebElement feedback : feedbacks) {
            if (feedback.getText().contains(feedBackCategory)) {
                matchedCategory = feedback.getText();
                feedback.click();
                break;
            }
        }
        return matchedCategory;
    }

}