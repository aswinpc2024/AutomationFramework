package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.pages.MyPropertiesPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Epic ("Epic - My Properties")
@Feature ("Feature - To Add and publish property as a User ")
public class MyPropertiesPageTest extends BaseTest{

    @Test (description = "Verify successful property add")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testSuccessfulPropertyAdd() throws InterruptedException
    {
        MyPropertiesPage myPropertiesPage = new MyPropertiesPage();
        Map<String,String> testResults;
        testResults = myPropertiesPage.addProperty( false, "aswin@pixbitsolutions.com", "1234", "Single Ownership", "Ready", "Self Occupied", "Bedrooms-4", "Bathrooms-3", "Kitchens-2", "Balconies-1", "Furnished");
        
        
        if(testResults.get("Upload Status").equals("Failed"))
        {
            Assert.assertEquals(testResults.get("Error Message"),"The title deed you submitted is already under verification. Please try again later.");
        }
        else if(testResults.get("Upload Status").equals("Success"))
        {
            Assert.assertEquals(testResults.get("Success Modal Heading"),"Success");
            Assert.assertEquals(testResults.get("Success Modal Description"),"Your Property Has Been Successfully Submitted For Verification");
        }
    }
}
