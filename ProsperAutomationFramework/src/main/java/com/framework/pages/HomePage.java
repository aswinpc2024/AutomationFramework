/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.pages;
import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By lblWelcomeMessage = By.id("welcomeMessage");
    private final By btnLogout = By.id("logoutButton");
    private final By menuProfile = By.id("profileMenu");

    public String getWelcomeMessage() {
        return getText(lblWelcomeMessage, WaitStrategy.VISIBLE);
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(lblWelcomeMessage);
    }

    public void clickLogout() {
        click(btnLogout, WaitStrategy.CLICKABLE, "Logout button");
    }

    public void navigateToProfile() {
        click(menuProfile, WaitStrategy.CLICKABLE, "Profile menu");
    }
}