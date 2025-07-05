package com.framework.pages;

import com.framework.driver.DriverManager;
import com.framework.enums.WaitStrategy;
import com.framework.reports.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SignUpSuccessPage extends BasePage
{
    // --- Signup Success Page Locators ---
    private final By accountCreateSuccessHeader = By.xpath ( "//div/p[contains(.,'Account Created')]" );
    private final By accountCreateSuccessDescription = By.xpath ( "//div/span[contains(.,'Thank you for signing up! Verify your documents to publish your properties with us.')]" );
    private final By skipDocumentVerificationBtn = By.xpath ( "//button[contains(.,'Skip, I will do it later')]" );

    // --- Intro Screen Locators ---
    private final By introScreen = By.xpath ( "/html/body/div[4]/div[2]" );
    private final By welcomeUserMessage = By.xpath("//div[2]/p[contains(normalize-space(),'Hello')]");
    private final By welcomeToProsper = By.xpath ( "(//div/div[1]/div[2]/p[2])[3]" );
    private final By introScreenDescription = By.xpath ( "(//div/div[1]/div[2]/p[1])[6]" );
//    private final By


    // --- Page Methods ---
    /**
     * Checks if the sign-up success page is loaded by verifying the visibility of the success header.
     *
     * @return true if the success header is displayed, false otherwise.
     */
    public boolean isPageLoaded() {
        return isElementDisplayed(accountCreateSuccessHeader);
    }
    public boolean isIntroScreenLoaded() {
        return isElementDisplayed(introScreen);
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
        return getText(accountCreateSuccessHeader, WaitStrategy.VISIBLE);
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
        return getText(accountCreateSuccessDescription, WaitStrategy.VISIBLE);
    }

    /**
     * Clicks the "Skip, I will do it later" button to bypass document verification.
     *
     * @return A new instance of the ProfilePage, as skipping typically leads to the user's profile or dashboard.
     */
    public void clickSkipDocumentVerification() {
        waitForPageLoad ();
        click(skipDocumentVerificationBtn, WaitStrategy.CLICKABLE, "Skip Document Verification Button");
    }



    /**
     * Waits for the intro screen to appear and retrieves the welcome message.
     * This method replaces the need for a manual if/else check by using an
     * explicit wait, which is a more reliable way to handle dynamic content.
     *
     * @return The text of the welcome message.
     * @throws org.openqa.selenium.TimeoutException if the intro screen or message does not appear within the configured wait time.
     */
    public String getIntroScreenWelcomeMessage() {
        waitForPageLoad ();
        // The getText helper method from your BasePage will wait for the element
        // to become visible before retrieving its text. This is the most efficient
        // way to handle this, as it combines waiting and action in one step.
        String message = getText( welcomeUserMessage, WaitStrategy.VISIBLE);
        System.out.println ("[DEBUG] Evaluating intro screen welcome message....." );
        System.out.println ("[DEBUG] Intro screen welcome message: '" + message + "'" );


        // Use the framework's logger for better reporting.
        ExtentLogger.info("Intro screen welcome message: '" + message + "'");

        return message;
    }

}
