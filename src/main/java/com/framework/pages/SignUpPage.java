package com.framework.pages;

import com.framework.driver.DriverManager;
import com.framework.enums.WaitStrategy;
import com.framework.utils.MapPair;
import org.openqa.selenium.By;

import java.util.HashMap;

/**
 * Represents the Sign Up page of the application.
 * This class follows the Page Object Model pattern, providing an API
 * to interact with the sign-up form.
 */
public class SignUpPage extends BasePage {

    // 1. Navigation: Start from the login page and navigate to the sign-up page.
    // This returns the SignUpPage object, ensuring we are on the correct page.
    LoginPage loginPage = new LoginPage();
    SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage ();
    OTPpage otpPage = new OTPpage ();

    HashMap<String,String> testResults = new HashMap<> ();
    HashMap<String,String> userStatus = new HashMap<> ();
    // --- Locators ---
    private final By nameField = By.name("name");
    private final By phoneField = By.name("phone");
    private final By emailField = By.name("email");
    private final By languageDropdown = By.xpath("//button[@role='combobox']");
    private final By agreeChkBox = By.xpath ( "//span[@class='geekmark']" );
    private final By continueBtn =By.xpath ( "//button[contains(text(),'Continue')]" );

    private final By mobileNumberAlreadyExistError = By.xpath ( "//span[contains(text(),'The mobile is already registered with prosper')]" );
    private final By emailIdAlreadyExistError = By.xpath ( "//span[contains(text(),'The email is already registered with prosper.')]" );
    private final By closeSignUpPopup = By.xpath ( "/html/body/div[2]/div/div/div/div[2]/div/div/div/div/div[1]" );

    public SignUpPage ( ) throws InterruptedException {
    }
    // --- Page Methods ---
    /**
     * Checks if a key element (the name field) is visible to confirm the page has loaded.
     *
     * @return true if the page is loaded, false otherwise.
     */
    public boolean isPageLoaded() {
        // Using the robust helper method inherited from BasePage
        return isElementDisplayed(nameField);
    }
    /**
     * Fills the sign-up form with user data and submits it.
     * This is a high-level method that makes test scripts clean and readable.
     *
     * @param fullName      The full name of the user.
     * @param mobileNumber  The mobile number of the user.
     * @param emailAddress  The email address of the user.
     * @param preferredLang The preferred language for the user.
     * @return A new instance of the DashboardPage, the expected destination after signing up.
     */
    public MapPair<String, String> fillSignUpFormAndSubmitwithNewUser(String fullName, String mobileNumber, String emailAddress, String preferredLang) throws InterruptedException {

        //1. Navigation: Start from the login page and navigate to the sign-up page.
        loginPage.navigateToSignUpPage();

        if (!isPageLoaded())
        {
            throw new IllegalStateException("Sign Up page did not load correctly. Cannot proceed.");
        }

        return fillUserDetails(fullName, mobileNumber, emailAddress, preferredLang);
    }

    /**
     * Selects a language from the custom dropdown.
     *
     * @param language The visible text of the language to select (e.g., "English").
     */
    private void selectLanguage(String language) {
        click(languageDropdown, WaitStrategy.CLICKABLE, "Language dropdown");
        // Build a dynamic locator for the specific language option
        By languageOption = By.xpath ( "//span[contains(.,'"+language+"')]" );
        click(languageOption, WaitStrategy.CLICKABLE, "Language option: " + language);
    }

    private MapPair<String, String> fillUserDetails(String fullName, String mobileNumber, String emailAddress, String preferredLang) throws InterruptedException {

        sendKeys ( nameField , fullName , WaitStrategy.VISIBLE , "Full Name field" );
        sendKeys ( phoneField , mobileNumber , WaitStrategy.NONE , "Phone Number field" );
        sendKeys ( emailField , emailAddress , WaitStrategy.NONE , "Email field" );
        selectLanguage ( preferredLang );
        click ( agreeChkBox , WaitStrategy.CLICKABLE , "Terms and Conditions Checkbox" );
        click ( continueBtn , WaitStrategy.CLICKABLE , "Continue Button" );
        waitForPageLoad ();
        System.out.println ("I'm after Wait" );
        if(isElementDisplayed ( mobileNumberAlreadyExistError ))
        {
            System.out.println ("I'm in IF" );
            userStatus.put ( "userExist", "true" );
            userStatus.put ( "mobileNumberExist","true" );
            testResults.put ( "mobileExistError", DriverManager.getDriver ( ).findElement ( mobileNumberAlreadyExistError ).getText ( ) );
            System.out.println ( STR."[DEBUG] \{DriverManager.getDriver ( ).findElement ( mobileNumberAlreadyExistError ).getText ( )}, So Initiating Login with given mobile number" );
            closeSignUpPopupAndSigninWithOtp(mobileNumber);
            return new MapPair<>( userStatus, testResults);
        }
        else if ( isElementDisplayed ( emailIdAlreadyExistError ) )
        {
            System.out.println ("I'm in ELSE IF" );
            userStatus.put ( "userExist", "true" );
            userStatus.put ( "emailIdExist","true" );
            testResults.put ( "emailExistError", DriverManager.getDriver ( ).findElement ( emailIdAlreadyExistError ).getText ( ) );
            System.out.println ( STR."[DEBUG] \{DriverManager.getDriver ( ).findElement ( emailIdAlreadyExistError ).getText ( )}, So Initiating Login with given Email address" );
            closeSignUpPopupAndSigninWithOtp(emailAddress);
            return new MapPair<>(userStatus, testResults);
        }
        else
        {
            System.out.println ("I'm in ELSE" );
            userStatus.put ( "userExist", "false" );
            userStatus.put ( "mobileNumberExist","false" );
            userStatus.put ( "emailIdExist","false" );
            otpPage.enterOtpAndVerify ( "email", "1234" );
            Thread.sleep ( 5000 );
            otpPage.enterOtpAndVerify ( "mobile", "1234" );
            signUpSuccessPage.clickSkipDocumentVerification ();
            testResults = signUpSuccessPage.getIntroScreenData ();

            return new MapPair<>(userStatus, testResults);
        }
    }

    public void closeSignUpPopupAndSigninWithOtp(String otpType) throws InterruptedException {
        DriverManager.getDriver ().findElement (closeSignUpPopup) .click ();
        Thread.sleep ( 5000 );
        new LoginPage ().loginToApplicationWithOTP ( otpType,"1234" );
    }
}