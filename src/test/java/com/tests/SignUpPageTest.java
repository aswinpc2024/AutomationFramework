package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.pages.LoginPage;
import com.framework.pages.SignUpPage;
import com.framework.pages.SignUpSuccessPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

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

        SignUpPage signUpPage = new SignUpPage ();
        // 2. Action: Fill the sign-up form and submit.
        // It's good practice for the action method to return the next page object.
        HashMap<String,String> signupResults = signUpPage.fillSignUpFormAndSubmitwithNewUser ( fullName, mobileNumber, emailAddress, preferredLang);

        //4. Assertion: Verify Intro Screen Welcome Message and description.
        Assert.assertEquals ( signupResults.get ( "welcomeUser" ) , "Hello, "+fullName );
        Assert.assertEquals ( signupResults.get ( "welcomeToProsper" ) , "Welcome to Prosper" );
        Assert.assertEquals ( signupResults.get ( "introScreenDescriptionFirst" ) , "With powerful data, smart insights and personalized service, Prosper makes your property journey smoother, smarter and more rewarding." );
        Assert.assertEquals ( signupResults.get ( "introScreenDescriptionSecond" ) , "We help you stay ahead and make confident decisions.");

        //5.Assertion: Verify Intro Screen Feature Cards
        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardOne" ) , "Automated Dashboard" );
        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardTwo" ) , "Sell or Rent your property");
        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardThree" ) , "Mortgages" );
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

        HashMap<String,String> signupResults =signUpPage.fillSignUpFormAndSubmitwithNewUser ( fullName, mobileNumber, emailAddress, preferredLang );

        //4. Assertion: Verify Intro Screen Welcome Message and description.
        Assert.assertEquals ( signupResults.get ( "welcomeUser" ) , "Hello, "+fullName );
        Assert.assertEquals ( signupResults.get ( "welcomeToProsper" ) , "Welcome to Prosper" );
        Assert.assertEquals ( signupResults.get ( "introScreenDescriptionFirst" ) , "With powerful data, smart insights and personalized service, Prosper makes your property journey smoother, smarter and more rewarding." );
        Assert.assertEquals ( signupResults.get ( "introScreenDescriptionSecond" ) , "We help you stay ahead and make confident decisions.");

        //5.Assertion: Verify Intro Screen Feature Cards
        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardOne" ) , "Automated Dashboard" );
        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardTwo" ) , "Sell or Rent your property");
        Assert.assertEquals ( signupResults.get ( "introScreenFeatureCardThree" ) , "Mortgages" );
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
                {"OJASVI", "+971501975289","ojasvi@gmail.com", "English"}
                // {"Test User Two", "+15551234567", "test.user@example.com", "Spanish"}
        };
    }
}