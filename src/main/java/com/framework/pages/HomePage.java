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

    public final By lblWelcomeMessage = By.id("welcomeMessage");
    public final By btnLogout = By.id("logoutButton");
    public final By menuProfile = By.id("profileMenu");
    public final By prosperLogo = By.xpath ("(//img[contains(@alt,'Prosper Logo')])[1]");

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