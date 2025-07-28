package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import com.framework.pages.Sprint20;
import org.testng.annotations.Test;

public class Sprint20Test extends BaseTest
{
    @Test (description = "Verify passport upload and verification for Non-Resident user")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })
    public void testNonUaeResidentPassportVerification () throws InterruptedException
    {
        Sprint20 sprint20 = new Sprint20();
        sprint20.passportVerification ();
    }
}
