package com.framework.pages;

import com.framework.driver.DriverManager;
import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;

/**
 * Represents the Sign Up page of the application.
 * This class follows the Page Object Model pattern, providing an API
 * to interact with the sign-up form.
 */
public class SignUpPage extends BasePage {

    Boolean isNewUser;

    // --- Locators ---
    private final By nameField = By.name("name");
    private final By phoneField = By.name("phone");
    private final By emailField = By.name("email");
    private final By languageDropdown = By.xpath("//button[@role='combobox']");
    private final By agreeChkBox = By.xpath ( "//span[@class='geekmark']" );
    private final By continueBtn =By.xpath ( "//button[contains(text(),'Continue')]" );

    private final By otpFieldOne =By.xpath ( "//input[@aria-label='Please enter OTP character 1']" );
    private final By otpFieldTwo =By.xpath ( "//input[@aria-label='Please enter OTP character 2']" );
    private final By otpFieldThree =By.xpath ( "//input[@aria-label='Please enter OTP character 3']" );
    private final By otpFieldFour =By.xpath ( "//input[@aria-label='Please enter OTP character 4']" );

    private final By mobileNumberAlreadyExistError = By.xpath ( "//div[2]/span[text()='The mobile is already registered with prosper']" );
    private final By emailIdAlreadyExistError = By.xpath ( "//span[text()='The email is already registered with prosper.']" );


    private final By verifyBtn = By.xpath ( "//button[contains(.,'Verify')]" );
    private final By verifyNowBtn = By.xpath ( "//button[contains(.,'Verify now')]" );

    private final By accountCreateSuccessHeader = By.xpath ( "//p[contains(.,'Account Created')]" );
    private final By accountCreateSuccessDescription = By.xpath ( "//span[contains(.,'Thank you for signing up! Verify your documents to publish your properties with us.')]" );
    private final By skipDocumentVerificationBtn = By.xpath ( "//button[contains(.,'Skip, I will do it later')]" );

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
    public DashboardPage fillSignUpFormAndSubmit( String fullName, String mobileNumber, String emailAddress, String preferredLang) throws InterruptedException {

        if(isPageLoaded ())
        {
            if(fillUserDetails(fullName,mobileNumber,emailAddress,preferredLang))
            {
                // OTP verification for Mobile Number and Email
                enterOtpAndVerify ( "Mobile Number" , "1234" );
                Thread.sleep ( 5000 );
                enterOtpAndVerify ( "Email" , "1234" );
                Thread.sleep ( 5000 );
            }
            else
            {
                System.out.println ("Email or Mobile Number Already exist!" );
            }
        }
        return new DashboardPage();
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

    private Boolean fillUserDetails(String fullName, String mobileNumber, String emailAddress, String preferredLang) throws InterruptedException {
        sendKeys ( nameField , fullName , WaitStrategy.VISIBLE , "Full Name field" );
        sendKeys ( phoneField , mobileNumber , WaitStrategy.NONE , "Phone Number field" );
        sendKeys ( emailField , emailAddress , WaitStrategy.NONE , "Email field" );
        selectLanguage ( preferredLang );
        click ( agreeChkBox , WaitStrategy.CLICKABLE , "Terms and Conditions Checkbox" );
        click ( continueBtn , WaitStrategy.CLICKABLE , "Continue Button" );
        Thread.sleep ( 5000 );
        if(isElementDisplayed ( mobileNumberAlreadyExistError ))
        {
            isNewUser=false;
            System.out.println ("[DEBUG] Mobile Number already exist " );
            System.out.println ("[DEBUG] "+DriverManager.getDriver ().findElement ( mobileNumberAlreadyExistError ).getText ( ) );
        }
        else if ( isElementDisplayed ( emailIdAlreadyExistError ) )
        {
            isNewUser = false;
            System.out.println ("[DEBUG] Email already exist " );
            System.out.println ("[DEBUG] " +DriverManager.getDriver ().findElement ( emailIdAlreadyExistError ).getText () );
        }
        else
        {
            isNewUser = true;
        }
        return isNewUser;
    }

    /**
     * Enters the 4-digit OTP and submits the form to complete the Mobile number and Email verification process.
     * This method assumes the OTP is entered into four separate input fields.
     *
     * @param otp The 4-digit OTP as a String (e.g., "1234").
     * @return A new instance of the DashboardPage, the expected destination after OTP verification.
     */
    public void enterOtpAndVerify (String otpType, String otp) {
        System.out.println ("[DEBUG] Initiating OTP Verification for "+otpType);
        // Basic validation to ensure the OTP is in the expected format.
        if (otp == null || otp.length() != 4) {
            throw new IllegalArgumentException("OTP must be a 4-digit string.");
        }

        // Split the OTP string into individual characters
        String[] otpDigits = otp.split("");
        System.out.println ("OTP is : "+otpDigits[0]+" "+otpDigits[1]+" "+otpDigits[2]+" "+otpDigits[3] );
        // Send each digit to its corresponding input field
        sendKeys(otpFieldOne, otpDigits[0], WaitStrategy.VISIBLE, "OTP Digit Field 1");
        sendKeys(otpFieldTwo, otpDigits[1], WaitStrategy.VISIBLE, "OTP Digit Field 2");
        sendKeys(otpFieldThree, otpDigits[2], WaitStrategy.VISIBLE, "OTP Digit Field 3");
        sendKeys(otpFieldFour, otpDigits[3], WaitStrategy.VISIBLE, "OTP Digit Field 4");
        click ( verifyBtn, WaitStrategy.CLICKABLE, "Verify Button" );
        waitForPageLoad ();
        System.out.println ("[DEBUG] OTP Verification for "+ otpType +" Completed" );
    }
}