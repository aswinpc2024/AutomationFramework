/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.utils;

import com.framework.constants.FrameworkConstants;
import com.framework.driver.DriverManager;
import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.framework.enums.WaitStrategy.*;

public
class WaitUtils {
    private WaitUtils() {
        // Private constructor to prevent instantiation
    }

    public static
    WebElement performExplicitWait(By by, WaitStrategy waitStrategy) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait( DriverManager.getDriver(), FrameworkConstants.getExplicitWait());

        if ( waitStrategy.equals ( CLICKABLE ) ) {
            element = wait.until ( ExpectedConditions.elementToBeClickable ( by ) );
        }
        else if ( waitStrategy.equals ( PRESENCE ) ) {
            element = wait.until ( ExpectedConditions.presenceOfElementLocated ( by ) );
        }
        else if ( waitStrategy.equals ( VISIBLE ) ) {
            element = wait.until ( ExpectedConditions.visibilityOfElementLocated ( by ) );
        }
        else if ( waitStrategy.equals ( NONE ) ) {
            element = DriverManager.getDriver ( ).findElement ( by );
        }
        else {
            throw new IllegalArgumentException ( "Invalid wait strategy: " + waitStrategy );
        }

        return element;
    }
}
