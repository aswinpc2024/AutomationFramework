/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.javaMailerAPI;

import com.framework.constants.FrameworkConstants;

public
class EmailConfig {
    public static final String SERVER = "smtp.gmail.com";
    public static final String PORT = "587";

    public static final String FROM = "<Enter your Mail>";
    public static final String PASSWORD = "<MAil App Password>";

    /* "**********@gmail.com", */
    public static final String[] TO = {"aswin@pixbitsolutions.com","vishnudas@pixbitsolutions.com","binoy@pixbitsolutions.com"};
    public static final String SUBJECT = FrameworkConstants.getProjectName();
}
