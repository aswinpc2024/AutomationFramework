package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.pages.Sprint19;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public
class Sprint19Test extends BaseTest
{
    @Test (description = "Verify share feedback from user")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testShareFeedbacksFieldsAndPlaceholder() {
        Sprint19 sprint19 = new Sprint19();
        Map<String, String> results = sprint19.checkShareFeedbackFieldsTextAndPlaceholders ();

        String heading = results.get ("heading");
        String subHeading = results.get ("subHeading");

        String categoryLabel = results.get ("categoryLabel");
        String categoryPlaceholder = results.get ("categoryPlaceholder");

        String titleLabel = results.get ("titleLabel");
        String titlePlaceholder = results.get ("titlePlaceholder");

        String descriptionLabel = results.get ("descriptionLabel");
        String descriptionPlaceholder = results.get ("descriptionPlaceholder");

        String addScreenshotsLabel = results.get ("labelAddScreenshots");
        String addscreenshotsPlaceholder = results.get ("placeholderAddScreenshot");

        //Assertion
        Assert.assertEquals ( heading,"Let us know your feedbacks" );
        Assert.assertEquals ( subHeading,"Help us improve your experience by sharing your thoughts and suggestions. Your feedback matters!" );

        Assert.assertEquals ( categoryLabel,"Category" );
        Assert.assertEquals ( categoryPlaceholder,"Others" );

        Assert.assertEquals ( titleLabel,"Title"  );
        Assert.assertEquals ( titlePlaceholder ,"Enter title" );

        Assert.assertEquals ( descriptionLabel,"Description" );
        Assert.assertEquals ( descriptionPlaceholder,"Type your feedback..." );

        Assert.assertEquals ( addScreenshotsLabel,"Add screenshots (Optional)" );
        Assert.assertEquals ( addscreenshotsPlaceholder,"upload image" );
    }

    @Test (description = "Verify the navigation bredcrumb is shown correctly")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testNavigationBredcrumbFeedbackShare() {
        Sprint19 sprint19 = new Sprint19();
        String result = sprint19.checkBredcrumbMenu();
        Assert.assertEquals ( result,"Share Feedback");
    }

    @Test (description = "Verify the create feedback feature with valid feedback")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testCreateFeedback() throws InterruptedException {
        Sprint19 sprint19 =new Sprint19 ();
        Map<String,String> results = sprint19.createFeedback( "Customer Support Responsiveness", "Hello", "World");

        Assert.assertEquals ( results.get ( "feedbackSuccessPopupTitle" ),"Success" );
        Assert.assertEquals ( results.get ( "feedbackSuccessMessage" ),"Feedback sent successfully!\n"
               + "Thank you for helping us improve" );
       Assert.assertEquals ( results.get("btnFeedbackSuccess"),"Done");
    }

    @Test (description = "Verify blogs listed")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testBlogs() throws InterruptedException, IOException {
        Sprint19 sprint19 = new Sprint19 ();
        List<String> expectedBlogs = Sprint19.blogApiResponse ();
        List<String> actualBlogs = sprint19.blogs();

        for (String expected : expectedBlogs)
        {
            if (actualBlogs.contains(expected))
            {
                System.out.println(expected + " ➜ Match found");
            } else
            {
                System.out.println(expected + " ➜ No match");
            }
        }
    }

//    @Test (description = "Verify the create feedback feature with empty fields")
//    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
//    public void testCreateFeedbackWithEmptyFields() throws InterruptedException
//    {
//        Sprint19 sprint19 =new Sprint19 ();
//        Map<String,String> results = sprint19.createFeedback( "", "", "");
//
//        Assert.assertEquals ( results.get ( "feedbackSuccessPopupTitle" ),"Success" );
//        Assert.assertEquals ( results.get ( "feedbackSuccessMessage" ),"Feedback sent successfully!\n"
//               + "Thank you for helping us improve" );
////       Assert.assertEquals ( results.get("btnFeedbackSuccess"),"Done");
//    }
}
