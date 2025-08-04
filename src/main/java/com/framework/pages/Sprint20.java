package com.framework.pages;

import com.framework.utils.MapPair;

public class Sprint20 extends BasePage
{
    public void passportVerification () throws InterruptedException {
        SignUpPage signUpPage = new SignUpPage ();
        MapPair<String,String> results = signUpPage.fillSignUpFormAndSubmitWithNewUser ( "Arjun", "+918856879849", "qa36@gmail.com", "English" , "true","true");
    }
}

