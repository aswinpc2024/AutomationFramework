package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.pages.DashboardPage; // Assuming this is the page after successful sign-up
import com.framework.pages.LoginPage;
import com.framework.pages.SignUpPage;
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

        // 1. Navigation: Start from the login page and navigate to the sign-up page.
        // This returns the SignUpPage object, ensuring we are on the correct page.
        LoginPage loginPage = new LoginPage();
        SignUpPage signUpPage = loginPage.navigateToSignUpPage(); // Assuming LoginPage has this navigation method

        // 2. Action: Fill the sign-up form and submit.
        // It's good practice for the action method to return the next page object.
        DashboardPage dashboardPage = signUpPage.fillSignUpFormAndSubmit(fullName, mobileNumber, emailAddress, preferredLang);

        // 3. Assertion: Verify that the sign-up was successful.
        // A good verification is to check if we landed on the expected page (e.g., a Dashboard).
//        Assert.assertTrue(dashboardPage.isPageLoaded(), "User was not redirected to the Dashboard after sign-up.");
//        Assert.assertEquals(dashboardPage.getWelcomeMessage(), "Welcome, " + fullName, "The welcome message is incorrect.");
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
                {"Vishnudas K", "+918892898880", "ashwi.n10n@gmail.com", "Arabic"},
                // You can add more data sets here to test other scenarios
                // {"Test User Two", "+15551234567", "test.user@example.com", "Spanish"}
        };
    }
}