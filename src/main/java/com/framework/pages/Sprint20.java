package com.framework.pages;

import com.framework.utils.MapPair;

public class Sprint20 extends BasePage
{
    public void passportVerification () throws InterruptedException {
        SignUpPage signUpPage = new SignUpPage ();
        MapPair<String,String> results = signUpPage.fillSignUpFormAndSubmitWithNewUser ( "Aswin", "+918856879855", "qa01@gmail.com", "English" , "true","true");
    }
}

