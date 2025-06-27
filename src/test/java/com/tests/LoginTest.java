/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.driver.DriverManager;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic ("Epic - Login")
@Feature ("Feature - Login with Email and Mobile Number")
public class LoginTest extends BaseTest
{
    @Test (description = "Verify successful login with valid Email and OTP")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testSuccessfulLoginWithEmailAndOTP()
    {
        String email = "aswin@pixbitsolutions.com";
        String otp = "1234";
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.loginToApplicationWithOTP(email, otp);
        Assert.assertTrue(DriverManager.getDriver().findElement(homePage.prosperLogo).isDisplayed());
    }

    @Test (description = "Verify successful login with valid Mobile Number and OTP")
    @FrameworkAnnotation (author = {  AuthorType.VISHNUDAS_K}, category = { CategoryType.REGRESSION })
    public void testSuccessfulLoginWithMobileNumberAndOTP()
    {
        String mobileNumber = "8892898820";
        String otp ="1234";
        LoginPage loginPage = new LoginPage();
        loginPage.loginToApplicationWithOTP ( mobileNumber, otp);
    }

    @Test (description = "Verify successful login with valid Mobile Number and Password")
    @FrameworkAnnotation (author = {  AuthorType.VISHNUDAS_K}, category = { CategoryType.REGRESSION })
    public void testSuccessfulLoginWithMobileNumberAndPassword()
    {
        String mobileNumber = "+91 8892898820";
        String password ="Qapixbit@14";
        LoginPage loginPage = new LoginPage();
        loginPage.loginToApplicationWithPassword ( mobileNumber, password);
    }

    @Test (description = "Verify successful login with valid Email and Password")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testSuccessfulLoginWithEmailAndPassword()
    {
        String email = "aswin@pixbitsolutions.com";
        String password ="Qapixbit@14";
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.loginToApplicationWithPassword ( email, password);
        Assert.assertTrue ( DriverManager.getDriver ().findElement ( homePage.prosperLogo ).isDisplayed () );
    }
}
