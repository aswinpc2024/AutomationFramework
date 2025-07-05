package com.framework.pages;

import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;

/**
 * Represents the main Dashboard or Home Page that a user sees after a successful login or sign-up.
 * This class provides methods to interact with and verify elements on the dashboard.
 */
public class ProfilePage extends BasePage {

    // --- Locators ---
    // These are example locators. Please adjust them to match your application's actual HTML.
    private final By welcomeMessageHeader = By.xpath("//h1[contains(text(), 'Welcome')]");
    private final By userProfileIcon = By.id("user-profile-icon");

    /**
     * Checks if the Dashboard page has loaded successfully by verifying the visibility
     * of a key, stable element like the user profile icon.
     *
     * @return true if the profile icon is displayed, false otherwise.
     */
    public boolean isPageLoaded() {
        // Using the robust helper method inherited from BasePage.
        // This is a reliable way to confirm the user has landed on the correct page.
        return isElementDisplayed(userProfileIcon);
    }

    /**
     * Retrieves the full text of the welcome message from the dashboard.
     * This is useful for assertions to verify the correct user is logged in.
     *
     * @return The welcome message as a String (e.g., "Welcome, Aswin Chandran PC").
     */
    public String getWelcomeMessage() {
        // Uses the getText helper from BasePage, which includes an explicit wait.
        return getText(welcomeMessageHeader, WaitStrategy.VISIBLE);
    }




}