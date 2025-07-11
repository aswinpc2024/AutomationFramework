/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.pages;

import com.framework.enums.WaitStrategy;
import org.openqa.selenium.By;

public class OTPpage extends BasePage
{
    private final By otpFieldOne =By.xpath ( "//input[@aria-label='Please enter OTP character 1']" );
    private final By otpFieldTwo =By.xpath ( "//input[@aria-label='Please enter OTP character 2']" );
    private final By otpFieldThree =By.xpath ( "//input[@aria-label='Please enter OTP character 3']" );
    private final By otpFieldFour =By.xpath ( "//input[@aria-label='Please enter OTP character 4']" );

    private final By verifyBtn = By.xpath ( "//button[contains(.,'Verify')]" );

    /**
     * Enters the 4-digit OTP and submits the form to complete the Mobile number and Email verification process.
     * This method assumes the OTP is entered into four separate input fields.
     *
     * @param otp The 4-digit OTP as a String (e.g., "1234").
     * @return A new instance of the DashboardPage, the expected destination after OTP verification.
     */
    public void enterOtpAndVerify (String otpType, String otp) {
        System.out.println ( STR."[DEBUG] Initiating OTP Verification for \{otpType}" );
        // Basic validation to ensure the OTP is in the expected format.
        if (otp == null || otp.length() != 4) {
            throw new IllegalArgumentException("OTP must be a 4-digit string.");
        }

        // Split the OTP string into individual characters
        String[] otpDigits = otp.split("");
        System.out.println ( STR."OTP is : \{otpDigits[0]} \{otpDigits[1]} \{otpDigits[2]} \{otpDigits[3]}" );
        // Send each digit to its corresponding input field
        sendKeys( otpFieldOne, otpDigits[0], WaitStrategy.VISIBLE, "OTP Digit Field 1");
        sendKeys(otpFieldTwo, otpDigits[1], WaitStrategy.VISIBLE, "OTP Digit Field 2");
        sendKeys(otpFieldThree, otpDigits[2], WaitStrategy.VISIBLE, "OTP Digit Field 3");
        sendKeys(otpFieldFour, otpDigits[3], WaitStrategy.VISIBLE, "OTP Digit Field 4");
        click ( verifyBtn, WaitStrategy.CLICKABLE, "Verify Button" );
        waitForPageLoad ();
        System.out.println ("[DEBUG] OTP Verification for "+ otpType +" Completed" );
    }

}