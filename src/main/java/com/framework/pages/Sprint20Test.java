package com.framework.pages;

public class Sprint20Test extends BasePage
{
    LoginPage loginPage = new LoginPage();


    public void passportVerification()
    {
        loginPage.loginToApplicationWithOTP ( "mobile","1234" );

    }

}
