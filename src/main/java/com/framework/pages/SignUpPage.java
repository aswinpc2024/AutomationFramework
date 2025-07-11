package com.framework.pages;

import com.framework.driver.DriverManager;
import com.framework.enums.WaitStrategy;
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

    Boolean isNewUser;
    HashMap<String,String> introScreenData = new HashMap<> ();
    HashMap<String,Boolean> isUserExist = new HashMap<> ();
    // --- Locators ---
    private final By nameField = By.name("name");
    private final By phoneField = By.name("phone");
    private final By emailField = By.name("email");
    private final By languageDropdown = By.xpath("//button[@role='combobox']");
    private final By agreeChkBox = By.xpath ( "//span[@class='geekmark']" );
    private final By continueBtn =By.xpath ( "//button[contains(text(),'Continue')]" );



    private final By mobileNumberAlreadyExistError = By.xpath ( "//div[2]/span[text()='The mobile is already registered with prosper']" );
    private final By emailIdAlreadyExistError = By.xpath ( "//span[text()='The email is already registered with prosper.']" );

    private final By closeSignUpPopup = By.xpath ( "/html/body/div[2]/div/div/div/div[2]/div/div/div/div/div[1]" );

    public
    SignUpPage ( ) throws InterruptedException {
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
     * @param fullName       The full name of the user.
     * @param mobileNumber   The mobile number of the user.
     * @param emailAddress   The email address of the user.
     * @param preferredLang  The preferred language for the user.
     * @return A new instance of the DashboardPage, the expected destination after signing up.
     */
    public HashMap<String,String> fillSignUpFormAndSubmitwithNewUser(String fullName, String mobileNumber, String emailAddress, String preferredLang) throws InterruptedException {

        loginPage.navigateToSignUpPage();
        if(isPageLoaded ())
        {
            if(fillUserDetails(fullName,mobileNumber,emailAddress,preferredLang).get ( "isNewUser" ))
            {
                otpPage.enterOtpAndVerify ( "email", "1234" );
                Thread.sleep ( 5000 );
                otpPage.enterOtpAndVerify ( "mobile", "1234" );
                // 3. Action: Click the "Skip, I will do it later" button to bypass document verification.
                signUpSuccessPage.clickSkipDocumentVerification ();
                introScreenData = signUpSuccessPage.getIntroScreenData ();
            }
            else
            {
                System.out.println ("Email or Mobile Number Already exist!" );
            }
        }
        return introScreenData;
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

    private HashMap<String,Boolean> fillUserDetails(String fullName, String mobileNumber, String emailAddress, String preferredLang) throws InterruptedException{
        sendKeys ( nameField , fullName , WaitStrategy.VISIBLE , "Full Name field" );
        sendKeys ( phoneField , mobileNumber , WaitStrategy.NONE , "Phone Number field" );
        sendKeys ( emailField , emailAddress , WaitStrategy.NONE , "Email field" );
        selectLanguage ( preferredLang );
        click ( agreeChkBox , WaitStrategy.CLICKABLE , "Terms and Conditions Checkbox" );
        click ( continueBtn , WaitStrategy.CLICKABLE , "Continue Button" );

        if(isElementDisplayed ( mobileNumberAlreadyExistError ))
        {
            isUserExist.put (DriverManager.getDriver ().findElement ( mobileNumberAlreadyExistError ).getText ( ),false );
            System.out.println ( STR."[DEBUG] \{DriverManager.getDriver ( ).findElement ( mobileNumberAlreadyExistError ).getText ( )}, So Initiating Login with given mobile number" );
            closeSignUpPopupAndSigninWithOtp(mobileNumber);
        }
        else if ( isElementDisplayed ( emailIdAlreadyExistError ) )
        {
            isUserExist.put (DriverManager.getDriver ().findElement ( emailIdAlreadyExistError ).getText ( ),false  );
            System.out.println ( STR."[DEBUG] \{DriverManager.getDriver ( ).findElement ( emailIdAlreadyExistError ).getText ( )}, So Initiating Login with given Email address" );
            closeSignUpPopupAndSigninWithOtp(emailAddress);
        }
        else
        {
            isNewUser = true;
            // 3. Action: Click the "Skip, I will do it later"
            // button to bypass document verification.
            signUpSuccessPage.clickSkipDocumentVerification ();
            introScreenData = signUpSuccessPage.getIntroScreenData ();
        }
        return isUserExist;
    }


    public void closeSignUpPopupAndSigninWithOtp(String otpType) throws InterruptedException {
        DriverManager.getDriver ().findElement (closeSignUpPopup) .click ();
        Thread.sleep ( 5000 );
        new LoginPage ().loginToApplicationWithOTP ( otpType,"1234" );
    }

}