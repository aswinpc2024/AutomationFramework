/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.tests;

import com.framework.pages.SignUpPage;
import org.testng.annotations.Test;

public
class SignUpPageTest extends BaseTest
{
    @Test (description = "Verify successful login with valid Mobile Number")
    public void testSuccessfulSignUp() {
        String fullName = "Aswin Chandran PC";
        String mobileNumber = "+918892898826";
        String emailAddress = "ashwi.n@gmail.com";
        String prefferredLang = "English";
        SignUpPage signUpPage = new SignUpPage ();
        signUpPage.signupToApplication(fullName,mobileNumber,emailAddress,prefferredLang);
    }
}
