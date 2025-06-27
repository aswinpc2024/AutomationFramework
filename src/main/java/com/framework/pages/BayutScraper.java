package com.framework.pages;

import com.opencsv.CSVWriter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BayutScraper
{
    public static void main(String[] args) throws IOException, InterruptedException {
        // Set your ChromeDriver path

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // optional
        WebDriver driver = new ChromeDriver ( );

        driver.get("https://www.bayut.com/new-projects/uae/");
        Thread.sleep(5000); // wait for page to load

        // Scroll to load more projects
        for (int i = 0; i < 5; i++) {
            ((JavascriptExecutor) driver).executeScript( "window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(3000);
        }

        List<WebElement> projectCards = driver.findElements( By.cssSelector( "article"));
        List<String[]> data = new ArrayList<> ();

        // CSV Header
        data.add(new String[]{"Title", "Location", "Developer", "Price", "Area", "Bedrooms", "Bathrooms", "Handover Date", "Features", "Images", "Link"});

        for (WebElement card : projectCards) {
            String title = safeGetText(card, By.tagName("h2"));
            String location = safeGetText(card, By.cssSelector("span[data-testid='location-text']"));
            String developer = safeGetText(card, By.cssSelector("p[data-testid='developer-name']"));
            String price = safeGetText(card, By.cssSelector("span[data-testid='price']"));
            String area = safeGetText(card, By.cssSelector("span[data-testid='area']"));
            String bedrooms = safeGetText(card, By.cssSelector("span[data-testid='bedrooms']"));
            String bathrooms = safeGetText(card, By.cssSelector("span[data-testid='bathrooms']"));
            String handoverDate = safeGetText(card, By.cssSelector("span[data-testid='handover-date']"));
            String features = safeGetText(card, By.cssSelector("span[data-testid='features']"));
            String images = getImages(card);
            String link = safeGetAttribute(card, By.tagName("a"), "href");

            data.add(new String[]{title, location, developer, price, area, bedrooms, bathrooms, handoverDate, features, images, link});
        }

        // Write to CSV
        try (CSVWriter writer = new CSVWriter( new FileWriter ( "bayut_projectsd.csv"))) {
            writer.writeAll(data);
        }

        driver.quit();
        System.out.println("✅ Scraping complete. CSV saved as 'bayut_projectsd.csv'.");
    }

    private static String safeGetText(WebElement parent, By selector) {
        try {
            return parent.findElement(selector).getText().trim();
        } catch (NoSuchElementException e) {
            return "N/A";
        }
    }

    private static String safeGetAttribute(WebElement parent, By selector, String attr) {
        try {
            return parent.findElement(selector).getAttribute(attr);
        } catch (NoSuchElementException e) {
            return "N/A";
        }
    }

    private static String getImages(WebElement parent) {
        try {
            List<WebElement> imageElements = parent.findElements(By.cssSelector("img"));
            StringBuilder imageUrls = new StringBuilder();
            for (WebElement image : imageElements) {
                String src = image.getAttribute("src");
                if (src != null && !src.isEmpty()) {
                    imageUrls.append(src).append(";");
                }
            }
            return imageUrls.length() > 0 ? imageUrls.toString() : "N/A";
        } catch (Exception e) {
            return "N/A";
        }
    }


}
