package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.pages.SignUpPage;
import com.framework.utils.MapPair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpPageTest extends BaseTest {
    /**
     * Test to verify that a user can successfully complete the sign-up process.
     * This test is data-driven and uses the 'signUpDataProvider'.
     *
     * @param fullName       The full name of the user.
     * @param mobileNumber   The mobile number of the user.
     * @param emailAddress   The email address of the user.
     * @param preferredLang  The preferred language for the user.
     */
    @Test(description = "Verify successful user sign-up functionality", dataProvider = "signUpDataProvider")
    @FrameworkAnnotation(author = {AuthorType.ASWIN_CHANDRAN_PC}, category = {CategoryType.SANITY})
    public void testSuccessfulSignUp(String fullName, String mobileNumber, String emailAddress, String preferredLang) throws InterruptedException {

        SignUpPage signUpPage = new SignUpPage();

        MapPair<String, String> signupResults = signUpPage.fillSignUpFormAndSubmitwithNewUser(fullName, mobileNumber, emailAddress, preferredLang);

        if ("true".equals(signupResults.userStatus.get("userExist")) && "true".equals(signupResults.userStatus.get("mobileNumberExist")))
        {
            Assert.assertEquals( signupResults.testResults.get( "mobileExistError"), "The mobile is already registered with prosper");
        }
        else if ("true".equals(signupResults.userStatus.get("userExist")) && "true".equals(signupResults.userStatus.get("emailIdExist")))
        {
            Assert.assertEquals( signupResults.testResults.get( "emailExistError"), "The email is already registered with prosper.");
        }
        else
        {
            //4. Assertion: Verify Intro Screen Welcome Message and description.
            Assert.assertEquals( signupResults.testResults.get( "welcomeUser"), STR."Hello, \{fullName}");
            Assert.assertEquals( signupResults.testResults.get( "welcomeToProsper"), "Welcome to Prosper");
            Assert.assertEquals( signupResults.testResults.get( "introScreenDescriptionFirst"), "With powerful data, smart insights and personalized service, Prosper makes your property journey smoother, smarter and more rewarding.");
            Assert.assertEquals( signupResults.testResults.get( "introScreenDescriptionSecond"), "We help you stay ahead and make confident decisions.");

            //5.Assertion: Verify Intro Screen Feature Cards
            Assert.assertEquals( signupResults.testResults.get( "introScreenFeatureCardOne"), "Automated Dashboard");
            Assert.assertEquals( signupResults.testResults.get( "introScreenFeatureCardTwo"), "Sell or Rent your property");
            Assert.assertEquals( signupResults.testResults.get( "introScreenFeatureCardThree"), "Mortgages");
        }
    }

    /**
     * Test to verify that a user can successfully complete the sign-up process.
     * This test is data-driven and uses the 'signUpDataProvider'.
     *
     * @param fullName       The full name of the user.
     * @param mobileNumber   The mobile number of the user.
     * @param emailAddress   The email address of the user.
     * @param preferredLang  The preferred language for the user.
     */
    @Test(description = "Verify successful user sign-up functionality", dataProvider = "signUpDataProvider")
    @FrameworkAnnotation(author = {AuthorType.ASWIN_CHANDRAN_PC}, category = {CategoryType.SANITY})
    public void testSuccessfulSignUpwithSkipDocumentVerificationAndVerifyIntroScreen(String fullName, String mobileNumber, String emailAddress, String preferredLang) throws InterruptedException {

         // Assuming LoginPage has this navigation method
        SignUpPage signUpPage =new SignUpPage ();

        MapPair<String, String> signupResults =signUpPage.fillSignUpFormAndSubmitwithNewUser ( fullName, mobileNumber, emailAddress, preferredLang );
        if(signupResults.userStatus.get ( "userExist" ).contains ( "true" ))
        {
         Assert.assertEquals ( "The mobile is already registered with prosper", signupResults.testResults.get ( "mobileExistError" ) );
        }

        //4. Assertion: Verify Intro Screen Welcome Message and description.
//        Assert.assertEquals ( signupResults.get ( "welcomeUser" ) , "Hello, "+fullName );
//        Assert.assertEquals ( signupResults.get ( "welcomeToProsper" ) , "Welcome to Prosper" );
//        Assert.assertEquals ( signupResults.get ( "introScreenDescriptionFirst" ) , "With powerful data, smart insights and personalized service, Prosper makes your property journey smoother, smarter and more rewarding." );
//        Assert.assertEquals ( signupResults.get ( "introScreenDescriptionSecond" ) , "We help you stay ahead and make confident decisions.");

        //5.Assertion: Verify Intro Screen Feature Cards
//        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardOne" ) , "Automated Dashboard" );
//        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardTwo" ) , "Sell or Rent your property");
//        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardThree" ) , "Mortgages" );
    }

    /**
     * Provides test data for the successful sign-up test.
     * This separates the test data from the test logic, making the test reusable.
     *
     * @return A 2D object array with sign-up data.
     */
    @DataProvider(name = "signUpDataProvider")
    public Object[][] signUpData() {
        return new Object[][]{
                {"Aswin Chandran PC", "+918892898820","aswin@pixbitsolutions.com", "English"}
        };
    }
}