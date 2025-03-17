/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic ("Epic - Login")
@Feature ("Feature - Login with OTP from Email,Login with OTP from Mobile Number ")
public class LoginTest extends BaseTest
{
    @Test (description = "Verify successful login with valid Email")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testSuccessfulLoginWithEmail()
    {
        String email = "pcaswin96@gmail.com";
        String otp ="1234";
        LoginPage loginPage = new LoginPage();
        loginPage.loginToApplication( email,otp);
    }

    @Test (description = "Verify successful login with valid Mobile Number")
    @FrameworkAnnotation (author = {  AuthorType.VISHNUDAS_K}, category = { CategoryType.REGRESSION })
    public void testSuccessfulLoginWithMobileNumber()
    {
        String mobileNumber = "+918892898820";
        String otp ="1234";
        LoginPage loginPage = new LoginPage();
        loginPage.loginToApplication( mobileNumber,otp);
    }


}
