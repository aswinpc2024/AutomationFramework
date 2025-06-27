/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.pages;

import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;


public class LoginPage extends BasePage
{
    // Locators for elements on the Login page/modal
    public final By txtLoginBy = By.xpath ( "//input[@id='name']");
    public final By btnLogin = By.id("login-btn-desktop");
    public final By lblErrorMessage = By.id("errorMessage");
    public final By btnSignUp = By.xpath ( "//strong[1]" );
    public final By btnGetOtp = By.xpath ( "//button[normalize-space()='GET OTP']" );
    public final By txtOTP = By.name ( "otp");
    public final By btnGetPassword = By.xpath ( "(//button[normalize-space()='Login With Password'])[1]");
    public final By txtPassword = By.id ( "password");
    public final By btnContinue = By.xpath ( "(//button[normalize-space()='Continue'])[1]");


    public void enterEmail(String username) {
        sendKeys( txtLoginBy, username, WaitStrategy.NONE, "Username field");
    }

    // Note: The return type here is unconventional. It creates a new HomePage instance
    // before the login is complete. While it works for your current tests,
    // a 'void' or 'LoginPage' return type would be more standard.
    public HomePage clickLogin() {
        click(btnLogin, WaitStrategy.CLICKABLE, "Login button");
        return new HomePage ();
    }

    public void clickSignUp(){
        click(btnSignUp, WaitStrategy.CLICKABLE, "Sign Up button");
    }

    public String getErrorMessage() {
        return getText(lblErrorMessage, WaitStrategy.VISIBLE);
    }

    public HomePage loginToApplicationWithOTP(String loginBy,String otp)
    {
        clickLogin();
        enterEmail(loginBy);
        click ( btnGetOtp, WaitStrategy.CLICKABLE, "GET OTP button " );
        sendKeys ( txtOTP,otp, WaitStrategy.VISIBLE, "OTP field " );
        click ( btnContinue,WaitStrategy.CLICKABLE,"Continue Button" );
        return new HomePage();
    }

    public HomePage loginToApplicationWithPassword (String loginBy, String password)
    {
        clickLogin();
        enterEmail(loginBy);
        click ( btnGetPassword, WaitStrategy.CLICKABLE, "Login With Password Button " );
        sendKeys ( txtPassword,password, WaitStrategy.VISIBLE, "Password field " );
        click ( btnContinue,WaitStrategy.CLICKABLE,"Continue Button" );
        return new HomePage();
    }

    /**
     * Navigates to the Sign Up page.
     * This business-level method encapsulates the flow of opening the login modal
     * and then clicking the sign-up link.
     *
     * @return A new instance of the SignUpPage.
     */
    public SignUpPage navigateToSignUpPage() {
        // Step 1: Click the main login button to open the login form.
        click(btnLogin, WaitStrategy.CLICKABLE, "Login button");

        // Step 2: Click the Sign Up link within the form.
        click(btnSignUp, WaitStrategy.CLICKABLE, "Sign Up button");

        return new SignUpPage();
    }
}