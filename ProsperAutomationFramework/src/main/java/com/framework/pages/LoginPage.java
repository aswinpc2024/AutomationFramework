/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.pages;

import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;


public class LoginPage extends BasePage {
    private final By txtLoginBy = By.xpath ( "//input[@id='name']");
    private final By btnLogin = By.id("login-btn-desktop");
    private final By lblErrorMessage = By.id("errorMessage");
    private final By btnSignUp = By.xpath ( "//strong[1]" );
    private final By btnGetOtp = By.xpath ( "//button[normalize-space()='GET OTP']" );
    private final By txtOTP = By.name ( "otp");

    public void enterEmail(String username) {
        sendKeys( txtLoginBy, username, WaitStrategy.NONE, "Username field");
    }

    public LoginPage enterMobileNumber(String mobNo) {
        sendKeys(txtLoginBy, mobNo, WaitStrategy.VISIBLE, "Password field");
        return this;
    }

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

    public HomePage loginToApplication(String loginBy,String otp)
    {
        HomePage homePage = clickLogin();
        enterEmail(loginBy);
        click ( btnGetOtp, WaitStrategy.CLICKABLE, "GET OTP button " );
        sendKeys ( txtOTP,otp, WaitStrategy.VISIBLE, "OTP field " );
        return homePage;
    }
}
