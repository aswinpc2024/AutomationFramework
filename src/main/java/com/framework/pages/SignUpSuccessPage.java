package com.framework.pages;

import com.framework.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SignUpSuccessPage extends BasePage
{
    // --- Locators ---
    private final By accountCreateSuccessHeader = By.xpath ( "//div/p[contains(.,'Account Created')]" );
    private final By accountCreateSuccessDescription = By.xpath ( "//div/span[contains(.,'Thank you for signing up! Verify your documents to publish your properties with us.')]" );
    private final By skipDocumentVerificationBtn = By.xpath ( "//button[contains(.,'Skip, I will do it later')]" );

    // --- Page Methods ---
    /**
     * Checks if the sign-up success page is loaded by verifying the visibility of the success header.
     *
     * @return true if the success header is displayed, false otherwise.
     */
    public boolean isPageLoaded() {
        return isElementDisplayed(accountCreateSuccessHeader);
    }

    /**
     * Retrieves the text of the account creation success header.
     *
     * @return The text of the success header.
     */
    public String getAccountCreateSuccessHeader() {
        waitForPageLoad ();
        WebElement accountCreatesuccessHeader = DriverManager.getDriver ().findElement ( this.accountCreateSuccessHeader );
        highlightXpathElement ( accountCreatesuccessHeader );
        System.out.println (accountCreatesuccessHeader.getText () );
        return getText(accountCreateSuccessHeader, com.framework.enums.WaitStrategy.VISIBLE);
    }

    /**
     * Retrieves the text of the account creation success description.
     *
     * @return The text of the success description.
     */
    public String getAccountCreateSuccessDescription() {
        waitForPageLoad ();
        WebElement accountCreatesuccessDescription = DriverManager.getDriver ().findElement ( this.accountCreateSuccessDescription );
        highlightXpathElement ( accountCreatesuccessDescription );
        System.out.println (accountCreatesuccessDescription.getText () );
        return getText(accountCreateSuccessDescription, com.framework.enums.WaitStrategy.VISIBLE);
    }

    /**
     * Clicks the "Skip, I will do it later" button to bypass document verification.
     *
     * @return A new instance of the ProfilePage, as skipping typically leads to the user's profile or dashboard.
     */
    public ProfilePage clickSkipDocumentVerification() {
        click(skipDocumentVerificationBtn, com.framework.enums.WaitStrategy.CLICKABLE, "Skip Document Verification Button");
        return new ProfilePage();
    }

}
