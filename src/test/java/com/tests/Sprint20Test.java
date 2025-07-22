package com.tests;

import com.framework.annotations.FrameworkAnnotation;
import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;
import org.testng.annotations.Test;

public class Sprint20Test extends BaseTest
{
    @Test (description = "Verify Passport Upload and verification from user")
    @FrameworkAnnotation (author = { AuthorType.ASWIN_CHANDRAN_PC}, category = { CategoryType.SANITY })

}
