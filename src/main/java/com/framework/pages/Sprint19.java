package com.framework.pages;

import com.framework.driver.DriverManager;
import com.framework.enums.WaitStrategy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Sprint19 extends BasePage {

    LoginPage loginPage = new LoginPage ( );
    HomePage homePage;

    /* ************************************************************************************************************** */
    public final By btnShareFeedback = By.xpath ( "/html/body/div/div[1]/main/div/div/div[3]/div/div[2]/div[2]/div/div[8]/div" );
    public final By drpdwnFeedbackCategory = By.xpath ( "//form/div/div[1]/div/div/button" );
    public final By drpdwnFeedback = By.xpath ( "//form/div/div[1]/div/div/input[@placeholder='Others']" );
    public final By dropDownPlaceholder = By.xpath ( "//form/div/div[1]/div/div/input" );
    public final By feedbackCategory = By.xpath ( "(//span[normalize-space()='Others'])[1]" );
    public final By profileBtn = By.xpath ( "(//button[normalize-space()='Profile'])[1]" );
    public final By navigationBredcrumb = By.xpath ( "/html/body/div/div[1]/main/div/div/div[2]/ul/li[3]/p" );
    public final By headingShareFeedback = By.xpath ( "(//div/p[1])[7]" );
    public final By subHeadingShareFeedback = By.xpath ( "(//div/p[2])[1]" );
    public final By labelFeedbackCategory = By.xpath ( "(//form/div/div[1]/label)[1]" );
    public final By titleLabel = By.xpath ( "//form/div/div[2]/label" );
    public final By descriptionLabel = By.xpath ( "//form/div/div[3]/label" );
    public final By titlePalceholder = By.xpath ( "//form/div/div[2]/input" );
    public final By descriptionPalceholder = By.xpath ( "//form/div/div[3]/textarea" );
    public final By addScreenshotsLabel = By.xpath ( "//form/div/div[4]/div/div/label" );
    public final By addScreenshotsPlaceholder = By.xpath ( "//form/div/div[4]/div/label/span" );

    public final By marketInsightsMenu = By.xpath("//div/button[3]");
    public final By blogMenu = By.xpath("//div/span[contains(.,'Blogs')]");


    /* ************************************************************************************************************** */
    /*                                          Share Feedback                                                                 */
    /* ************************************************************************************************************** */

    public Map<String, String> checkShareFeedbackFieldsTextAndPlaceholders () {

        String email = "aswin@pixbitsolutions.com";
        String otp ="1234";
        HashMap<String,String> results = new HashMap<> ();

        homePage = loginPage.loginToApplicationWithOTP ( email,otp );
        click ( profileBtn, WaitStrategy.CLICKABLE ,"Profile Button");
        click ( btnShareFeedback,WaitStrategy.CLICKABLE,"Share Feedbacks button");
        highlightByElement ( headingShareFeedback );
        highlightByElement ( subHeadingShareFeedback );
        click ( drpdwnFeedbackCategory,WaitStrategy.CLICKABLE,"Share Feedback Category dropdown" );
        highlightByElement ( drpdwnFeedbackCategory );
        highlightByElement ( labelFeedbackCategory );
        highlightByElement ( dropDownPlaceholder );
        highlightByElement ( titleLabel );
        highlightByElement ( descriptionLabel );
        highlightByElement ( addScreenshotsLabel );

        String labelShareFeedbackHeading = DriverManager.getDriver ().findElement ( headingShareFeedback ).getText ();
        String labelShareFeedbackSubHeading = DriverManager.getDriver ().findElement ( subHeadingShareFeedback ).getText ();
        String labelShareFeedbackCategory = DriverManager.getDriver ().findElement ( labelFeedbackCategory ).getText ();
        String labelTitle = DriverManager.getDriver ().findElement ( titleLabel ).getText ();
        String labelDescription = DriverManager.getDriver ().findElement (descriptionLabel).getText ();
        String labelAddScreenshots = DriverManager.getDriver ().findElement ( addScreenshotsLabel ).getText ();

        String placeholderSharefeedbackCategory = DriverManager.getDriver ().findElement ( dropDownPlaceholder ).getDomAttribute ( "placeholder" );
        String placeholderTitle = DriverManager.getDriver ().findElement ( titlePalceholder ).getDomAttribute ( "placeholder" );
        String placeholderDescription = DriverManager.getDriver ().findElement ( descriptionPalceholder ).getDomAttribute ( "placeholder" );
        String placeholderAddScreenshot = DriverManager.getDriver ().findElement ( addScreenshotsPlaceholder ).getText ();

        System.out.println ( "Heading                    :"+ labelShareFeedbackHeading);
        System.out.println ( "Sub-heading                :"+ labelShareFeedbackSubHeading);

        System.out.println ( "Category Label             :"+ labelShareFeedbackCategory);
        System.out.println ( "Category Placeholder       :"+ placeholderSharefeedbackCategory);

        System.out.println ( "Title Label                :"+ labelTitle);
        System.out.println ( "Title Placeholder          :"+ placeholderTitle);

        System.out.println ( "Description Label          :"+ labelDescription);
        System.out.println ( "Description Placeholder    :"+ placeholderDescription);

        System.out.println ( "Add Screenshot Label       :"+ labelAddScreenshots);
        System.out.println ( "Add Screenshot Placeholder :"+ placeholderAddScreenshot);

        results.put ( "heading",labelShareFeedbackHeading );
        results.put ( "subHeading",labelShareFeedbackSubHeading );

        results.put ( "categoryLabel",labelShareFeedbackCategory );
        results.put ( "categoryPlaceholder",placeholderSharefeedbackCategory );

        results.put ( "titleLabel",labelTitle );
        results.put ( "titlePlaceholder",placeholderTitle);

        results.put ( "descriptionLabel",labelDescription );
        results.put ( "descriptionPlaceholder",placeholderDescription);

        results.put ( "labelAddScreenshots",labelAddScreenshots );
        results.put ( "placeholderAddScreenshot",placeholderAddScreenshot);

        return results;
    }

    public String checkBredcrumbMenu() {
        String email = "aswin@pixbitsolutions.com";
        String otp ="1234";

        homePage = loginPage.loginToApplicationWithOTP ( email,otp );
        click ( profileBtn, WaitStrategy.CLICKABLE ,"Profile Button");
        click ( btnShareFeedback,WaitStrategy.CLICKABLE,"Share Feedbacks button");
        waitForPageLoad ();
        highlightByElement ( navigationBredcrumb);
        return DriverManager.getDriver().findElement (navigationBredcrumb).getText();
    }

    public Map<String, String> createFeedback(String feedBackCategory, String feedbackTitle, String feedbackDescription) throws InterruptedException {

        String email = "aswin@pixbitsolutions.com";
        String otp ="1234";

        final By feedbackCategories =By.xpath ( "//form/div/div[1]/div/ul/li[*]");
        final By sendFeedbackBtn = By.xpath("//button[normalize-space()='Send Feedback']");
        final By feedbackSuccessHeader = By.xpath ( "//div[2]/div/div/div/div/div/p" );
        final By feedbackSuccessDescription = By.xpath ( "//div[2]/div/div/div/div/div/span" );

        homePage = loginPage.loginToApplicationWithOTP ( email,otp );
        openFeedbackForm();
        click ( drpdwnFeedbackCategory,WaitStrategy.CLICKABLE,"Share Feedback Category dropdown" );
        String matchedCategory = selectFeedbackCategory(feedbackCategories,feedBackCategory);
        System.out.println ("[DEBUG] Feedback Category set to "+ matchedCategory );
        DriverManager.getDriver ().findElement ( By.xpath ( "//input[@placeholder='Enter title']" ) ).sendKeys ( feedbackTitle );
        DriverManager.getDriver().findElement(By.xpath ( "//textarea[@placeholder='Type your feedback...']" )).sendKeys ( feedbackDescription );

        Thread.sleep ( 5000 );

        DriverManager.getDriver().findElement(sendFeedbackBtn).click();

        Thread.sleep ( 5000 );

        String feedbackSuccessPopupTitle = DriverManager.getDriver().findElement ( feedbackSuccessHeader ).getText ();
        String feedbackSuccessMessage = DriverManager.getDriver ().findElement ( feedbackSuccessDescription).getText ();

        Map<String,String> feedbacks = new HashMap<> ();
        feedbacks.put ("feedbackSuccessPopupTitle",feedbackSuccessPopupTitle);
        feedbacks.put ("feedbackSuccessMessage",feedbackSuccessMessage  );

        return feedbacks;
    }

    public void openFeedbackForm() {
        click(profileBtn, WaitStrategy.CLICKABLE, "Profile");
        click(btnShareFeedback, WaitStrategy.CLICKABLE, "Feedbacks");
        waitForPageLoad ();
    }

    /* ************************************************************************************************************** */
    /*                                          Blogs                                                                 */
    /* ************************************************************************************************************** */
    public static List<String> blogApiResponse ( ) throws IOException {
            // API URL
            URL url = new URL ( "http://blog.prosper.pixbit.in/content-manager/collection-types/api::blog.blog?page=1&pageSize=10&sort=title%3AASC"); // Sample GET API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Request type
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty ( "Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwiaWF0IjoxNzQ0ODc5MjYwLCJleHAiOjE3NDc0NzEyNjB9.3ZWtNLyjiPOPZClJ_ENncwjFpNlkuyzm4rFJ99zzOdA" );

            // Read response
            BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();

            List<String> blogs = new ArrayList<> ();
            // Parse response into JSON
            JSONObject jsonResponse = new JSONObject( response.toString());
            JSONArray results = jsonResponse.getJSONArray("results");
            // Loop through and print titles
            for (int i = 0; i < results.length(); i++)
            {
                if(results.getJSONObject ( i ).getString ( "status" ).contains ( "draft" ))
                {
                    JSONObject blog = results.getJSONObject ( i );
                    String title = blog.getString ( "title" );
                    System.out.println (title );
                    blogs.add ( title );
                }
            }
            return blogs;

    }

    public List<String> blogs() throws InterruptedException {
        String email = "aswin@pixbitsolutions.com";
        String otp ="1234";
        homePage = loginPage.loginToApplicationWithOTP ( email,otp );
        Thread.sleep ( 3000 );
        click ( marketInsightsMenu, WaitStrategy.CLICKABLE ,"market Insights Menu");
        waitForPageLoad ();
        click ( blogMenu, WaitStrategy.CLICKABLE ,"Blog Menu");
        Thread.sleep(5000);
        List<String> actualBlogs = new ArrayList<> ();
        List<WebElement> blogs = DriverManager.getDriver().findElements ( By.xpath ( "//div[2]/a[*]/div/div[2]/div/h2" ) );
        for ( WebElement blog : blogs )
        {
            highlightXpathElement (blog);
            System.out.println (blog.getText());
            actualBlogs.add (blog.getText());
        }
        return actualBlogs;
    }

    public static void main (String[] args) throws IOException
    {

        try {

            HttpClient client = HttpClient.newHttpClient();
            for(int i=101;i<=500;i++) {
                String jsonInput = "{\n"
                        + "  \"title\": \"Test API Create Blog "
                        + i + ""
                        + "\",\n"
                        + "  \"description\": \"Test API Create Blog\",\n"
                        + "  \"slug\": \"test-postman-"+i+"\",\n"
                        + "  \"readingTime\": 1,\n"
                        + "  \"author\": {\n"
                        + "        \"connect\": [\n"
                        + "            {\n"
                        + "                \"id\": 1,\n"
                        + "                \"documentId\": \"n65kxle5rje77qds21xaf66b\"\n"
                        + "            }\n"
                        + "        ]\n"
                        + "    },\n"
                        + "  \"category\": {\n"
                        + "        \"connect\": [\n"
                        + "            {\n"
                        + "                \"id\": 1,\n"
                        + "                \"documentId\": \"hb4kmqrinrvdl6zdxtfddasb\"\n"
                        + "            }\n"
                        + "        ]\n"
                        + "    },\n"
                        + "  \n"
                        + "  \"faqs\": [],\n"
                        + "  \"content\": [\n"
                        + "    {\n"
                        + "      \"id\": 352,\n"
                        + "      \"content\": \"Test postman 2\",\n"
                        + "      \"title\": \"Test postman 3\",\n"
                        + "      \"__temp_key__\": \"a0\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"id\": 353,\n"
                        + "      \"content\": \"Test postman 5\",\n"
                        + "      \"title\": \"Test postman 4\",\n"
                        + "      \"__temp_key__\": \"a1\"\n"
                        + "    }\n"
                        + "  ],\n"
                        + "  \"cover\": {\n"
                        + "    \"id\": 10,\n"
                        + "    \"documentId\": \"a24rrdwipejou8d7fzprqos9\",\n"
                        + "    \"name\": \"Hot-new-property-launches-dubai-v2-april-2025.webp\",\n"
                        + "    \"alternativeText\": null,\n"
                        + "    \"caption\": null,\n"
                        + "    \"width\": 1440,\n"
                        + "    \"height\": 626,\n"
                        + "    \"formats\": {\n"
                        + "      \"large\": {\n"
                        + "        \"ext\": \".webp\",\n"
                        + "        \"url\": \"/uploads/large_Hot_new_property_launches_dubai_v2_april_2025_92fc0aafaf.webp\",\n"
                        + "        \"hash\": \"large_Hot_new_property_launches_dubai_v2_april_2025_92fc0aafaf\",\n"
                        + "        \"mime\": \"image/webp\",\n"
                        + "        \"name\": \"large_Hot-new-property-launches-dubai-v2-april-2025.webp\",\n"
                        + "        \"path\": null,\n"
                        + "        \"size\": 45.46,\n"
                        + "        \"width\": 1000,\n"
                        + "        \"height\": 435,\n"
                        + "        \"sizeInBytes\": 45464\n"
                        + "      }\n"
                        + "    },\n"
                        + "    \"hash\": \"Hot_new_property_launches_dubai_v2_april_2025_92fc0aafaf\",\n"
                        + "    \"ext\": \".webp\",\n"
                        + "    \"mime\": \"image/webp\",\n"
                        + "    \"size\": 95.76,\n"
                        + "    \"url\": \"/uploads/Hot_new_property_launches_dubai_v2_april_2025_92fc0aafaf.webp\",\n"
                        + "    \"previewUrl\": null,\n"
                        + "    \"provider\": \"local\",\n"
                        + "    \"provider_metadata\": null,\n"
                        + "    \"folderPath\": \"/\",\n"
                        + "    \"createdAt\": \"2025-04-10T04:49:16.407Z\",\n"
                        + "    \"updatedAt\": \"2025-04-10T04:50:20.697Z\",\n"
                        + "    \"publishedAt\": \"2025-04-10T04:49:16.408Z\",\n"
                        + "    \"locale\": null,\n"
                        + "    \"folder\": null,\n"
                        + "    \"isUrlSigned\": false,\n"
                        + "    \"isSelectable\": true,\n"
                        + "    \"type\": \"asset\"\n"
                        + "  }\n"
                        + "}";

                HttpRequest request = HttpRequest.newBuilder ( )
                        .uri ( URI.create ( "http://blog.prosper.pixbit.in/content-manager/collection-types/api::blog.blog/actions/publish?" ) )
                        .header ( "Content-Type" , "application/json" )
                        .headers ( "Authorization" , "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwiaWF0IjoxNzQ0OTQ1NTQzLCJleHAiOjE3NDc1Mzc1NDN9.ExBFxmxQkA-w6yxPGiCJ9egOd5CyQR7lRTROau9p0_s" )
                        .POST ( HttpRequest.BodyPublishers.ofString ( jsonInput ) )
                        .build ( );

                HttpResponse<String> response = client.send ( request , HttpResponse.BodyHandlers.ofString ( ) );

                System.out.println ( "Response Code: " + response.statusCode ( ) );
                System.out.println ( "Response Body: " + response.body ( ) );
            }
            client.close ();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}